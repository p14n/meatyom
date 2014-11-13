(set-env!
  :src-paths    #{"src/clj" "src/cljs"}
  :rsc-paths    #{"resources"}
  :dependencies '[[adzerk/boot-cljs      "0.0-2371-20" :scope "test"]
                  [adzerk/boot-cljs-repl "0.1.5"       :scope "test"]
                  [adzerk/boot-reload    "0.1.3"       :scope "test"]
                  [om "0.7.0"]
                  [datascript "0.5.1"]
                  [reagent "0.2.0"]])

(require
  '[adzerk.boot-cljs      :refer :all]
  '[adzerk.boot-cljs-repl :refer :all]
  '[adzerk.boot-reload    :refer :all])
