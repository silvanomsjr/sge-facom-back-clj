(ns sge-facom.domain.user
  (:require [buddy.hashers :as hashers]))

(defn create-user! [email password userType]
  {:email email :password-hash (hashers/derive password) :user-type userType})

(defn valid-credentials? [user password]
  (when user
    (if (hashers/check password (:users/password_hash user))
      user
      nil)))
