(ns meatyom.repl
  (:require [cemerick.austin.repls :refer (browser-connected-repl-js)]
            [compojure.route :refer (resources)]
            [compojure.core :refer (GET defroutes)]  
            ring.adapter.jetty
            [clojure.java.io :as io]))                                 

(defroutes site
  (resources "/") 
  (GET "/*" req {:status 200 :headers {"Content-Type" "text/html"}
   :body (str "<html><head>"
    "</head><body><div id='app'>Hey</div><script src='react-0.9.0.js'></script><script src='/app.js' type='text/javascript'></script><script>"
              (browser-connected-repl-js)
              "</script></body></html>")}))

(defn run
  []
  (defonce ^:private server
    (ring.adapter.jetty/run-jetty #'site {:port 8080 :join? false}))
  server)

(def repl-env (reset! cemerick.austin.repls/browser-repl-env
                      (cemerick.austin/repl-env)))

(defn cljs-browser-repl [] (cemerick.austin.repls/cljs-repl repl-env))

(defn cljs-project-repl [] (cemerick.piggieback/cljs-repl :repl-env (cemerick.austin/exec-env)))
