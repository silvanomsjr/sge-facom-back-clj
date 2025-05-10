(ns sge-facom.infrastructure.middlewares.error
  (:require
   [cheshire.core :as json]
   [ring.util.response :refer [response status content-type]]))

(defn wrap-error-handling [handler]
  (fn [req]
    (try
      (handler req)
      (catch Exception e
        (let [error-msg (.getMessage e)]
          (println "Erro capturado: " error-msg)
          (-> (response (json/generate-string {:error "Erro interno do servidor"}))
              (status 500)
              (content-type "application/json")))))))

