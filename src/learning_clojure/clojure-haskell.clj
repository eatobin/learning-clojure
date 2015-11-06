(ns learning-clojure.clojure-haskell)

(defn last-digit [n]
  (mod n 10))
(defn drop-last-digit [n]
  (quot n 10))
(defn to-rev-digits [n]
  (loop [x n
         a []]
    (if (<= x 0)
      a
      (recur (drop-last-digit x)
             (conj a (last-digit x))))))
;toRevDigits n
;  | (<=) n 0  = []
;  | otherwise = lastDigit n : toRevDigits (dropLastDigit n)
