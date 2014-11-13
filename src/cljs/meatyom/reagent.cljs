(ns meatyom.reagent
  (:require [clojure.string :as string]
            [reagent.core :as r]
            [meatyom.data :as d]))

;;; Add some data
(d/w [{:db/id -1 :name "Bob" :age 30}
                   {:db/id -2 :name "Sally" :age 25}])


(defn simple-component [name]
  [:div
   [:p "I am a " name "!"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red "] "text."]])

;; (defn simpler-component []
;;   [:span "HHEEEYEYEYE"])

(defn ^:export run []
  (r/render-component [simple-component "thing"]
                            (. js/document (getElementById "app"))))
