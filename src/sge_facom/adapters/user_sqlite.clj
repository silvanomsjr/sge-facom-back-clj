(ns sge-facom.adapters.user-sqlite
  (:require [next.jdbc :as jdbc]
            [sge-facom.infrastructure.db.sqlite :refer [ds]]
            [sge-facom.ports.user-repository :refer [UserRepository]]))

(defrecord SQLiteRepo []
  UserRepository
  (save-user [_ {:keys [email password-hash user-type]}]
    (jdbc/execute-one! ds
                       ["INSERT INTO users (email, password_hash, userTypeId) VALUES (?, ?, ?)"
                        email password-hash user-type]))

  (find-user-by-email [_ email]
    (jdbc/execute-one! ds
                       ["SELECT *
                        FROM users u
                        LEFT JOIN userTypes ut ON u.userTypeId = ut.id
                        WHERE u.email = ?;" email])))

(defn make-user-repo []
  (->SQLiteRepo))
