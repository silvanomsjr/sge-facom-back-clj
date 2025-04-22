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
      (if (contains? #{"/teste" "/users/login" "/users/cadastro"} uri)
        (do
          (println "Aqui passou")
          (handler req))
        (try
          (if-let [claims (jwt/read_token token)]
            (handler (assoc req :claims claims))
            (-> (response (json/generate-string {:error "Token inválido ou ausente"}))
                (status 401)
                (assoc :headers {"Content-Type" "application/json"})))
          (catch Exception e
            (println "Erro ao veriricar token: " (.getMessage e))
            (-> (response (json/generate-string {:error "Token inválido ou corrompido"}))
                (status 401)
                (assoc :headers {"Content-Type" "application/json"}))))))))

