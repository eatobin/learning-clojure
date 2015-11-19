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

;toRevDigits :: Integer -> [Integer]
;toRevDigits n
;| (<=) n 0  = []
;| otherwise = (:) (lastDigit n) (toRevDigits (dropLastDigit n))

(defn double-every-other [coll]
  (loop [[x y & zs :as w] coll
         a []]
    (if (empty? w)
      a
      (if (= (count w) 1)
        (conj a x)
        (recur (into [] zs)
               (conj a x (* 2 y)))))))

;doubleEveryOther :: [Integer] -> [Integer]
;doubleEveryOther []       = []
;doubleEveryOther (x:[])   = [x]
;doubleEveryOther (x:y:zs) = x : (y * 2) : doubleEveryOther zs

(defn sum-digits [coll]
  (loop [[x & xs :as w] coll
         a 0]
    (if (empty? w)
      a
      (recur (into [] xs)
             (+ (+ a (drop-last-digit x)) (last-digit x))))))

;sumDigits :: [Integer] -> Integer
;sumDigits [] = 0
;sumDigits (x:xs) = (dropLastDigit x) + (lastDigit x) + sumDigits xs

(defn luhn [n]
  (= (mod (sum-digits (double-every-other (to-rev-digits n)))
          10)
     0))

;luhn :: Integer -> Bool
;luhn n = (mod (sumDigits (doubleEveryOther (toRevDigits n))) 10) == 0
;-- 4662110665499438 True
;-- 645937 True
;-- 1859 True

(defn hanoi [n p1 p2 p3]
  (if (= n 1)
    (println (format "Move from %s to %s" p1 p3))
    (do
      (hanoi (dec n) p1 p3 p2)
      (println (format "Move fromX %s to %s" p1 p3))
      (recur (dec n) p2 p1 p3))))

;(defn hanoi [n p1 p2 p3]
;  (loop [ln n lp1 p1 lp2 p2 lp3 p3
;         a []]
;    (if (= ln 0)
;      a
;      (hanoi (dec n) p1 p3 p2
;             (recur 1 p1 p2 p3
;                    (when (= n 1)
;                      (conj a (list p1 p3))))))))

;type Peg = String
;type Move = (Peg, Peg)

;hanoi :: Integer -> Peg -> Peg -> Peg -> [Move]
;hanoi 1 p1 _ p3 = [(p1, p3)]
;hanoi n p1 p2 p3 = (hanoi (n - 1) p1 p3 p2) ++ (hanoi 1 p1 p2 p3) ++ (hanoi (n - 1) p2 p1 p3)
