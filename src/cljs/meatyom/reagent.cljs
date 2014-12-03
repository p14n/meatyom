(ns meatyom.reagent
  (:require [clojure.string :as string]
            [reagent.core :as r]
            [meatyom.data :as d]))

;;; Add some data
(defonce initial-transaction (d/w [{:db/id -1 :name "Bob" :age 30}
     {:db/id -2 :name "Sally" :age 25}]))

(defn write-value [entity key]
  (fn [el]
    (let [new-val (-> el .-target .-value)
          new-ent {:db/id (:db/id entity) key new-val}]
      (d/w [new-ent]))))

(def last-tx (r/atom {}))

(d/register-listener :for-display (fn[x](swap! last-tx #(do x))))

(defn person-component [pers]
  [:li {:key (:db/id pers)}
   [:input {:type "text"
            :default-value (:name pers)
            :on-change (write-value pers :name)}]
   [:input {:type "text"
            :default-value (:age pers)
            :on-change (write-value pers :age)}]])

(defn new-person-component []
  (let [namein [:input {:type "text"}]
        agein [:input {:type "text"}]]
    [:li
     namein
     agein
     [:input {:type "button"
              :on-click #(.log js/console (namein :type))}]]))

(defn persons-component []
  [:ul
   (let [persons (d/q '[:find ?e :where [?e :age ?a][(< ?a 31)]])]
     (map #(person-component (d/ef %)) @persons))
   (new-person-component)])

(defn page-component[]
  [:div [:span (str (:tx-data @last-tx))]
   (persons-component)])

(defn ^:export run []
  (r/render-component [page-component]
                            (. js/document (getElementById "app"))))
