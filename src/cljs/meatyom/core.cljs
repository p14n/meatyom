(ns meatyom.core
  (:require [clojure.browser.repl]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(def app-state (atom {}))
(swap! app-state assoc :text "Do it lve!")

(om/root
  (fn [app owner]
    (dom/h1 nil (:text app)))
  app-state
  {:target (. js/document (getElementById "app"))})

;; (om/root contacts-view app-state
;;          {:target (. js/document (getElementById "contacts"))})
