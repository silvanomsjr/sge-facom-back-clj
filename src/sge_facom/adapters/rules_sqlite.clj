(ns sge-facom.adapters.rules-sqlite
  (:require [next.jdbc :as jdbc]
            [sge-facom.infrastructure.db.sqlite :refer [ds]]
            [sge-facom.ports.rules-repository :refer [RulesRepository]]))

(defrecord SQLiteRepo []
  RulesRepository
  (save-rule [_ {:keys [text description active internship_type]}]
    (let [correct_type (or internship_type "OBRIGATORIO")]
      (jdbc/execute-one! ds
                         ["INSERT INTO rules (text, description, active, internship_type) VALUES (?, ?, ?, ?)"
                          text description active correct_type])))

  (find-rule-by-id [_ id]
    (jdbc/execute-one! ds
                       ["SELECT *
                        FROM rules
                        WHERE id = ?;" id]))

  (get-all-rules [_]
    (jdbc/execute! ds
                   ["SELECT *
                        FROM rules"]))

  (remove-rule [_ id]
    (jdbc/execute-one! ds
                       ["DELETE FROM rules
                        WHERE id = ?;" id]))

  (edit-rule [_ id rule]
    (jdbc/execute-one! ds
                       ["UPDATE rules
                        SET text = ?, description = ?, active = ?, internship_type = ?
                        WHERE id = ?",
                        (:text rule)
                        (:description rule)
                        (:active rule)
                        (:internship_type rule)
                        id])))

(defn make-rules-repo []
  (->SQLiteRepo))
