(ns app.ui
  (:require [uix.core :refer [defui $] :as uix]
            #?(:cljs [reitit.frontend.easy :as rfe])))

(defui layout [{:keys [children]}]
  ($ :div
     ($ :nav 
        {:class ["p-6" "bg-slate-500"]}
        ($ :ul
           {:class ["flex" "gap-4"]}
           ($ :li 
              ($ :a {:href "/"} "home"))
           ($ :li 
              ($ :a {:href "/about"} "about"))
           ($ :li 
              ($ :a {:href "/profile"} "profile"))))
     children))

(defui home []
  ($ layout
     (let [[count set-count] (uix/use-state 0)]
       ($ :div {:class ["flex" "gap-4"]}
          ($ :button {:on-click (fn [_] (set-count #(inc %)))} "+")
          ($ :p {:class ["text-green-600"]} (str "count: " count))
          ($ :button {:on-click (fn [_] (set-count #(dec %)))} "-")))

     ;; Example: navigate to another page [rfe/push-state replace-state]
     ($ :a {:on-click (fn [] #?(:cljs (rfe/navigate :app.router/about)))} "!go to about")
     ($ :p "ring, reitit, uix2, tailwindcss hot reloaded template")))

(defui about []
  ($ layout ($ :p "about")))

(defui profile []
  ($ layout ($ :p "profile")))

(defui app []
  ($ home))
