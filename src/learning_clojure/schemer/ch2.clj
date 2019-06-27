(ns learning-clojure.schemer.ch2
  (:require [learning-clojure.schemer.ch1 :refer :all]))

(def lat?
  (fn [l]
    (cond
      (null? l) true
      (and (seq? l)
           (atom? (first l))) (lat? (rest l))
      true false)))

(def member?
  (fn [a lat]
    (cond
      (null? lat) false
      true (or
            (= (first lat) a)
            (member? a (rest lat))))))
