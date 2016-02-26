(ns learning-clojure.eric)

(def t0 (System/currentTimeMillis))
(defn t1 [] (System/currentTimeMillis))
(t1)
t0
t0
(t1)

(def xx 1)
xx

(list 1 2 3)

(def aa 70)
(def vv 88)
(+ vv aa)

(let [j 58] (/ j 2) j)

(def x (/ 12 3))
x

(let [x 1
      y x]
  y)

(let [x 1 y x] y)

(let [h 20 r (/ h 4)] r)

(defn big [st n]
  (if (> (count st) n)
    "True it is."))
(big "gggg" 4)                                              ;; prints nothing
(big "gggg" 3)

(defn factorial [x]
  (if (zero? x)
    1
    (* x (factorial (dec x)))))
(factorial 12)

;; We can use get-in for reaching into nested maps:
(def my {:username "sally"
         :profile  {:name    "Sally Clojurian"
                    :address {:city "Austin" :state "TX"}}})

(get-in my [:profile :name])
;; "Sally Clojurian"
(get-in my [:profile :address :city])
;; "Austin"
(get-in my [:profile :address :zip-code])
;; nil
(get-in my [:profile :address :zip-code] "no zip code!")
;; "no zip code!"

(def us {:username "toberi"
         :profile  {:first "Eric", :last "Tobin"}, :tobbre {:first "Brenda", :last "Tobin"}})
(get-in us [0 :first])
;; nil
(get-in us [1 :first])
;; nil
(get-in us [:profile :first])
;; "Eric"
(get-in us [:username])
;;=> "toberi"
(get-in us [:tobbre :last])
;;=> "Tobin"

;; We can mix associative types:
(def mv {:username "jimmy"
         :pets     [{:name "Rex"
                     :type :dog}
                    {:name "Sniffles"
                     :type :hamster}]})

(get-in mv [:pets 1 :type])
;; :hamster

(def mo {:toberi {:first "Eric", :last "Tobin"}
         :tobbre {:first "Brenda" :last "Tobin"}})

(get-in mo [:toberi :first])
;; "Eric"
(get-in mo [:tobbre :first])
;; "Brenda"

;I’ll give you one last convenient oddity before I move on. Sets are also functions.
;The set #{:jar-jar, :chewbacca} is an element but also a function. Sets test
;membership, like this:
;user=> (#{:jar-jar :chewbacca} :chewbacca)
;:chewbacca
;user=> (#{:jar-jar :chewbacca} :luke)
;nil
;When you use a set as a function, the function will return the first argument
;if that argument is a member of the set. That covers the basics for sets. Let’s
;move on to maps.

(def seq-of-maps1 [{:foo 1 :bar "hi"} {:foo 0 :bar "baz"}])
;; #'user/seq-of-maps
(filter (comp #{"hi"} :bar) seq-of-maps1)
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

(def seq-of-maps2 [{:foo 1 :bar "baz"} {:foo 0 :bar "hi"}])
;; #'user/seq-of-maps
(filter (comp #{"hi"} :bar) seq-of-maps2)
;; ({:bar "hi", :foo 0})

(def seq-of-maps9 [{:foo 1 :bar "baz"} {:foo 0 :bar "hi"} {:foo 22 :bar "hi"}])
(filter (comp #{"hi"} :bar) seq-of-maps9)
;; ({:bar "hi", :foo 0} {:bar "hi", :foo 22})

(def books [{:title "Book One" :author "Joe Blow" :person "Person One"}
            {:title "Book Two" :author "Joe Two" :person nil}
            {:title "The Bible" :author "G-d" :person "Person One"}])
(def people [{:name "Person One" :max-books 3}
             {:name "Person Two" :max-books 6}])
(filter (comp #{"Book One"} :title) books)
(filter (comp #{"Person One"} :name) people)
(filter (comp #{"Person One"} :person) books)

(require '[clj-yaml.core :as yaml])

(yaml/generate-string
  [{:name "John Smith", :weight 100, :age 33}
   {:weight 150, :name "Mary Smith", :age 27}])
; => "- {age: 33, name: John Smith, weight: 100}\n- {age: 27, name: Mary Smith, weight: 150}\n"

(into [] (yaml/parse-string "- {age: 33, name: John Smith, weight: 100}\n- {age: 27, name: Mary Smith, weight: 150}\n"))
;; => [{:age 33, :name "John Smith", :weight 100} {:age 27, :name "Mary Smith", :weight 150}]

(spit "flubber.yaml" (yaml/generate-string
                       [{:name "Scott Smith", :age 33}
                        {:name "Brenda Smith", :age 27}]))

(into [] (yaml/parse-string
           (slurp "flubber.yaml")))
;; => [{:age 33 :name "Scott Smith"} {:age 27 :name "Brenda Smith"}]

(spit "flubber.yaml" (yaml/generate-string
                       [{:title "Book One" :author "Joe Blow" :person "Person One"}
                        {:title "Book Two" :author "Joe Two" :person nil}
                        {:title "The Bible" :author "G-d" :person "Person One"}]))

(def booksX
  (atom (into []
              (yaml/parse-string
                (slurp "flubber.yaml")))))

(spit "flubber.yaml"
      (yaml/generate-string
        (deref booksX)))
