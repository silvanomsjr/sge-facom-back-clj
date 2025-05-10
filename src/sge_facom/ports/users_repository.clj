(ns sge-facom.ports.users-repository)

(defprotocol UserRepository
  (save-user [this user])
  (find-user-by-email [this email])
  (get-users [this]))
