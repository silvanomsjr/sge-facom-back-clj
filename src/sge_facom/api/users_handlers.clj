(ns sge-facom.api.users-handlers
  (:require [compojure.core :refer [POST routes]]
            [ring.util.response :refer [response status content-type]]
            [cheshire.core :as json]
            [sge-facom.infrastructure.auth.jwt :as jwt]
            [sge-facom.application.user-service :as user-service]))

(defn user-routes [repo]
  (routes
   (POST "/cadastro" req
     (let [{:keys [email password user-type]} (-> req :body slurp (json/parse-string true))]
       (if (not (.endsWith email "@ufu.br"))
         (-> (response (json/generate-string {:error "Email deve ser seu email constitucional"}))
             (status 400)
             (content-type "application/json"))
         (let [result (user-service/register-user repo email password user-type)]
           (cond
             (= (:status result) :ok)
             (-> (response (json/generate-string {:message "Usuário criado com sucesso"}))
                 (status 201)
                 (content-type "application/json"))
             (= (:status result) :conflict)
             (-> (response (json/generate-string {:error "Usuário já possui cadastro"}))
                 (status 201)
                 (content-type "application/json"))

             :else
             (-> (response (json/generate-string {:error "Erro ao cadastrar usuário"}))
                 (status 500)
                 (content-type "application/json")))))))

   (POST "/login" req
     (let [{:keys [email password]} (-> req :body slurp (json/parse-string true))
           credentials (user-service/login repo email password)]
       (if credentials
         (let [user_type (get-in credentials [:userTypes/name])
               token (jwt/generate_token  email user_type)]
           (-> (response (json/generate-string {:token token}))
               (content-type "application/json")))
         (-> (response (json/generate-string {:error "Email ou senha inválidos"}))
             (status 401)
             (content-type "application/json")))))))

