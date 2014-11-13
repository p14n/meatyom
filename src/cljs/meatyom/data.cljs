(ns meatyom.data
  (:require [datascript :as d]))

(def conn (d/create-conn))

(defn- capture-update [key ref olds news]
  (println "This where we capture the deltas"))

(defn q[query]
  (let [state-atom (bind conn query)]
    (add-watch state-atom nil capture-update)))

(defn w[data]
  (d/transact! conn data))

;;query and update the result of the query with
;;changes to the db
(defn- bind
  ([conn q]
   (bind conn q (atom nil)))
  ([conn q state]
   (let [k (uuid/make-random-uuid)]
     (reset! state (d/q q @conn))
     (d/listen! conn k (fn [tx-report]
                         (let [novelty (d/q q (:tx-data tx-report))]
                           (when (not-empty novelty) ;; Only update if query results actually changed
                             (reset! state (d/q q (:db-after tx-report)))))))
     (set! (.-__key state) k)
     state)))
 
(defn unbind
  [state]
  (d/unlisten! conn (.-__key state)))

