(ns sge-facom.api.routes
  (:require [compojure.core :refer [context routes]]
            [sge-facom.api.users-handlers :as users]
            [sge-facom.api.rules-handlers :as rules]
            [sge-facom.api.teste-handlers :as testes]
            [compojure.route :as route]))

(defn app [{:keys [user-repo rule-repo]}]
  (routes
   (context "/users" [] (users/user-routes user-repo))
   (context "/teste" [] testes/routes)
   (context "/rules" [] (rules/rules-routes rule-repo))
   (route/not-found "Rota não encontrada")))

