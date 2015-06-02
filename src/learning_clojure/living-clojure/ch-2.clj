(class true)
(true? true)
(true? false)
(true? nil)
(false? false)
(false? true)
(false? nil)
(nil? nil)
(nil? 1)
(not true)
(not 1)
(not "hi")
(not false)
(not nil)

(= :drinkme :drinkme)
(= :drinkme 4)

(= '(:drinkme :bottle) [:drinkme :bottle])

;; There also is a not= expression that is a shortcut for doing (not (= x y)) :
(not= :drinkme :4)

(empty? [:table :door :key])
(empty? [])
(empty? {})
(empty? '())

(seq [1 2 3])
(class [1 2 3])
(class (seq [1 2 3]))

;; use this to check for not empty
(seq [])

(every? odd? [1 3 5])
(every? odd? [1 2 3 4 5])

(defn drinkable? [x]
  (= x :drinkme))
(drinkable? :drinkme)
(drinkable? :poison)
(every? drinkable? [:drinkme :drinkme])
(every? drinkable? [:drinkme :poison])
fn [x] (= x :drinkme)
(every? (fn [x] (= x :drinkme) :toast) [:drinkme :drinkme])
(every? #(= % :drinkme) [:drinkme :drinkme])
(not-any? #(= % :drinkme) [:drinkme :poison])
(not-any? #(= % :drinkme) [:poison :poison])

(some #(> % 3) [1 2 3 4 5])
(#{1 2 3 4 5} 3)
(#{1 2 3 4 5} 9)                                            ;; nil
(some #{3} [1 2 3 4 5])
(some #{4 5} [1 2 3 4 5])
(some #{8 9} [1 2 3 4 5])                                   ;; nil
(some #{nil} [nil nil nil])
(some #{false} [false false false])

(if true "it is true" "it is false")
;; -> "it is true"
(if false "it is true" "it is false")
;; -> "it is false"
(if nil "it is true" "it is false")
;; -> "it is false"

(if (= :drinkme :drinkme)
  "Try it"
  "Don't try it")
;; -> "Try it"

(if-let [result "I'll go to a park."]
  result
  "I'll go to a cafe.")                                     ;; "I'll go to a park."

(if-let [result nil]
  result
  "I'll go to a cafe.")
;; "I'll go to a cafe."

(if (= 1 1)
  100
  nil)
;; 100
(if-let [result (if (= 1 1)
                  100
                  nil)]
  result
  "I'll go to a cafe.")
;; 100
(if-let [result (if (not= 1 1) 100 nil)]
  result
  "I'll go to a cafe.")
;; "I'll go to a cafe."

(defn drink [need-to-grow-small]
  (when need-to-grow-small "drink bottle"))

(drink true)
;; -> "drink bottle"
(drink false)
;; -> nil

(when-let [x true] "It's true!")
;; "It's true!"
(when-let [x false] "It's true!")
;; nil

(when-let [x "It's true!!!"] x)
;; "It's true!!!"

(when-let [x false] x)
;; nil

(when-let [result (if (= 1 1)
                    100)]
  result)
;; 100
(when-let [result (if (not= 1 1)
                    100)]
  result)
;; nil
(when-let [result (if (not= 1 1)
                    100
                    500)]
  result)
;; 500
(when-let [result (if (= 1 1)
                    100
                    500)]
  result)
;; 100

(let [bottle "drinkme"]
  (cond
    (= bottle "poison") "don't touch"
    (= bottle "drinkme") "grow smaller"
    (= bottle "empty") "all gone"))
;; -> "grow smaller"

(let [x 5]
  (cond
    (> x 10) "bigger than 10"
    (> x 4) "bigger than 4"
    (> x 3) "bigger than 3"))
;; -> "bigger than 4"

(let [x 5]
  (cond
    (> x 3) "bigger than 3"
    (> x 10) "bigger than 10"
    (> x 4) "bigger than 4"))
;; -> "bigger than 3"

(let [x 1]
  (cond
    (> x 10) "bigger than 10"
    (> x 4) "bigger than 4"
    (> x 3) "bigger than 3"))
;; nil

(let [bottle "mystery"]
  (cond
    (= bottle "poison") "don't touch"
    (= bottle "drinkme") "grow smaller"
    (= bottle "empty") "all gone"
    :else "unknown"))
;; -> "unknown"

(let [bottle "drinkme"]
  (case bottle
    "poison" "don't touch"
    "drinkme" "grow smaller"
    "empty" "all gone"))
;; "grow smaller"

(let [bottle "mystery"]
  (case bottle
    "poison" "don't touch"
    "drinkme" "grow smaller"
    "empty" "all gone"))
;; -> IllegalArgumentException No matching clause: mystery

(let [bottle "mystery"]
  (case bottle
    "poison" "don't touch"
    "drinkme" "grow smaller"
    "empty" "all gone"
    "unknown"))
;; -> "unknown"

(defn grow [name direction]
  (if (= direction :small)
    (str name " is growing smaller")
    (str name " is growing bigger")))
;; -> #'user/grow
(grow "Alice" :small)
;; -> "Alice is growing smaller"
(grow "Alice" :big)
;; -> "Alice is growing bigger"
(partial grow "Alice")
;; -> #<core$partial$fn__4228 clojure.core$partial$fn__4228@1759817d>
((partial grow "Alice") :small)
;; -> "Alice is growing smaller"

(defn toggle-grow [direction]
  (if (= direction :small) :big :small))
;; -> #'user/toggle-grow
(toggle-grow :big)
;; -> :small
(toggle-grow :small)
;; -> :big
(defn oh-my [direction]
  (str "Oh My! You are growing " direction))
;; -> #'user/oh-my
(oh-my (toggle-grow :small))
;; -> "Oh My! You are growing :big"
;; Or, we could use comp to create a function that was the composition of the two:
(defn surprise [direction]
  ((comp oh-my toggle-grow) direction))
(surprise :small)
;; -> "Oh My! You are growing :big"

(defn adder [x y]
  (+ x y))
;; -> #'user/adder
(adder 3 4)
;; -> 7
(def adder-5 (partial adder 5))
;; -> #'user/adder-5
(adder-5 10)
;; -> 15

(let [[color size] ["blue" "small"]]
  (str "The " color " door is " size))
;; -> "The blue door is small"


;; page 38



(let [[color size :as original] ["blue" "small"]]
  {:color color :size size :original original})

(let [[color [size] :as original] ["blue" ["small"]]]
  {:color color :size size :original original})

(let [{myFlower :flower1 yourFlower :flower2}
      {:flower1 "red" :flower2 "blue"}]
  (str "The flowers are " myFlower " and " yourFlower))
