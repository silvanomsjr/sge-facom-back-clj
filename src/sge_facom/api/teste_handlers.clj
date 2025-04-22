(ns sge-facom.api.teste-handlers
  (:require [compojure.core :refer [GET defroutes]]
            [ring.util.response :as res]))

(defroutes routes
  (GET "/" []
    (res/response "Funcionando")))

