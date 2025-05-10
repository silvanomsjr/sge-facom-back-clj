(ns sge-facom.ports.rules-repository)

(defprotocol RulesRepository
  (save-rule [this rule])
  (find-rule-by-id [this id])
  (get-all-rules [this])
  (remove-rule [this id])
  (edit-rule [this id rule]))
