(ns learning-clojure.schemer.ch4
  (:require [learning-clojure.schemer.ch1 :refer :all]
            [learning-clojure.schemer.ch2 :refer :all]
            [learning-clojure.schemer.ch3 :refer :all]))

;; 4 clojure
(defn flater [l]
  (cond
    (null? l) nil
    (atom? (first l)) (cons (first l) (flater (rest l)))
    true (concat (flater (first l)) (flater (rest l)))))

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
      (zero_? n) false
      (zero_? m) true
      true (o> (sub1 n) (sub1 m)))))

(def o<
  (fn [n m]
    (cond
      (zero_? m) false
      (zero_? n) true
      true (o< (sub1 n) (sub1 m)))))

(defn o=1 [n m]
  (cond
    (zero_? m) (zero_? n)
    (zero_? n) false
    true (o=1 (sub1 n) (sub1 m))))

(defn o=2 [n m]
  (cond
    (o> n m) false
    (o< n m) false
    true true))

(defn my-exp [n m]
  (cond
    (zero_? m) 1
    true (ox n (my-exp n (sub1 m)))))

(def my-div
  (fn [n m]
    (cond
      (< n m) 0
      true (add1 (my-div (o- n m) m)))))

(def my-length
  (fn [lat]
    (cond
      (null? lat) 0
      true (add1 (my-length (rest lat))))))
