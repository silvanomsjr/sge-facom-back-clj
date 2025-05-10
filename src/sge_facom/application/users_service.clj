(ns sge-facom.application.users-service
  (:require
   [sge-facom.ports.users-repository :as repo]
   [sge-facom.domain.user :as domain]))

(defn login [user-repo email password]
  (let [user (repo/find-user-by-email user-repo email)
        credentials (domain/valid-credentials? user password)]
    credentials))

(defn register-user [user-repo email password]
  (try
    (let [exists? (repo/find-user-by-email user-repo email)]
      (if exists?
        {:status :conflict}
        (let [user (domain/create-user! email password)]
          (repo/save-user user-repo user)
          {:status :ok})))
    (catch Exception e
      {:status :error :message (.getMessage e)})))

(defn list-users [user-repo]
  (try
    (let [users (repo/get-users user-repo)]
      {:status :ok :response users})
    (catch Exception e
      {:status :error :response (.getMessage e)})))
