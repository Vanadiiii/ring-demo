(ns ring-demo.core
  (:require [ring.adapter.jetty :as ring.jetty])
  (:gen-class))

; just get any request from localhost:999
; and return 'hello-world' string with 200-sc
(defn handler
  "just get any (!) request from localhost:999
  and return 'hello-world' string with 200-sc"
  [_]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Hello, world"})

(defn start []
  (ring.jetty/run-jetty handler {:port  9999
                                 :join? false}))

(defn -main
  [& _]
  (start))
