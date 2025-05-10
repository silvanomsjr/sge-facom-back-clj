(ns sge-facom.core
  (:gen-class)
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.cors :refer [wrap-cors]]
            [taoensso.timbre :as log]
            [sge-facom.infrastructure.middlewares.auth :refer [wrap-auth]]
            [sge-facom.infrastructure.middlewares.error :refer [wrap-error-handling]]
            [sge-facom.api.routes :refer [app]]
            [sge-facom.infrastructure.db.sqlite :as repo]
            [sge-facom.adapters.user-sqlite :refer [make-user-repo]]
            [sge-facom.adapters.rules-sqlite :refer [make-rules-repo]]))

(defn -main []
  (try
    (log/info "Iniciando aplicação")
    (repo/init-db)

    (let [port (or (some-> (System/getenv "PORT") Integer/parseInt) 3000)
          user-repo-instance (make-user-repo)
          rule-repo-instance (make-rules-repo)
          deps {:user-repo user-repo-instance
                :rule-repo rule-repo-instance}]
      (log/info "Banco de dados inicializado com sucesso")
      (log/info "Rodando na porta " port)
      (run-jetty (-> (app deps)
                     (wrap-cors
                      :access-control-allow-origin [#".*"]
                      :access-control-allow-methods [:get :post :put :delete :options])
                     wrap-auth
                     wrap-error-handling
                     wrap-reload)
                 {:port port :join? false}))

    (catch Exception e
      (log/error e "Erro ao conectar ao banco"))))
