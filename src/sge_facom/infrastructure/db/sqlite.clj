(ns sge-facom.infrastructure.db.sqlite
  (:require [next.jdbc :as jdbc]
            [clojure.java.io :as io]
            [clojure.string :as string]))

(def db-spec {:dbtype "sqlite" :dbname "sgefacom.db"})

(defn read-sql-file [path]
  (slurp (io/resource path)))

(def ds (jdbc/get-datasource db-spec))

(defn init-db []
  (let [sql-content (read-sql-file "schema.sql")
        statements (string/split sql-content #";\s*")]
    (doseq [stmt statements
            :when (not (string/blank? stmt))]
      (jdbc/execute! ds [stmt]))))
