(ns learning-clojure.schemer.ch3
  (:require [learning-clojure.schemer.ch1 :refer :all]
            [learning-clojure.schemer.ch2 :refer :all]))

(def rember
  (fn [a lat]
    (cond
      (null? lat) '()
      true (cond
             (= (first lat) a) (rest lat)
             true (cons (first lat)
                        (rember
                         a (rest lat)))))))

(def firsts
  (fn [l]
    (cond
      (null? l) '()
      true (cons (first (first l))
                 (firsts (rest l))))))

(def insertR
  (fn [new old lat]
    (cond
      (null? lat) '()
      true (cond
             (= (first lat) old) (cons (first lat) (cons new (rest lat)))
             true (cons (first lat) (insertR new old (rest lat)))))))

(def insertL
  (fn [new old lat]
    (cond
      (null? lat) '()
      true (cond
             (= (first lat) old) (cons new lat)
             true (cons (first lat) (insertL new old (rest lat)))))))
