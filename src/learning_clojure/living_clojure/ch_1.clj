(ns learning_clojure.living_clojure.ch_1)

42
12.43
1/3
4/2
(/ 1 3)
(/ 1 3.0)
(/ 1.0 3)
(/ 1.0 3.0)
"jam"
\j
true
false
nil
(+ 1 1)
(+ 1 (+ 8 3))
;; My last comment!
'(1 2 "jam" :marmalade-jar)
(list 1 2 :me "you")
'(1, 2, "jam", :bee)
(first (list :rabbit :pocket-watch :marmalade :door))
(rest '(:rabbit :pocket-watch :marmalade :door))
(first (rest '(:rabbit :pocket-watch :marmalade :door)))
(first (rest (rest '(:rabbit :pocket-watch :marmalade :door))))
(first (rest (rest (rest '(:rabbit :pocket-watch :marmalade :door)))))
(first (rest (rest (rest (rest '(:rabbit :pocket-watch :marmalade :door))))))
(cons 5 '())
(cons 5 nil)
(cons 4 (cons 5 nil))
(cons 3 (cons 4 (cons 5 nil)))
(cons 2 (cons 3 (cons 4 (cons 5 nil))))
'(1 2 3 4 5)
(list 1 2 3 4 5)
[:jar1 1 2 3 :jar2]
(first [:jar1 1 2 3 :jar2])
(rest [:jar1 1 2 3 :jar2])
(nth [:jar1 1 2 3 :jar2] 0)
(nth [:jar1 1 2 3 :jar2] 4)
(last [:rabbit :pocket-watch :marmalade])
(last '(:rabbit :pocket-watch :marmalade))
(count [1 2 3 4])
(conj [:toast :butter] :jam)
(conj [:toast :butter] :jam :honey)
(conj '(:toast :butter) :jam)
(conj '(:toast :butter) :jam :honey)
{:jam1 "strawberry" :jam2 "blackberry"}
{:jam1 "strawberry", :jam2 "blackberry"}
(get {:jam1 "strawberry", :jam2 "blackberry"} :jam2)
(get {:jam1 "strawberry", :jam2 "blackberry"} :jam3 "not found")
(:jam2 {:jam1 "strawberry", :jam2 "blackberry" :jam3 "marmalade"})
(keys {:jam1 "strawberry", :jam2 "blackberry", :jam3 "marmalade"})
(vals {:jam1 "strawberry", :jam2 "blackberry", :jam3 "marmalade"})
(assoc {:jam1 "red" :jam2 "black"} :jam1 "orange")
(dissoc {:jam1 "strawberry", :jam2 "blackberry"} :jam1)
(merge {:jam1 "red" :jam2 "black"}
       {:jam1 "orange" :jam3 "red"}
       {:jam4 "blue"})
#{:red :blue :white :pink}
(clojure.set/union #{:r :b :w} #{:w :p :y})
(clojure.set/difference #{:r :b :w} #{:w :p :y})
(clojure.set/intersection #{:r :b :w} #{:w :p :y})
(set [:rabbit :rabbit :watch :door])
(set {:a 1, :b 2, :c 3})
(get (set {:a 1, :b 2, :c 3}) :c)
(get #{:rabbit :door :watch} :rabbit)
(get #{:rabbit :door :watch} :jar)
(:rabbit #{:rabbit :door :watch})
(#{:rabbit :door :watch} :rabbit)
(contains? #{:rabbit :door :watch} :rabbit)
(contains? #{:rabbit :door :watch} :jam)

(contains? [:rabbit :door :watch] :door)

;; `contains?` is straightforward for maps:
(contains? {:a 1} :a)                                       ;=> true
(contains? {:a nil} :a)                                     ;=> true
(contains? {:a 1} :b)                                       ;=> false

;; It's likely to surprise you for other sequences because it's
;; about *indices* or *keys*, not *contents*:

(contains? [:a :b :c] :b)                                   ;=> false
(contains? [:a :b :c] 2)                                    ;=> true
(contains? "f" 0)                                           ;=> true
(contains? "f" 1)                                           ;=> false
;; Can be used to test set membership
(def s #{"a" "b" "c"})

;; The members of a set are the keys of those elements.
(contains? s "a")                                           ;=> true
(contains? s "z")                                           ;=> false

(conj #{:rabbit :door} :jam)
(disj #{:rabbit :door} :door)

'(+ 1 1)
(first '(+ 1 1))

(def developer "Alice")
developer
*ns*
;; user/developer

(let [developer "Alice in Wonderland"]
  developer)
developer

(let [developer "Alice in Wonderland"
      rabbit "White Rabbit"]
  [developer rabbit])
developer

(defn follow-the-rabbit [] "Off we go!")
(follow-the-rabbit)

(defn shop-for-jams [jam1 jam2]
  {:name "jam-basket"
   :jam1 jam1
   :jam2 jam2})
(shop-for-jams "straw" "blue")

;;returns back a function
(fn [] (str "Off we go" "!"))
;;invoke with parens
((fn [] (str "Off we go" "!")))

(def follow-again (fn [] (str "Off we go" "!")))
(follow-again)

(#(str "Off we go" "!"))
(#(str "Off we go" "!" " - " %) "again")
(#(str "Off we go" "!" " - " %1 %2) "again" "?")

;(ns alice.favfoods)
;*ns*
;(def fav-food "strawberry jam")
;fav-food
;alice.favfoods/fav-food
;(ns rabbit.favfoods)
;(def fav-food "lettuce soup")
;fav-food
;*ns*
;alice.favfoods/fav-food
;
;(ns wonderland)
;(require '[alice.favfoods :as af])
;af/fav-food
;*ns*
;
;(ns wonderland2
;  (:require [alice.favfoods :as af]))
;*ns*
;af/fav-food
;
;(ns wonderland
;  (:require [clojure.set :as s]))
;
;(defn common-fav-foods [foods1 foods2]
;  (let [food-set1 (set foods1)
;        food-set2 (set foods2)
;        common-foods (s/intersection food-set1 food-set2)]
;    (str "Common Foods: " common-foods)))
;
;(common-fav-foods [:jam :brownies :toast]
;                  [:lettuce :carrots :jam])

(defrecord Person [fname lname address])
;=> learning_clojure.ch_1.Person
(defrecord Address [street city state zip])
;=> learning_clojure.ch_1.Address
(def stu (Person. "Stu" "Halloway"
                  (Address. "200 N Mangum"
                            "Durham"
                            "NC"
                            27701)))
;=> #'learning_clojure.ch-1/stu
(:lname stu)
;=> "Halloway"
(-> stu :address :city)
;=> "Durham"
(assoc stu :fname "Stuart")
;=>
;#learning_clojure.ch_1.Person{:fname "Stuart",
;                              :lname "Halloway",
;                              :address #learning_clojure.ch_1.Address{:street "200 N Mangum",
;                                                                      :city "Durham",
;                                                                      :state "NC",
;                                                                      :zip 27701}}
(update-in stu [:address :zip] inc)
;=>
;#learning_clojure.ch_1.Person{:fname "Stu",
;                              :lname "Halloway",
;                              :address #learning_clojure.ch_1.Address{:street "200 N Mangum",
;                                                                      :city "Durham",
;                                                                      :state "NC",
;                                                                      :zip 27702}}
