(ns learning-clojure.4clojure)

;; 51

(let [[a b & c] ["cat" "dog" "bird" "fish"]]
  [a b])
;; -> ["cat" "dog"]

(let [[a b & c] ["cat" "dog" "bird" "fish"]]
  c)
;; -> ("bird" "fish")

;The other type of destructuring technique is to use the :as keyword.
; This takes the whole destructuring argument and binds it to a name:

(let [[a b :as x] ["cat" "dog" "bird" "fish"]]
  x)
;; -> ["cat" "dog" "bird" "fish"]

(= [1 2 [3 4 5] [1 2 3 4 5]] (let [[a b & c :as d] [1 2 3 4 5]] [a b c d]))
