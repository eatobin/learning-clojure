(ns learning_clojure.well_grounded)

(defn const-fun1 [_] 1)
(defn ident-fun [x] x)
(defn list-maker-fun [x f]
  (map (fn [z]
         (let [w z]
           (list (f w) w)))
       x))
(const-fun1 "hi")
(ident-fun "hi")
(list-maker-fun ["a" "b"] const-fun1)
(list-maker-fun ["a" "b"] ident-fun)

(defn make-adder [x]
  (let [y x]
    (fn [z]
      (+ y z))))
(def add2 (make-adder 2))
(add2 24)

(defn make-adder2 [a]
  (fn [z]
    (+ a z)))

(def add3 (make-adder2 3))
(add3 3)
(add3 24)
