(ns build
  (:require [clojure.tools.build.api :as b]))

(def lib 'sge-facom/app)
(def class-dir "target/classes")
(def basis (b/create-basis {:project "deps.edn"}))
(def uber-file "target/app.jar")

(defn clean [_]
  (b/delete {:path "target"}))

(defn uber [_]
  (clean nil)
  (b/copy-dir {:src-dirs ["resources"]
               :target-dir "target/classes"})
  (b/copy-dir {:src-dirs ["."]
               :include ["sgefacom.db"]
               :target-dir "target"})
  (b/compile-clj {:src-dirs ["src"]
                  :class-dir class-dir
                  :basis basis
                  :ns-compile ['sge-facom.core]})
  (b/uber {:class-dir class-dir
           :uber-file uber-file
           :basis basis
           :main 'sge-facom.core}))

