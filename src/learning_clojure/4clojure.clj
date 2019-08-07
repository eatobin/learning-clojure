(ns learning_clojure.4clojure)

;;; Elementary
;; 1
(= true
   true)
(= false
   false)

;; 2
(= (- 10 (* 2 3))
   4)

;; 3
(= "HELLO WORLD" (.toUpperCase "hello world"))

;; 4
(= (list :a :b :c) '(:a :b :c))

;; 5
(= '(1 2 3 4) (conj '(2 3 4) 1))
(= '(1 2 3 4) (conj '(3 4) 2 1))

;; 6
(= [:a :b :c]
   (list :a :b :c)
   (vec '(:a :b :c))
   (vector :a :b :c))

;; 7
(= [1 2 3 4] (conj [1 2 3] 4))
(= [1 2 3 4] (conj [1 2] 3 4))

;; 8
(= #{:a :b :c :d} (set '(:a :a :b :c :c :c :c :d :d)))
(= #{:a :b :c :d} (clojure.set/union #{:a :b :c} #{:b :c :d}))

;; 9
(= #{1 2 3 4} (conj #{1 4 3} 2))

;; 10
(= 20 ((hash-map :a 10, :b 20, :c 30) :b))
(= 20 (:b {:a 10, :b 20, :c 30}))

;; 11
(= {:a 1, :b 2, :c 3} (conj {:a 1} [:b 2] [:c 3]))

;; 12
(= 3 (first '(3 2 1)))
(= 3 (second [2 3 4]))
(= 3 (last (list 1 2 3)))

;; 13
(= (seq [20 30 40]) (rest [10 20 30 40]))

;; 14
(= 8
   ((fn add-five [x] (+ x 5)) 3))
(= 8
   ((fn [x] (+ x 5)) 3))
(= 8
   (#(+ % 5) 3))
(= 8
   ((partial + 5) 3))

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
(= ((fn [n] (str "Hello, " n "!")) "Dave") "Hello, Dave!")

;; 17
(= (seq '(6 7 8)) (map #(+ % 5) '(1 2 3)))

;; 18
(= (seq '(6 7)) (filter #(> % 5) '(3 4 5 6 7)))

;; 35
(= 7 (let [x 5] (+ 2 x)))
(= 7 (let [x 3, y 10] (- y x)))
(= 7 (let [x 21] (let [y 3] (/ x y))))

;; 36
(= 10 (let [_ 1
            y 3
            x 7] (+ x y)))
(= 4 (let [z 1
           y 3
           _ 7] (+ y z)))
(= 1 (let [z 1
           _ 3
           _ 7] z))

;; 37
(re-seq #"jam" "I like jam in my jam ")
(= "ABC" (apply str (re-seq #"[A-Z]+" "bA1B3Ce ")))

;; 57
(conj '(1 2 3) 4)
;; => (4 1 2 3)
(= '(5 4 3 2 1)
   ((fn foo [x]
      (when (> x 0)
        (conj (foo (dec x)) x)))
    5))

;; 64
(= 15 (reduce + [1 2 3 4 5]))
(= 0 (reduce + []))
(= 6 (reduce + 1 [2 3]))

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

;; 134
(true? ((fn [k coll]
          (if
           (and
            (not (nil? (find coll k)))
            (nil? (k coll)))
            true
            false)) :a {:a nil :b 2}))
(false? ((fn [k coll]
           (if
            (and
             (not (nil? (find coll k)))
             (nil? (k coll)))
             true
             false)) :b {:a nil :b 2}))
(false? ((fn [k coll]
           (if
            (and
             (not (nil? (find coll k)))
             (nil? (k coll)))
             true
             false)) :c {:a nil :b 2}))

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

;;; Easy
;; 19
(= ((fn [seq]
      (first (reverse seq))) [1 2 3 4 5]) 5)
(= ((fn [seq]
      (first (reverse seq))) '(5 4 3)) 3)
(= ((fn [seq]
      (first (reverse seq))) ["b" "c" "d"]) "d")

;; 20
(= ((fn [coll]
      (last (take 2 (reverse coll)))) (list 1 2 3 4 5)) 4)
(= ((fn [coll]
      (last (take 2 (reverse coll)))) ["a" "b" "c"]) "b")
(= ((fn [coll]
      (last (take 2 (reverse coll)))) [[1 2] [3 4]]) [1 2])
(fn [coll]
  (last (take 2 (reverse coll))))

;; 21
(= ((fn [coll n]
      (loop [coll coll
             n n
             acc 0]
        (if (= n acc)
          (first coll)
          (recur (rest coll) n (inc acc))))) '(4 5 6 7) 2) 6)
(= ((fn [coll n]
      (loop [coll coll
             n n
             acc 0]
        (if (= n acc)
          (first coll)
          (recur (rest coll) n (inc acc))))) [:a :b :c] 0) :a)
(= ((fn [coll n]
      (loop [coll coll
             n n
             acc 0]
        (if (= n acc)
          (first coll)
          (recur (rest coll) n (inc acc))))) [1 2 3 4] 1) 2)
(= ((fn [coll n]
      (loop [coll coll
             n n
             acc 0]
        (if (= n acc)
          (first coll)
          (recur (rest coll) n (inc acc))))) '([1 2] [3 4] [5 6]) 2) [5 6])

(fn [coll n]
  (loop [coll coll
         n n
         acc 0]
    (if (= n acc)
      (first coll)
      (recur (rest coll) n (inc acc)))))

;;22
(= ((fn [coll]
      (loop [coll coll
             acc 0]
        (if (empty? coll)
          acc
          (recur (rest coll) (inc acc)))))
    '(1 2 3 3 1))
   5)
(= ((fn [coll]
      (loop [coll coll
             acc 0]
        (if (empty? coll)
          acc
          (recur (rest coll) (inc acc))))) '(1 2 3 3 1)) 5)
(= ((fn [coll]
      (loop [coll coll
             acc 0]
        (if (empty? coll)
          acc
          (recur (rest coll) (inc acc))))) "Hello World") 11)
(= ((fn [coll]
      (loop [coll coll
             acc 0]
        (if (empty? coll)
          acc
          (recur (rest coll) (inc acc))))) [[1 2] [3 4] [5 6]]) 3)
(= ((fn [coll]
      (loop [coll coll
             acc 0]
        (if (empty? coll)
          acc
          (recur (rest coll) (inc acc))))) '(13)) 1)
(= ((fn [coll]
      (loop [coll coll
             acc 0]
        (if (empty? coll)
          acc
          (recur (rest coll) (inc acc))))) '(:a :b :c)) 3)

;;23
(= ((fn [coll]
      (loop [coll coll
             reversed (empty coll)]
        (if (empty? coll)
          reversed
          (recur (rest coll) (cons (first coll) reversed)))))
    [1 2 3 4 5])
   [5 4 3 2 1])
(= ((fn [coll]
      (loop [coll coll
             reversed (empty coll)]
        (if (empty? coll)
          reversed
          (recur (rest coll) (cons (first coll) reversed)))))
    (sorted-set 5 7 2 7))
   '(7 5 2))
(= ((fn [coll]
      (loop [coll coll
             reversed (empty coll)]
        (if (empty? coll)
          reversed
          (recur (rest coll) (cons (first coll) reversed)))))
    [[1 2] [3 4] [5 6]])
   [[5 6] [3 4] [1 2]])

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

;; 26
(def fib
  (map first
       (iterate
        (fn [[a b]]
          [b (+ a b)])
        [0 1])))
(take 10 fib)

(fn [n]
  (take n
        (map first
             (iterate
              (fn [[a b]]
                [b (+ a b)])
              [1 1]))))
(= ((fn [n]
      (take n
            (map first
                 (iterate
                  (fn [[a b]]
                    [b (+ a b)])
                  [1 1])))) 3)
   '(1 1 2))
(= ((fn [n]
      (take n
            (map first
                 (iterate
                  (fn [[a b]]
                    [b (+ a b)])
                  [1 1])))) 6)
   '(1 1 2 3 5 8))
(= ((fn [n]
      (take n
            (map first
                 (iterate
                  (fn [[a b]]
                    [b (+ a b)])
                  [1 1])))) 8)
   '(1 1 2 3 5 8 13 21))

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

;;28
(defn flat [x]
  (filter (complement sequential?)
          (rest (tree-seq sequential? seq x))))
(defn flater [l]
  (cond
    (empty? l) nil
    (not (seq? (first l))) (cons (first l) (flater (rest l)))
    true (concat (flater (first l)) (flater (rest l)))))

(= (#(filter (complement sequential?) (rest (tree-seq sequential? seq %)))
    '((1 2) 3 [4 [5 6]]))
   '(1 2 3 4 5 6))
(= (#(filter (complement sequential?) (rest (tree-seq sequential? seq %)))
    ["a" ["b"] "c"])
   '("a" "b" "c"))
(= (#(filter (complement sequential?) (rest (tree-seq sequential? seq %)))
    '((((:a)))))
   '(:a))

;; 29
(fn [s]
  (apply str
         (re-seq #"[A-Z]"
                 s)))
(= ((fn [s]
      (apply str
             (re-seq #"[A-Z]"
                     s))) "HeLlO, WoRlD!") "HLOWRD")
(empty? ((fn [s]
           (apply str
                  (re-seq #"[A-Z]"
                          s))) "nothing"))
(= ((fn [s]
      (apply str
             (re-seq #"[A-Z]"
                     s))) "$#A(*&987Zf") "AZ")

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

;; 33
(fn [coll n]
  (mapcat
   (fn [x]
     (repeat n x))
   coll))
(= ((fn [coll n]
      (mapcat
       (fn [x]
         (repeat n x))
       coll)) [1 2 3] 2)
   '(1 1 2 2 3 3))
(= ((fn [coll n]
      (mapcat
       (fn [x]
         (repeat n x))
       coll)) [:a :b] 4)
   '(:a :a :a :a :b :b :b :b))
(= ((fn [coll n]
      (mapcat
       (fn [x]
         (repeat n x))
       coll)) [4 5 6] 1)
   '(4 5 6))
(= ((fn [coll n]
      (mapcat
       (fn [x]
         (repeat n x))
       coll)) [[1 2] [3 4]] 2)
   '([1 2] [1 2] [3 4] [3 4]))
(= ((fn [coll n]
      (mapcat
       (fn [x]
         (repeat n x))
       coll)) [44 33] 2)
   [44 44 33 33])

;;34
(= ((fn [x y]
      (loop [x x
             y y
             rng '()]
        (if (= x y)
          (reverse rng)
          (recur (inc x) y (cons x rng))))) 1 4) '(1 2 3))
(= ((fn [x y]
      (loop [x x
             y y
             rng '()]
        (if (= x y)
          (reverse rng)
          (recur (inc x) y (cons x rng))))) -2 2) '(-2 -1 0 1))
(= ((fn [x y]
      (loop [x x
             y y
             rng '()]
        (if (= x y)
          (reverse rng)
          (recur (inc x) y (cons x rng))))) 5 8) '(5 6 7))
;;38
(= ((fn [& m] (reduce #(if (> %1 %2) %1 %2) m)) 1 8 3 4) 8)
(= ((fn [& m] (reduce #(if (> %1 %2) %1 %2) m)) 30 20) 30)
(= ((fn [& m] (reduce #(if (> %1 %2) %1 %2) m)) 45 67 11) 67)

;;39
(= ((fn [col1 col2]
      (loop [col1 col1
             col2 col2
             combo (empty col1)]
        (if (or (empty? col1) (empty? col2))
          (reverse combo)
          (recur (rest col1)
                 (rest col2)
                 (cons (first col2)
                       (cons (first col1) combo))))))
    [1 2 3] [:a :b :c])
   '(1 :a 2 :b 3 :c))
(= ((fn [col1 col2]
      (loop [col1 col1
             col2 col2
             combo (empty col1)]
        (if (or (empty? col1) (empty? col2))
          (reverse combo)
          (recur (rest col1)
                 (rest col2)
                 (cons (first col2)
                       (cons (first col1) combo))))))
    [1 2] [3 4 5 6])
   '(1 3 2 4))
(= ((fn [col1 col2]
      (loop [col1 col1
             col2 col2
             combo (empty col1)]
        (if (or (empty? col1) (empty? col2))
          (reverse combo)
          (recur (rest col1)
                 (rest col2)
                 (cons (first col2)
                       (cons (first col1) combo))))))
    [30 20] [25 15])
   [30 25 20 15])

;;40
(= ((fn [v coll]
      (loop [v v
             coll coll
             coll2 (empty coll)]
        (cond
          (empty? coll) (butlast (reverse coll2))
          true (recur v (rest coll) (cons v (cons (first coll) coll2))))))
    0 [1 2 3]) [1 0 2 0 3])
(= (apply str ((fn [v coll]
                 (loop [v v
                        coll coll
                        coll2 (empty coll)]
                   (cond
                     (empty? coll) (butlast (reverse coll2))
                     true (recur v (rest coll) (cons v (cons (first coll) coll2))))))
               ", " ["one" "two" "three"])) "one, two, three")
(= ((fn [v coll]
      (loop [v v
             coll coll
             coll2 (empty coll)]
        (cond
          (empty? coll) (butlast (reverse coll2))
          true (recur v (rest coll) (cons v (cons (first coll) coll2))))))
    :z [:a :b :c :d]) [:a :z :b :z :c :z :d])

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

;; 42
(fn [n]
  (reduce * (range 1 (inc n))))
(= ((fn [n]
      (reduce * (range 1 (inc n)))) 1) 1)
(= ((fn [n]
      (reduce * (range 1 (inc n)))) 3) 6)
(= ((fn [n]
      (reduce * (range 1 (inc n)))) 5) 120)
(= ((fn [n]
      (reduce * (range 1 (inc n)))) 8) 40320)

;; 45
(= '(1 4 7 10 13)
   (take 5 (iterate #(+ 3 %) 1)))

;;47

;; 48
(= 6 (some #{2 7 6} [5 6 7 8]))
(= 6 (some #(when (even? %) %) [5 6 7 8]))

;;49

;; 51
(let [[a b] ["cat" "dog" "bird" "fish"]]
  [a b])
;; -> ["cat" "dog"]
(let [[& c] ["cat" "dog" "bird" "fish"]]
  c)
;; -> ("cat" "dog" "bird" "fish")
;The other type of destructuring technique is to use the :as keyword.
; This takes the whole destructuring argument and binds it to a name:
(let [[:as x] ["cat" "dog" "bird" "fish"]]
  x)
;; -> ["cat" "dog" "bird" "fish"]
(= [1 2 [3 4 5] [1 2 3 4 5]]
   (let [[a b & c :as d] [1 2 3 4 5]]
     [a b c d]))

;;52
;;61
;;62
;;66
;;81
;;83
;;86
