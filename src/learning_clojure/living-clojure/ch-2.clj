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
;; page 31

















(let [[color size] ["blue" "small"]]
  (str "The " color " door is " size))

(let [[color size :as original] ["blue" "small"]]
  {:color color :size size :original original})

(let [[color [size] :as original] ["blue" ["small"]]]
  {:color color :size size :original original})

(let [{myFlower :flower1 yourFlower :flower2}
      {:flower1 "red" :flower2 "blue"}]
  (str "The flowers are " myFlower " and " yourFlower))
