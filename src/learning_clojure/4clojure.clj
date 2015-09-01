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

;; to 72 pg 175

;; 51
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
