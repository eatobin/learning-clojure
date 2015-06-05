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

(defn size [v]
  (if (empty? v)
    0
    (inc (size (rest v)))))
(size [1 2 3])

(defn size [v]
  (loop [l v, c 0]
    (if (empty? l)
      c
      (recur (rest l) (inc c)))))
