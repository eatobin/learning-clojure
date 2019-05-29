(ns learning-clojure.schemer.ch-1)

(def atom?
  (fn [a]
    (not (seq? a))))

(atom? 42)
(atom? '(42))
