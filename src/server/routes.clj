(ns server.routes
  (:require [compojure.core :refer [defroutes GET]]
            [hiccup2.core :as h]
            [hiccup.util :refer [raw-string]]
            
            [uix.dom.server :as dom.server]
            [uix.core :refer [$]]
            
            [app.ui]))

(defn index [inner]
  (str
   "<!DOCTYPE html>" 
   (h/html
    [:html.no-js {:lang "en"}
     [:head
      [:meta {:charset "utf-8"}]
      [:meta {:http-equiv "x-ua-compatible" :content "ie=edge"}]
      [:meta {:name "description" :content ""}]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
      [:title "Affordable Online Art Therapy, Training, Somatic Education & Tools - Nattanan Sornpeng, art therapy & somatic education"]
      [:link {:rel "stylesheet" :href "/styles/output.css"}]]
     [:body
      [:div#root (raw-string inner)]
      [:script {:src "/js/entry.js"}]]])))

(defn render-app [_]
  {:status 200
   :headers {"Content-Type" "text/html"
             ; "Cache-Control" "no-cache, no-store, must-revalidate"
             ; "Expires" "0" ;; not sure that we need this ??
             }
   :body 
   (-> ($ app.ui/app)
       dom.server/render-to-static-markup
       index)})

(defroutes app 
  (GET "/" [] render-app))


