(ns sge-facom.domain.user
  (:require [buddy.hashers :as hashers]))

(defn create-user!
  "Prepara um MAP da maneira correta para inserir no banco"
  [email password]
  {:email email :password-hash (hashers/derive password)})

(defn valid-credentials?
  "Valida as credenciais do usuário antes do login"
  [user password]
  (when user
    (if (hashers/check password (:password_hash user))
      user
      nil)))
