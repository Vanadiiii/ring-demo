(ns ring-demo.core
  (:require [muuntaja.core :as m]
            [reitit.ring :as ring]
            [reitit.ring.middleware.muuntaja :as muuntaja]
            [ring-demo.handler :as handler]
            [ring.adapter.jetty :as ring.jetty])
  (:gen-class))

(def app
  (ring/ring-handler
    (ring/router
      ["/"
       ["users/:id"
        {:get handler/get-user-by-id}]
       ["users"
        {:get  handler/get-users
         :post handler/create-user}]
       ["" handler/send-string]]
      {:data {:muuntaja   m/instance
              :middleware [muuntaja/format-middleware]}})))

(defn start []
  (ring.jetty/run-jetty app {:port  9999
                             :join? false}))

(defn -main
  [& _]
  (start))
