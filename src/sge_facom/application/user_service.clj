(ns sge-facom.application.user-service
  (:require
   [sge-facom.ports.user-repository :as repo]
   [sge-facom.domain.user :as domain]))

(defn login [user-repo email password]
  (let [user (repo/find-user-by-email user-repo email)
        credentials (domain/valid-credentials? user password)]
    credentials))

(defn register-user [user-repo email password user-type]
  (try
    (let [exists? (repo/find-user-by-email user-repo email)]
      (if exists?
        {:status :conflict}
        (let [user (domain/create-user! email password user-type)]
          (repo/save-user user-repo user)
          {:status :ok})))
    (catch Exception e
      {:status :error :message (.getMessage e)})))
