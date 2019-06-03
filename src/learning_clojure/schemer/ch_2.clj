(ns learning-clojure.schemer.ch-2
  (:require [learning-clojure.schemer.ch-1 :as ch1]))

(def lat?
  (fn [l]
    (cond
      (ch1/null? l) true
      (and (seq? l)
           (ch1/atom? (first l)))
      (lat? (rest l))
      true false)))

(def member?
  (fn [a lat]
    (cond
      (ch1/null? lat) false
      true (or
             (= (first lat) a)
             (member? a (rest lat))))))
