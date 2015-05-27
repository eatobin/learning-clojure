(def t0 (System/currentTimeMillis))
(defn t1 [] (System/currentTimeMillis))
(t1)
t0
t0
(t1)

(def xx 1)
(println xx)

(defn const-fun1 [_] 1)
(defn ident-fun [x] x)
(defn list-maker-fun [x f]
  (map (fn [z] (let [w z]
                 (list (f w) w)
                 )) x))
(const-fun1 "hi")
(ident-fun "hi")
(list-maker-fun ["a" "b"] const-fun1)
(list-maker-fun ["a" "b"] ident-fun)

(defn list-maker-fun2 [x f] (map (fn [z] (let [w z] (list w (f w)))) x))
(list-maker-fun2 ["x" "y" "z"] const-fun1)
(list-maker-fun2 ["a" "b"] ident-fun)
(list 1 2 3)

(def aa 70)
(def vv 88)
(println (+ vv aa))

(let [j 58] (/ j 2))

(def x (/ 12 3))

(let [x 1
      y x]
  y)

(let [x 1 y x] y)

(let [h 20 r (/ h 4)] r)

(defn make-adder [x]
  (let [y x]
    (fn [z] (+ y z))))
(def add2 (make-adder 2))
(add2 24)

(def people ["Lea", "Han Solo"])
(count "Lea")
(map count people)
(defn twice-count [w]
  (* 2 (count w)))
(twice-count "Lando")
(map twice-count people)
(map (fn [w] (* 2 (count w))) people)
((fn [w] (* 2 (count w))) "eric")
(map #(* 2 (count %)) people)

(def v [3 11 2])
(apply + v)
(apply max v)
(apply * v)
(filter odd? v)
(filter #(< % 6) v)
(filter (fn [j] (< j 3)) v)

(defn big [st n] (if (> (count st) n) (println "True it is.")))
(defn big2 [st n]
  (if (> (count st) n)
    (println "True it is.")))
(big "gggg" 4) ;; prints nothing
(big2 "gggg" 3)

(defn factorial [x]
  (if (zero? x)
    1
    (* x (factorial (dec x)))))
(factorial 12)
