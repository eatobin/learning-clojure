(ns learning-clojure.schemer.ch1)

(def atom?
  (fn [a]
    (not (seq? a))))

(def null?
  (fn [a]
    (or
     (nil? a)
     (= () a))))

(atom? 42)
(atom? '(42))

(null? 88)
(null? nil)
(null? '())
