(ns server.core
  (:gen-class)
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.resource :refer [wrap-resource]]
            
            [server.routes]))

(comment 
  (restart!)
  (stop-server!))

(defonce server (atom nil))

(comment
  (require '[clojure.string :as string])
  (require '[clojure.java.io :as io])

  (def route 
    (-> server.routes/app
        (wrap-resource "public")))
  (-> (java.lang.System/getProperty "java.class.path")
      (string/split #":")
      ; #(filter (fn [s] true) %)
      )

  (io/resource "public/js/entry.js")
  
  
  (route {:request-method :get
          :uri "/"})
  (route {:request-method :get
          :uri "/js/entry.js"}))


(defn start-server! []
  (reset! 
    server
    (run-jetty
      (-> #'server.routes/app
          (wrap-resource "public")
          ; wrap-reload
          )
      {:port 3000 :join? false}))
  (println "Server started on port 3000"))

(defn -main []
  (start-server!))

(defn stop-server! []
  (when @server 
    (.stop @server)
    (reset! server nil)
    (prn "server stoped")))

(defn restart! []
  (stop-server!)
  (start-server!))


