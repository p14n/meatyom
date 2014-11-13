(ns meatyom.om
  (:require [clojure.browser.repl]
            [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(def app-state (atom {}))
(swap! app-state assoc :text "Do it lve!")

;; (om/root
;;  (fn [app owner]
;;    (dom/div nil
;;             (dom/span nil (:text app))
;;             (dom/hr)
;;     (dom/span nil (:text2 app))))
;;   app-state
;;   {:target (. js/document (getElementById "app"))})

;; (om/root contacts-view app-state
;;          {:target (. js/document (getElementById "contacts"))})
