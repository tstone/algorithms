(ns sudoku-spec
  (:require [speclj.core :refer :all]
            [sudoku :refer :all]))

(def board [
  [ 1   2   3    nil nil nil   nil nil nil]
  [ 4   5   6     1   2   3    nil nil nil]
  [ 7   8   9    nil nil nil   nil nil nil]

  [nil nil nil   nil nil nil   nil nil nil]
  [nil nil nil    4   5   6    nil nil nil]
  [nil nil nil   nil nil nil   nil nil nil]

  [nil nil nil   nil nil nil   nil nil nil]
  [nil nil nil   nil nil nil   nil nil nil]
  [nil nil nil   nil nil nil   nil nil nil] ])


(describe "position"
  (it "returns the value at the specified position"
    (should (= 5 (position board 1 1)))))

(run-specs)
