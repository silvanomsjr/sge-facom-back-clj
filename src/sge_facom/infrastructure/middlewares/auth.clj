(ns sge-facom.infrastructure.middlewares.auth
  (:require
   [clojure.string :as string]
   [cheshire.core :as json]
   [ring.util.response :refer [response status]]
   [sge-facom.infrastructure.auth.jwt :as jwt]))

(defn wrap-auth [handler]
  (fn [req]
    (let [uri (:uri req)
          token (get-in req [:headers "authorization"])
          token (some-> token (string/replace #"^Bearer " ""))]
      (if (contains? #{"/teste" "/users/login" "/users/cadastro" "/users/list"} uri)
        (handler req)
        (let [claims (try
                       (jwt/read_token token)
                       (catch Exception e
                         (println "Erro ao verificar token: " (.getMessage e))
                         ::invalid-token))]
          (if (and claims (not= claims ::invalid-token))
            (handler (assoc req :claims claims))
            (-> (response (json/generate-string {:error "Token inválido ou ausente"}))
                (status 401)
                (assoc :headers {"Content-Type" "application/json"}))))))))

