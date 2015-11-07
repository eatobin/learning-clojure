(ns learning-clojure.clojure-haskell)

(defn last-digit [n]
  (mod n 10))

;lastDigit :: Integer -> Integer
;lastDigit n = (mod) n 10

(defn drop-last-digit [n]
  (quot n 10))

;dropLastDigit :: Integer -> Integer
;dropLastDigit n = (div) n 10

(defn to-rev-digits [n]
  (loop [x n
         a []]
    (if (<= x 0)
      a
      (recur (drop-last-digit x)
             (conj a (last-digit x))))))
             
(defn double-every-other [coll]
  (loop [[x y & zs :as w] coll
          a []]
    (if (empty? w)
      a
      (recur (zs)
             (conj a x (* 2 y))))))

;toRevDigits :: Integer -> [Integer]
;toRevDigits n
;| (<=) n 0  = []
;| otherwise = (:) (lastDigit n) (toRevDigits (dropLastDigit n))
