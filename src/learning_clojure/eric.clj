(def t0 (System/currentTimeMillis))
(defn t1 [] (System/currentTimeMillis))
(t1)
t0
t0
(t1)

(def xx 1)
(println xx)

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

(defn big [st n]
  (if (> (count st) n)
    (println "True it is.")))
(big "gggg" 4)                                              ;; prints nothing
(big "gggg" 3)

(defn factorial [x]
  (if (zero? x)
    1
    (* x (factorial (dec x)))))
(factorial 12)

;; We can use get-in for reaching into nested maps:
(def m {:username "sally"
        :profile  {:name    "Sally Clojurian"
                   :address {:city "Austin" :state "TX"}}})

(get-in m [:profile :name])
;; "Sally Clojurian"
(get-in m [:profile :address :city])
;; "Austin"
(get-in m [:profile :address :zip-code])
;; nil
(get-in m [:profile :address :zip-code] "no zip code!")
;; "no zip code!"

(def us {:username "toberi"
         :profile  {:first "Eric", :last "Tobin"}, :tobbre {:first "Brenda", :last "Tobin"}})
(get-in us [0 :first])

;; We can mix associative types:
(def mv {:username "jimmy"
         :pets     [{:name "Rex"
                     :type :dog}
                    {:name "Sniffles"
                     :type :hamster}]})

(get-in mv [:pets 1 :type])
;; :hamster

(def m {:toberi {:first "Eric", :last "Tobin"}
        :tobbre {:first "Brenda" :last "Tobin"}})

(get-in m [:toberi :first])
;; "Eric"
(get-in m [:tobbre :first])
;; "Brenda"

(def seq-of-maps [{:foo 1 :bar "hi"} {:foo 0 :bar "baz"}])
;; #'user/seq-of-maps
(filter (comp #{"hi"} :bar) seq-of-maps)
;; ({:bar "hi", :foo 1})

(def test-it (comp #{"hi"} :bar))
;; #'user/test-it
(test-it {:foo 1 :bar "baz"})
;; nil
(test-it {:foo 1 :bar "hi"})
;; "hi"
(test-it {:foo 1 :bark "hi"})
;; nil
(:bar {:foo 1})
;; nil
(:bar {:bar 1})
;; 1
(#{2} (:bar {:bar 1}))
;; nil
(#{1} (:bar {:bar 1}))
;; 1
(#{"hi"} (:bar {:foo 1 :bar "hi"} {:foo 0 :bar "baz"}))
;; "hi"
(#{"baz"} (:bar {:foo 1 :bar "hi"} {:foo 0 :bar "baz"}))
;; nil
(#{"baz"} (:bar {:foo 1 :bar "baz"} {:foo 0 :bar "me"}))
;; "baz"

(def seq-of-maps [{:foo 1 :bar "baz"} {:foo 0 :bar "hi"}])
;; #'user/seq-of-maps
(filter (comp #{"hi"} :bar) seq-of-maps)
;; ({:bar "hi", :foo 0})

(def seq-of-maps [{:foo 1 :bar "baz"} {:foo 0 :bar "hi"} {:foo 22 :bar "hi"}])
(filter (comp #{"hi"} :bar) seq-of-maps)
;; ({:bar "hi", :foo 0} {:bar "hi", :foo 22})
