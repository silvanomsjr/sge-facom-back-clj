(ns sge-facom.domain.rule)

(defrecord Rule [text description active internship_type])

(defn create-rule
  "Cria nova regra"
  [{:keys [text active description internship_type]}]
  (->Rule text
          description
          (if (some? active) active 1)
          (if (some? internship_type) internship_type "OBRIGATORIO")))

(defn edit-rule
  "Atualiza uma rule existente com novos valores"
  [rule new-data]
  (merge rule new-data))

(defn validate-rule!
  "Retorna True caso os campos essenciais estiverem preenchidos"
  [rule]
  (and (:text rule)
       (:description rule)
       (:internship_type rule)))


