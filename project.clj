(defproject com.p14n/meatyom "1.0"
  :source-paths ["src/clj"]

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2202"]
                 [datascript "0.5.1"]
                 [ring "1.2.2"]
                 [compojure "1.1.8"]
                 [enlive "1.1.5"]
                 [om "0.7.0"]
                 [reagent "0.2.0"]
                 [org.clojars.franks42/cljs-uuid-utils "0.1.3"]]
  :resource {
             :resource-paths ["resources"]
             :target-path "target/classes"
  }
  :hooks [leiningen.resource]
  :profiles {:dev {
                   :repl-options {:init-ns meatyom.core}
                   :plugins [[com.cemerick/austin "0.1.5"]
                             [lein-cljsbuild "1.0.3"]
                             [lein-resource "14.10.1"] ]
                   :cljsbuild {:builds [{:source-paths ["src/cljs"]
                                         :compiler {:output-to "target/classes/public/app.js"
                                                    :optimizations :simple
                                                    :pretty-print true}}]}}})

