(ns learning-clojure.schemer.ch4
  (:require [learning-clojure.schemer.ch1 :refer :all]
            [learning-clojure.schemer.ch2 :refer :all]
            [learning-clojure.schemer.ch3 :refer :all]))

(def zero_?
  (fn [n]
    (= 0 n)))

(def add1
  (fn [n]
    (+ 1 n)))

(def sub1
  (fn [n]
    (- n 1)))

(def o+
  (fn [n m]
    (cond
      (zero_? m) n
      true (add1 (o+ n (sub1 m))))))

(def o-
  (fn [n m]
    (cond
      (zero_? m) n
      true (sub1 (o- n (sub1 m))))))

(def addtup
  (fn [tup]
    (cond
      (null? tup) 0
      true (o+ (first tup) (addtup (rest tup))))))

(def ox
  (fn [n m]
    (cond
      (zero_? m) 0
      true (o+ n (ox n (sub1 m))))))

(def tup+
  (fn [tup1 tup2]
    (cond
      (null? tup1) tup2
      (null? tup2) tup1
      true (cons (o+ (first tup1) (first tup2))
                 (tup+ (rest tup1) (rest tup2))))))

(def o>
  (fn [n m]
    (cond
      (zero_? m) true
      (zero_? n) false
      true (o> (sub1 n) (sub1 m)))))

(defn flater [l]
  (cond
    (null? l) nil
    (atom? (first l)) (cons (first l) (flater (rest l)))
    true (concat (flater (first l)) (flater (rest l)))))
