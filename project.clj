(defproject webmidi "0.1.0-SNAPSHOT"
  :description "Webmidi API for ClojureScript"
  :url "https://github.com/chr15m/webmidi"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.439"]
                 [reagent "0.8.1"]]

  :plugins [[lein-cljsbuild "1.1.5"]
            [lein-figwheel "0.5.16"]]

  :clean-targets ^{:protect false}
  [:target-path
   [:cljsbuild :builds :app :compiler :output-dir]
   [:cljsbuild :builds :app :compiler :output-to]]

  :resource-paths ["public"]

  :figwheel {:http-server-root "."
             :nrepl-port 7002
             :nrepl-middleware [cider.piggieback/wrap-cljs-repl]
             :css-dirs ["public/css"]}

  :cljsbuild {:builds {:app
                       {:source-paths ["src" "env/dev/cljs"]
                        :compiler
                        {:main "webmidi.dev"
                         :output-to "public/js/app.js"
                         :output-dir "public/js/out"
                         :asset-path   "js/out"
                         :source-map true
                         :optimizations :none
                         :pretty-print  true}
                        :figwheel
                        {:on-jsload "webmidi.core/mount-root"}}
                       :release
                       {:source-paths ["src" "env/prod/cljs"]
                        :compiler
                        {:output-to "public/js/app.js"
                         :output-dir "public/js/release"
                         :asset-path   "js/out"
                         :optimizations :advanced
                         :pretty-print false}}}}

  :aliases {"package" ["do" "clean" ["cljsbuild" "once" "release"]]}

  :profiles {:dev {:source-paths ["src" "env/dev/clj"]
                   :dependencies [[binaryage/devtools "0.9.10"]
                                  [figwheel-sidecar "0.5.16"]
                                  [nrepl "0.4.4"]
                                  [cider/piggieback "0.3.8"]]}})
