(ns sge-facom.infrastructure.auth.jwt
  (:require [buddy.sign.jwt :as jwt]
            [sge-facom.config :refer [secret-key-token]])
  (:import [java.time Instant]))

(defn generate_token [email user-type]
  (let [now (Instant/now)
        exp (.plusSeconds now (* 60 60 8))
        claims {:email email
                :userType user-type
                :exp (.getEpochSecond exp)}]
    (jwt/sign claims secret-key-token)))

(defn read_token [token]
  (jwt/unsign token secret-key-token))


