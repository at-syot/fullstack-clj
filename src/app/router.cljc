(ns app.router
  (:require [uix.core :refer [$ defui defhook] :as uix]
            #?(:cljs [reitit.core :as r])
            #?(:cljs [reitit.frontend :as rf])
            #?(:cljs [reitit.frontend.easy :as rfe])
            [app.ui :as ui]))

#?(:cljs 
   (def ^:private router-context (uix/create-context)))

#?(:cljs 
   (defui ^:private with-router
     "Creates router instance for given routes
     and shares it via React context"
     [{:keys [init-named-route routes children]}]
     (let [router (uix/use-memo #(rf/router routes) [routes])
           [route set-route] 
           (uix/use-state #(r/match-by-name router init-named-route))] 
       ;; (js/console.log "curr-route" (clj->js route))
       (uix/use-effect
         #(rfe/start! router set-route {:use-fragment false})
         [router])
       ($ (.-Provider router-context) {:value (:data route)}
          children))))

(def routes
  [["/" {:view ui/home 
         :name ::home}]
   ["/about" {:view ui/about 
              :name ::about}]
   ["/profile" {:view ui/profile
                :name ::profile}]])

#?(:cljs 
   (defui app []
     (let [{:keys [view]} (uix/use-context router-context)]
       ($ view))))

#?(:cljs
   (defui root [{:keys [init-named-route]}]
     (js/console.log "!" (clj->js init-named-route))
     ($ with-router {:init-named-route init-named-route 
                     :routes routes}
        ($ app))))

#?(:cljs
   (defn get-current-route []
     (let [!init-named-route (atom ::home)]
       (rfe/start! 
         (rf/router routes) 
         #(reset! 
            !init-named-route 
            (get-in % [:data :name]))
         {:use-fragment false})
       @!init-named-route)))

