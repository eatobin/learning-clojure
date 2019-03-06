(ns learning_clojure.living_clojure.ch_8)

(defn hi-queen [phrase]
  (str phrase ", so please your Majesty."))

(defmacro def-hi-queen [name phrase]
  (list 'defn
        (symbol name)
        []
        (list 'hi-queen phrase)))

(def-hi-queen alice-hi-queen
  "My name is Alice")
;; -> #'user/alice-hi-queen
(def-hi-queen march-hare-hi-queen
  "I'm the March Hare")
;; -> #'user/march-hare-hi-queen
(alice-hi-queen)
;; => "My name is Alice, so please your Majesty."
(march-hare-hi-queen)
;; => "I'm the March Hare, so please your Majesty."

'(first [1 2 3])
;; -> (first [1 2 3])
`(first [1 2 3])
;; -> (clojure.core/first [1 2 3])
(let [x 5]
  `(first [x 2 3]))
;; -> (clojure.core/first [user/x 2 3])
;;The code returned has the symbol for x, but we really want the value 5 in there
;;instead. This is where we can use the Unquote to tell that for the symbol of x, we
;;actually want to use the value of it:
(let [x 5]
  `(first [~x 2 3]))
;; -> (clojure.core/first [5 2 3])
(defmacro def-hi-queen2 [name phrase]
  `(defn ~(symbol name) []
     (hi-queen ~phrase)))
;;We use the ` (Syntax-quote) to quote the defn form. To get the name of the function,
;;we use the ~ (Unquote) to evaluate the symbol of the name parameter.
;;Checking this with our macroexpand-1, we can see that it is doing the right thing:
(macroexpand-1 '(def-hi-queen2 alice-hi-queen2 "My name is Alice"))
;; -> (clojure.core/defn alice-hi-queen []
;; (user/hi-queen "My name is Alice"))
;;We can then use it as our def-hi-queen macro:
(def-hi-queen2 dormouse-hi-queen "I am the Dormouse")
;; -> #'user/dormouse-hi-queen
(dormouse-hi-queen)
;; -> "I am the Dormouse, so please your Majesty."

(macroexpand-1 '(def-hi-queen2 alice-hi-queen "My name is Alice"))
;; -> (defn alice-hi-queen [] (hi-queen "My name is Alice"))

(defn make-adder [x]
  (let [y x]
    (fn [z] (+ y z))))

(def add2 (make-adder 2))
(add2 24)

(defmacro def-make-adder [name plus]
  (list 'def
        (symbol name)
        (list 'make-adder plus)))

(macroexpand-1 '(def-make-adder add3 3))
;=> (def add3 (make-adder 3))

(def-make-adder add3 3)
;=> #'user/add3
(add3 9)
;=> 12

(defmacro def-make-adder2 [name plus]
  `(def ~(symbol name)
     (make-adder ~plus)))

(macroexpand-1 '(def-make-adder2 add33 33))
;=> (def add33 (user/make-adder 33))

(def-make-adder2 add33 33)
;#'user/add33

(add33 6)
;=> 39

(defn make-adder-eric [x]
  (fn [y] (+ x y)))

(def add9 (make-adder-eric 9))

(add9 9)
