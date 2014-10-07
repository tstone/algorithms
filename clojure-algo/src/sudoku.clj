(ns sudoku
  (:require [clojure.math.numeric-tower :as math]))



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


;; Helper Functions

(defn identity-set
  "Transform the given collection into a set with no nils"
  [xs]
  (filter identity
    (into #{} xs)))


;; Board Read Functions

(defn pos
  "Get a specific position on the board"
  [board x y]
  (get-in board [x y]))

(defn row
  "Get a specific row"
  [board r]
  (identity-set
    (get board r)))

(defn column
  "Get a specific column"
  [board c]
  (identity-set
    (nth (apply map vector board) c)))

(defn which-sub-board
  "Determine the sub-board based on a whole-board position"
  [x y]
  ((math/floor (/ x 3)), (math/floor (/ y 3))))

(defn sub-board
  "Get the sub-board at the given x/y"
  [board, x y]
  (let [[sub-board-x sub-board-y] (which-sub-board x y)
        left   (* sub-board-x 3)
        right  (+ left 3)
        top    (* sub-board-y 3)
        bottom (+ top 3)
        rows   (subvec top bottom board)
        cols   (map (partial subvec left right) rows)]
    (identity-set
      (flatten cols))))


(defn possibilities
  "Determine the possibilities for the given x/y"
  [board x y]
  (difference #{1 2 3 4 5 6 7 8 9}
    (union (row y) (column x) (sub-board x y))))

(possibilities 2 1)
