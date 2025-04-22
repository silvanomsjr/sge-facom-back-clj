(ns sge-facom.api.routes
  (:require [compojure.core :refer [context routes]]
            [sge-facom.api.users-handlers :as users]
            [sge-facom.api.teste-handlers :as testes]
            [compojure.route :as route]))

(defn app [{:keys [user-repo]}]
  (routes
   (context "/users" [] (users/user-routes user-repo))
   (context "/teste" [] testes/routes)
   (route/not-found "Rota não encontrada")))

