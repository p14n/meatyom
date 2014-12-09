(set-env!
  :src-paths    #{"src/clj" "src/cljs"}
  :rsc-paths    #{"resources"}
  :dependencies '[[adzerk/boot-cljs      "0.0-2371-25" :scope "test"]
                  [adzerk/boot-cljs-repl "0.1.6"       :scope "test"]
                  [adzerk/boot-reload    "0.1.6"       :scope "test"]
                  
                  [om "0.7.0"]
                  [datascript "0.5.1"]
                  [reagent "0.4.3"]
                  [org.clojars.franks42/cljs-uuid-utils "0.1.3"]
                  [com.taoensso/sente "1.2.0"]
                  [http-kit "2.1.19"]
                  [compojure "1.1.8"]
                  [ring "1.2.1"]
])

(require
  '[adzerk.boot-cljs      :refer :all]
  '[adzerk.boot-cljs-repl :refer :all]
  '[adzerk.boot-reload    :refer :all])
