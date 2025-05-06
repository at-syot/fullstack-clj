(ns app.ui
  (:require [uix.core :refer [defui $] :as uix]))

(defui app []
  ($ :div 
     {:class "text-red-500"} 
     "ring, reitit, uix2, tailwindcss hot reloaded template"))

