(ns ring-demo.handler
  (:require [ring-demo.storage :as storage])
  (:import (java.util UUID)))

(defn send-string [_]
  {:status       200
   :body         "oh the code again"
   :content-type "text/html"})

(defn create-user [{user :body-params}]
  (let [id (str (UUID/randomUUID))
        saved-user (assoc user :id id)]
    (->> saved-user
         (swap! storage/users assoc id))
    {:status       201
     :content-type "application/json"
     :body         saved-user}))

(defn get-users [_]
  {:status       200
   :content-type "application/json"
   :body         (vals @storage/users)})

(defn get-user-by-id [{{:keys [id]} :path-params}]
  (let [user (get @storage/users id)]
    (if user
      {:status 200
       :body   user}
      {:status 404
       :body   {:message (str "User with id " id " not found")}})))
