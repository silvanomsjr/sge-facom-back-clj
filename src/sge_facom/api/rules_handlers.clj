(ns sge-facom.api.rules-handlers
  (:require [compojure.core :refer [POST GET DELETE routes]]
            [ring.util.response :refer [response status content-type]]
            [cheshire.core :as json]
            [sge-facom.application.rules-service :as rules-service]))

(defn rules-routes [repo]
  (routes
   (POST "/create" req
     (let [rule (-> req :body slurp (json/parse-string true))
           result (rules-service/create-rule repo rule)]
       (cond
         (= (:status result) :ok)
         (-> (response (json/generate-string {:message "Regra criada com sucesso"}))
             (status 201)
             (content-type "application/json"))
         (= (:status result) :invalid-data)
         (-> (response (json/generate-string {:error "Dados inválidos"}))
             (status 201)
             (content-type "application/json"))
         :else
         (-> (response (json/generate-string {:error "Erro ao cadastrar regra"}))
             (status 500)
             (content-type "application/json")))))

   (POST "/update" req
     (let [rule (-> req :body slurp (json/parse-string true))
           id (:id rule)
           result (rules-service/update-rule repo id rule)]
       (cond
         (= (:status result) :ok)
         (-> (response (json/generate-string {:message "Regra atualizada com sucesso"}))
             (status 200)
             (content-type "application/json"))
         (= (:status result) :not-found)
         (-> (response  (json/generate-string {:error "Regra não encontrada"}))
             (status 404)
             (content-type "application/json"))

         :else
         (-> (response (json/generate-string {:error "Erro ao atualizar"}))
             (status 500)
             (content-type "application/json")))))

   (DELETE "/delete/:id" [id]
     (let [result (rules-service/delete-rule repo (Integer/parseInt id))]
       (if (= (:status result) :ok)
         (-> (response (json/generate-string {:message "Regra deletada com sucesso"}))
             (status 200)
             (content-type "application/json"))
         (-> (response (json/generate-string {:error "Erro ao deletar regra"}))
             (status 500)
             (content-type "application/json")))))

   (GET "/get/:id" [id]
     (let [rule (rules-service/get-rule repo id)]
       (if rule
         (-> (response (json/generate-string rule))
             (status 200)
             (content-type "application/json"))
         (-> (response (json/generate-string {:error "Regra não encontrada"}))
             (status 404)
             (content-type "application/json")))))

   (GET "/list" _req
     (let [rules (rules-service/list-rules repo)]
       (if rules
         (-> (response (json/generate-string rules))
             (status 200)
             (content-type "application/json"))
         (-> (response (json/generate-string {:error "Erro ao listar regras"}))
             (status 500)
             (content-type "application/json")))))))

