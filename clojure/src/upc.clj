(ns upc
  (:use [clojure.math.combinatorics :only [permutations]]))


;; RULES
;; vector -> boolean

(defn second-is-2-or-4?
  "True if second digit is 2 or 4"
  [v]
  (let [second (get v 1)]
    (or
     (= second 2)
     (= second 4))))

(defn last-larger-than-3rd?
  "True if last digit is larger than the 3rd digit"
  [v]
  (let [third (get v 2)]
    (> (last v) third)))

(defn hasnt-sequence-2-3?
  "True if 3 does not immediately follow 2"
  [v]
  (not (in?
    (partition-all 2 1 v) '(2 3))))


;; USAGE

(defn validate
  "True if all rules are true"
  [val rules]
  (every? #(= %1 true)
    (map #(%1 val) rules)))

(defn generate
  "Generates all possible values given a set of rules"
  [rules]
  (filter #(validate %1 rules)
    (permutations [1 2 3 4 5])))


;; HELPERS

(defn in?
  "True if seq contains elem"
  [seq elem]
  (some #(= elem %) seq))


;; EXAMPLE

(generate
  [second-is-2-or-4?
   last-larger-than-3rd?
   hasnt-sequence-2-3?])





