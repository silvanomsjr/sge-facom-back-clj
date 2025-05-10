(ns sge-facom.application.rules-service
  (:require
   [sge-facom.domain.rule :as domain]
   [sge-facom.ports.rules-repository :as repo]))

(defn create-rule [repo-rule rule-data]
  (let [rule (domain/create-rule rule-data)]
    (if (domain/validate-rule! rule)
      (do
        (repo/save-rule repo-rule rule)
        {:status :ok :rule rule})
      {:status :invalid-data})))

(defn update-rule [repo-rule id novos-dados]
  (let [existing (repo/find-rule-by-id repo-rule id)]
    (if existing
      (let [updated (domain/edit-rule existing novos-dados)]
        (repo/edit-rule repo-rule id updated)
        {:status :ok :rule updated})
      {:status :not-found})))

(defn list-rules [repo-rule]
  (repo/get-all-rules repo-rule))

(defn get-rule [repo-rule id]
  (repo/find-rule-by-id repo-rule id))

(defn delete-rule [repo-rule id]
  (let [existing (repo/find-rule-by-id repo-rule id)]
    (if existing
      (do (repo/remove-rule repo-rule id)
          {:status :ok})
      {:status :not-found})))
