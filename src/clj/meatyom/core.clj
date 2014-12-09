(ns meatyom.core
  (:require [org.httpkit.server :as h]
            [taoensso.sente :as sente]
            [compojure.core :refer (GET POST ANY defroutes routes)]
            [compojure.handler :as handler]))


(let [{:keys [ch-recv
              send-fn
              ajax-post-fn
              ajax-get-or-ws-handshake-fn
              connected-uids]}
      (sente/make-channel-socket! {})]
  (def ring-ajax-post                ajax-post-fn)
  (def ring-ajax-get-or-ws-handshake ajax-get-or-ws-handshake-fn)
  (def ch-chsk                       ch-recv)        ; ChannelSocket's receive channel
  (def chsk-send!                    send-fn)        ; ChannelSocket's send API fn
  (def connected-uids                connected-uids) ; Watchable, read-only atom
)

(defroutes comm-routes
  (GET  "/chsk" req (ring-ajax-get-or-ws-handshake req))
  (POST "/chsk" req (ring-ajax-post                req)))

(def app
  (handler/site
   (routes
       comm-routes)))

(defonce server (atom nil))

(defn- logf [fmt & xs] (println (apply format fmt xs)))

(defn- event-msg-handler
  [{:as ev-msg :keys [ring-req event ?reply-fn]} _]
  (let [session (:session ring-req)
        uid     (:uid session)
        [id data :as ev] event]

    (logf "Event: %s" ev)))

(def chsk-router
  (sente/start-chsk-router-loop! event-msg-handler ch-chsk))

(defn stop-server []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))

(defn start-server []
  (reset! server (h/run-server #'app {:port 8081})))
