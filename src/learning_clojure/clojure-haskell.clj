(ns learning-clojure.clojure-haskell)

(defn last-digit [n]
  (mod n 10))
(defn drop-last-digit [n]
  (quot n 10))
;(loop [x 10]
;  (when (> x 1)
;    (println x)
;    (recur (- x 2))))
;(defn to-rev-digits [n]
;  (loop [n]
;    (if (> n 0)
;      (cons (last-digit n)
;             (recur (drop-last-digit n))))))
;toRevDigits n
;  | (<=) n 0  = []
;  | otherwise = lastDigit n : toRevDigits (dropLastDigit n)
