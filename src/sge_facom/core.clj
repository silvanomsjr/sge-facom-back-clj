(ns sge-facom.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.cors :refer [wrap-cors]]
            [taoensso.timbre :as log]
            [sge-facom.infrastructure.middlewares.auth :refer [wrap-auth]]
            [sge-facom.api.routes :refer [app]]
            [sge-facom.infrastructure.db.sqlite :as repo]
            [sge-facom.adapters.user-sqlite :refer [make-user-repo]]))

(defn -main []
  (try
    (log/info "Iniciando aplicação")
    (repo/init-db)

    (let [user-repo-instance (make-user-repo)
          deps {:user-repo user-repo-instance}]
      (log/info "Banco de dados inicializado com sucesso")
      (run-jetty (-> (app deps)
                     (wrap-cors
                      :access-control-allow-origin [#".*"]
                      :access-control-allow-methods [:get :post :put :delete :options])
                     wrap-auth
                     wrap-reload)
                 {:port 3000 :join? false}))

    (catch Exception e
      (log/error e "Erro ao conectar ao banco"))))
