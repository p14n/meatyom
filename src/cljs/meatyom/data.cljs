(ns meatyom.data
  (:require [datascript :as d]
            [reagent.core :as r]
            [cljs-uuid-utils :as uuid]))

(def conn (d/create-conn))

;;query and update the result of the query with
;;changes to the db
(defn- bind
  ([conn q]
   (bind conn q (r/atom nil)))
  ([conn q state]
   (let [k (uuid/make-random-uuid)]
     (reset! state (d/q q @conn))
     (d/listen! conn k (fn [tx-report]
                         (let [novelty (d/q q (:tx-data tx-report))]
                           (when (not-empty novelty) ;; Only update if query results actually changed
                             (reset! state (d/q q (:db-after tx-report)))))))
     (set! (.-__key state) k)
     state)))


(defn register-listener [key f]
  (d/listen! conn key f))

(defn unbind
  [state]
  (d/unlisten! conn (.-__key state)))


(defn- capture-update [key ref olds news]
  (println "This where we capture the deltas"))

(defn q[query]
  (let [state-atom (bind conn query)]
    ;; (add-watch state-atom nil capture-update)
    state-atom))

(defn w[data]
  "Write EDN to the database"
  (d/transact! conn data))

(defn e[id]
  (d/touch (d/entity (d/db conn) id)))

;; (defn ef[ids]
;;   (e (first ids)))
(defn ef [ids]
  (e (first ids)))


