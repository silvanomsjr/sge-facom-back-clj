(ns sge-facom.ports.user-repository)

(defprotocol UserRepository
  (save-user [this user])
  (find-user-by-email [this email]))
