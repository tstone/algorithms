(defproject clojure-algo "0.1.0-SNAPSHOT"
  :description "Various bits of algorithms written in Clojure"
  :url "https://github.com/tstone/algorithms"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/math.numeric-tower "0.0.4"]
                 [org.clojure/math.combinatorics "0.0.8"]]
  :main sudoku
  :profiles {:dev {:dependencies [[speclj "3.1.0"]]}}
  :plugins [[speclj "3.1.0"]]
  :test-paths ["spec"])
