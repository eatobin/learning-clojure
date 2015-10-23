(ns learning-clojure.4clojure)

;; 6
(= [:a :b :c]
   (list :a :b :c)
   (vec '(:a :b :c))
   (vector :a :b :c))

;; 14
(= 8 ((fn add-five [x] (+ x 5)) 3))
(= 8 ((fn [x] (+ x 5)) 3))
(= 8 (#(+ % 5) 3))
(= 8 ((partial + 5) 3))

;; 15
(= ((fn [n] (* n 2)) 2) 4)
(= ((fn me [x] (* x 2)) 3) 6)
(= (#(* % 2) 11) 22)
(= ((partial * 2) 7) 14)

;; 16
(defn add-name [name] (str "Hello, " name "!"))
(= (add-name "Dave") "Hello, Dave!")
(= (add-name "Jenn") "Hello, Jenn!")
(= (add-name "Rhea") "Hello, Rhea!")
(= (#(str "Hello, " % "!") "Dave") "Hello, Dave!")

;; 17
(= '(6 7 8) (map #(+ % 5) '(1 2 3)))

;; 35
(= 7 (let [x 5] (+ 2 x)))
(= 7 (let [x 3, y 10] (- y x)))
(= 7 (let [x 21] (let [y 3] (/ x y))))

;; 36
(= 10 (let [x 7 y 3] (+ x y)))
(= 4 (let [y 3 z 1] (+ y z)))
(= 1 (let [z 1] z))

;; End day 2

;; 37
(re-seq #"jam" "I like jam in my jam ")
(= "ABC" (apply str (re-seq #"[A-Z]+" "bA1B3Ce ")))

;; 57
(conj '(1 2 3) 4)
;; => (4 1 2 3)
(= '(5 4 3 2 1) ((fn foo [x] (when (> x 0) (conj (foo (dec x)) x))) 5))

;; 68
;; This results in a VECTOR - not a list! conj is at END for vector! (#57)
(= [7 6 5 4 3]
   (loop [x 5
          result []]
     (if (> x 0)
       (recur (dec x) (conj result (+ 2 x)))
       result)))

;; 71
(-> [:cat :dog :fish] first str .toUpperCase)
;; -> ":CAT"
(= (last (sort (rest (reverse [2 5 4 1 3 6]))))
   (-> [2 5 4 1 3 6] (reverse) (rest) (sort) (last))
   5)

;; 72
(->> [1 2 3 4 5 6 7 8] (filter even?) (take 3))
;; -> (2 4 6)

(= (reduce + (map inc (take 3 (drop 2 [2 5 4 1 3 6]))))
   (->> [2 5 4 1 3 6] (drop 2) (take 3) (map inc) (reduce +))
   11)

;; 145
(= '(1 5 9 13 17 21 25 29 33 37) (for [x (range 40)
                                       :when (= 1 (rem x 4))]
                                   x))
(= '(1 5 9 13 17 21 25 29 33 37) (for [x (iterate #(+ 4 %) 0)
                                       :let [z (inc x)]
                                       :while (< z 40)]
                                   z))
(= '(1 5 9 13 17 21 25 29 33 37) (for [[x y] (partition 2 (range 20))]
                                   (+ x y)))

;; 20
(= ((fn [coll]
      (last (take 2 (reverse coll)))) (list 1 2 3 4 5)) 4)
(= ((fn [coll]
      (last (take 2 (reverse coll)))) ["a" "b" "c"]) "b")
(= ((fn [coll]
      (last (take 2 (reverse coll)))) [[1 2] [3 4]]) [1 2])
(fn [coll]
  (last (take 2 (reverse coll))))

;; 24
(fn [coll]
  (reduce + coll))
(= ((fn [coll]
      (reduce + coll)) [1 2 3]) 6)
(= ((fn [coll]
      (reduce + coll)) (list 0 -2 5 5)) 8)
(= ((fn [coll]
      (reduce + coll)) #{4 2 1}) 7)
(= ((fn [coll]
      (reduce + coll)) '(0 0 -1)) -1)
(= ((fn [coll]
      (reduce + coll)) '(1 10 3)) 14)

;; 25
(fn [coll]
  (filter odd? coll))
(= ((fn [coll]
      (filter odd? coll)) #{1 2 3 4 5})
   '(1 3 5))
(= ((fn [coll]
      (filter odd? coll)) [4 2 1 6])
   '(1))
(= ((fn [coll]
      (filter odd? coll)) [2 2 4 6])
   '())
(= ((fn [coll]
      (filter odd? coll)) [1 1 1 3])
   '(1 1 1 3))

;; 27
(fn [coll]
  (= (seq coll)
     (reverse (seq coll))))
#(= (seq %)
    (reverse (seq %)))
(false? (#(= (seq %)
             (reverse (seq %)))
          '(1 2 3 4 5)))
(true? ((fn [coll]
          (= (seq coll)
             (reverse (seq coll))))
         "racecar"))
(true? ((fn [coll]
          (= (seq coll)
             (reverse (seq coll))))
         [:foo :bar :foo]))
(true? ((fn [coll]
          (= (seq coll)
             (reverse (seq coll))))
         '(1 1 3 3 1 1)))
(false? ((fn [coll]
           (= (seq coll)
              (reverse (seq coll))))
          '(:a :b :c)))

;; 32
(fn [coll]
  (mapcat #(repeat 2 %)
          coll))
(= ((fn [coll]
      (mapcat #(repeat 2 %)
              coll)) [1 2 3])
   '(1 1 2 2 3 3))
(= ((fn [coll]
      (mapcat #(repeat 2 %)
              coll)) [:a :a :b :b])
   '(:a :a :a :a :b :b :b :b))
(= ((fn [coll]
      (mapcat #(repeat 2 %)
              coll)) [[1 2] [3 4]])
   '([1 2] [1 2] [3 4] [3 4]))

;; 30
#(map first (partition-by identity %))
(= (apply str (#(map first (partition-by identity %))
                "Leeeeeerrroyyy"))
   "Leroy")
(= ((fn [coll]
      (map first (partition-by identity coll)))
     [1 1 2 3 3 2 2 3])
   '(1 2 3 2 3))
(= ((fn [coll]
      (map first (partition-by identity coll)))
     [[1 2] [1 2] [3 4] [1 2]])
   '([1 2] [3 4] [1 2]))

;; 31
(fn [coll]
  (seq (partition-by identity coll)))
(= ((fn [coll]
      (seq (partition-by identity coll))) [1 1 2 1 1 1 3 3])
   '((1 1) (2) (1 1 1) (3 3)))
(= (#(seq (partition-by identity %)) [:a :a :b :b :c])
   '((:a :a) (:b :b) (:c)))
(= (#(seq (partition-by identity %)) [[1 2] [1 2] [3 4]])
   '(([1 2] [1 2]) ([3 4])))

;; 41
(fn [coll n]
  (keep-indexed
    #(when (not= 0
                 (mod (inc %1) n))
      %2)
    coll))
(fn [coll n]
  (keep-indexed
    (fn [index value]
      (when (not= 0
                  (mod (inc index) n))
        value))
    coll))
(= ((fn [coll n]
      (keep-indexed
        #(when (not= 0
                     (mod (inc %1) n))
          %2)
        coll))
     [1 2 3 4 5 6 7 8]
     3)
   [1 2 4 5 7 8])
(= ((fn [coll n]
      (keep-indexed
        (fn [index value]
          (when (not= 0
                      (mod (inc index) n))
            value))
        coll))
     [:a :b :c :d :e :f]
     2)
   [:a :c :e])
(= ((fn [coll n]
      (keep-indexed
        #(when (not= 0
                     (mod (inc %1) n))
          %2)
        coll))
     [1 2 3 4 5 6]
     4)
   [1 2 3 5 6])

;; to 45 pg 178

;; 51 - last finished - pg 181
(let [[a b] ["cat" "dog" "bird" "fish"]]
  [a b])
;; -> ["cat" "dog"]
(let [[& c] ["cat" "dog" "bird" "fish"]]
  c)
;; -> ("bird" "fish")
;The other type of destructuring technique is to use the :as keyword.
; This takes the whole destructuring argument and binds it to a name:
(let [[:as x] ["cat" "dog" "bird" "fish"]]
  x)
;; -> ["cat" "dog" "bird" "fish"]
(= [1 2 [3 4 5] [1 2 3 4 5]] (let [[a b & c :as d] [1 2 3 4 5]] [a b c d]))
