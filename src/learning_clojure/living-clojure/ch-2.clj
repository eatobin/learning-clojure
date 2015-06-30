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

(when-let [_ true] "It's true!")
;; "It's true!"
(when-let [_ false] "It's true!")
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

(let [[color [size]] ["blue" ["very small"]]]
  (str "The " color " door is " size))
;; -> "The blue door is very small"

(let [[color size] ["blue" "very small"]]
  (str "The " color " door is " size))
;; -> "The blue door is very small"

(let [[color size :as original] ["blue" "small"]]
  {:color color :size size :original original})
;; {:color "blue", :size "small", :original ["blue" "small"]}

(let [[color [size] :as original] ["blue" ["small"]]]
  {:color color :size size :original original})
;; -> {:color "blue", :size "small", :original ["blue" ["small"]]}

(let [{myFlower :flower1 yourFlower :flower2}
      {:flower1 "red" :flower2 "blue"}]
  (str "The flowers are " myFlower " and " yourFlower))
;; "The flowers are red and blue"

(let [{flower1 :flower1 flower2 :flower2 :or {flower2 "missing"}}
      {:flower1 "red"}]
  (str "The flowers are " flower1 " and " flower2))
;; -> "The flowers are red and missing"
;; To keep the whole initial data structure as a binding, :as works in maps too:
(let [{flower1 :flower1 :as all-flowers}
      {:flower1 "red"}]
  [flower1 all-flowers])
;; -> ["red" {:flower1 "red"}]

(defn flower-colors [{:keys [flower1 flower2]}]
  (str "The flowers are " flower1 " and " flower2))
(flower-colors {:flower1 "red" :flower2 "blue"})
;; -> "The flowers are red and blue"

(take 5 (range))
;; -> (0 1 2 3 4)

(take 10 (range))
;; -> (0 1 2 3 4 5 6 7 8 9)

(count (take 1000 (range)))
;; -> 1000

(repeat 3 "rabbit")
;; -> ("rabbit" "rabbit" "rabbit")

(take 5 (repeat "rabbit"))
;; -> ("rabbit" "rabbit" "rabbit" "rabbit" "rabbit")

(rand-int 10)
;; -> 3

(repeat 5 (rand-int 10))
;; -> (7 7 7 7 7)

#(rand-int 10)
;; -> #<user$eval721$fn__722 user$eval721$fn__722@308092db>
(#(rand-int 10))
;; -> 3

(repeatedly 5 #(rand-int 10))
;; -> (1 5 8 4 3)

;; Dice:
(+ 1 (rand-int 6))
(repeatedly 50 #(+ 1 (rand-int 6)))
(sort (repeatedly 50 #(+ 1 (rand-int 6))))

(def adjs ["normal"
           "too small"
           "too big"
           "swimming"])
(defn alice-is [in out]
  (if (empty? in)
    out
    (alice-is
      (rest in)
      (conj out
            (str "Alice is " (first in))))))
(alice-is adjs [])
;; -> ["Alice is normal" "Alice is too small" "Alice is too big" "Alice is swimming"]

(defn alice-is2 [input]
  (loop [in input
         out []]
    (if (empty? in)
      out
      (recur (rest in)
             (conj out
                   (str "Alice is " (first in)))))))
(alice-is2 adjs)
;; -> ["Alice is normal" "Alice is too small" "Alice is too big" "Alice is swimming"]

(defn countdown [n]
  (if (= n 0)
    n
    (countdown (- n 1))))
(countdown 3)
;; -> 0
(countdown 100000)
;; -> StackOverflowError

(defn countdown [n]
  (if (= n 0)
    n
    (recur (- n 1))))
(countdown 100000)
;; -> 0
;; In this case, the recursion point is the function itself, because there is no loop.

(def animals [:mouse :duck :dodo :lory :eaglet])
;Because each of these are keywords, let’s have a function that will transform a key‐
;word to a string. str will do just fine for this:
(#(str %) :mouse)
;; -> ":mouse"
;Finally, let’s put it all together with map and map this function over all the elements of
;the collection:
(map #(str %) animals)
;; -> (":mouse" ":duck" ":dodo" ":lory" ":eaglet")
;Did you notice that it wasn’t a vector that got returned?
(class (map #(str %) animals))
;; -> clojure.lang.LazySeq
;map returns a lazy sequence. Lazy sequences mean that we can deal with infinite
;sequences if we like. Let’s see what happens when we try to map across an infinite
;sequence like all integers:
(take 3 (map #(str %) (range)))
;; -> ("0" "1" "2")
(take 10 (map #(str %) (range)))
;; -> ("0" "1" "2" "3" "4" "5" "6" "7" "8" "9")
(def animal-print (map #(println %) animals))
;; -> #'user/animal-print
animal-print
;; :mouse
;; :duck
;; :dodo
;; :lory
;; :eaglet
;; -> (nil nil nil nil nil)

(def animals
  ["mouse" "duck" "dodo" "lory" "eaglet"])
(def colors
  ["brown" "black" "blue" "pink" "gold"])
(defn gen-animal-string [animal color]
  (str color "-" animal))
(map gen-animal-string animals colors)
;; -> ("brown-mouse" "black-duck" "blue-dodo" "pink-lory" "gold-eaglet"
(def animals
  ["mouse" "duck" "dodo" "lory" "eaglet"])
(def colors
  ["brown" "black"])
(map gen-animal-string animals colors)
;; -> ("brown-mouse" "black-duck")
(map gen-animal-string animals (cycle ["brown" "black"]))
;; -> ("brown-mouse" "black-duck" "brown-dodo" "black-lory" "brown-eaglet")

(reduce + [1 2 3 4 5])
;; -> 15

(reduce (fn [r x] (+ r (* x x))) [1 2 3])
;; -> 14

(reduce (fn [r x] (if (nil? x) r (conj r x)))
        []
        [:mouse nil :duck nil nil :lory])
;; -> [:mouse :duck :lory]

((complement nil?) nil)
;; -> false
((complement nil?) 1)
;; -> true

(filter (complement nil?) [:mouse nil :duck nil])
;; -> (:mouse :duck)

(filter keyword? [:mouse nil :duck nil])
;; -> (:mouse :duck)

(remove nil? [:mouse nil :duck nil])
;; -> (:mouse :duck)

(for [animal [:mouse :duck :lory]]
  (str (name animal)))
;; -> ("mouse" "duck" "lory")

(for [animal [:mouse :duck :lory]
      color [:red :blue]]
  (str (name color) (name animal)))
;; -> ("redmouse" "bluemouse"
;; "redduck" "blueduck"
;; "redlory" "bluelory")

(for [animal [:mouse :duck :lory]
      color [:red :blue]
      :let [animal-str (str "animal-" (name animal))
            color-str (str "color-" (name color))
            display-str (str animal-str "-" color-str)]]
  display-str)
;; -> ("animal-mouse-color-red" "animal-mouse-color-blue"
;; "animal-duck-color-red" "animal-duck-color-blue"
;; "animal-lory-color-red" "animal-lory-color-blue")

(for [animal [:mouse :duck :lory]
      color [:red :blue]
      :let [animal-str (str "animal-" (name animal))
            color-str (str "color-" (name color))
            display-str (str animal-str "-" color-str)]
      :when (= color :blue)]
  display-str)
;; -> ("animal-mouse-color-blue"
;; "animal-duck-color-blue"
;; "animal-lory-color-blue")

(flatten [[:duck [:mouse] [[:lory]]]])
;; -> (:duck :mouse :lory)

(vec '(1 2 3))
;; -> [1 2 3]
(into [] '(1 2 3))
;; -> [1 2 3]

(sorted-map :b 2 :a 1 :z 3)
;; -> {:a 1, :b 2, :z 3}
(into (sorted-map) {:b 2 :c 3 :a 1})
;; -> {:a 1, :b 2, :c 3}
(into {} [[:a 1] [:b 2] [:c 3]])
;; -> {:a 1, :b 2, :c 3}
(into [] {:a 1, :b 2, :c 3})
;; -> [[:c 3] [:b 2] [:a 1]]

(partition 3 [1 2 3 4 5 6 7 8 9])
;; -> ((1 2 3) (4 5 6) (7 8 9))
(partition 3 [1 2 3 4 5 6 7 8 9 10])
;; -> ((1 2 3) (4 5 6) (7 8 9))

(partition-by #(= 6 %) [1 2 3 4 5 6 7 8 9 10])
;; -> ((1 2 3 4 5) (6) (7 8 9 10))

;; chapter 2 done
