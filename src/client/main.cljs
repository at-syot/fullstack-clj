(ns client.main
  (:require [uix.core :refer [$]]
            [uix.dom :as dom.client]
            
            [app.ui]
            [app.router]))

;; ** https://react.dev/reference/react-dom/client/hydrateRoot#hydrating-server-rendered-html

(defn -init []
  ;; Just before hydration
  ;; get active route and pass to with-router-component
  ;; * this is to solve hydration error *

  (dom.client/hydrate-root 
    (js/document.getElementById "root") 
    ($ app.router/root {:init-named-route (app.router/get-current-route)}))
  )

