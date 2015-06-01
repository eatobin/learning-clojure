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
(if-let [result (if (= 1 1) 100 nil)]
  result
  "I'll go to a cafe.")
;; 100
(if-let [result (if (not= 1 1) 100 nil)]
  result
  "I'll go to a cafe.")
;; "I'll go to a cafe."




(let [[color size] ["blue" "small"]]
  (str "The " color " door is " size))

(let [[color size :as original] ["blue" "small"]]
  {:color color :size size :original original})

(let [[color [size] :as original] ["blue" ["small"]]]
  {:color color :size size :original original})

(let [{myFlower :flower1 yourFlower :flower2}
      {:flower1 "red" :flower2 "blue"}]
  (str "The flowers are " myFlower " and " yourFlower))
