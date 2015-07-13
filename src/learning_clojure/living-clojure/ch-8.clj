(defn hi-queen [phrase]
  (str phrase ", so please your Majesty."))

(defmacro def-hi-queen [name phrase]
  (list 'defn
        (symbol name)
        []
        (list 'hi-queen phrase)))

(def-hi-queen alice-hi-queen "My name is Alice")
;; -> #'user/alice-hi-queen
(def-hi-queen march-hare-hi-queen "I'm the March Hare")
;; -> #'user/march-hare-hi-queen
(alice-hi-queen)
;; => "I'm Alice, so please your Majesty."
(march-hare-hi-queen)
;; => "I'm the March Hare, so please your Majesty."

'(first [1 2 3])
;; -> (first [1 2 3])
`(first [1 2 3])
;; -> (clojure.core/first [1 2 3])
(let [x 5]
  `(first [x 2 3]))
;; -> (clojure.core/first [user/x 2 3])
;The code returned has the symbol for x, but we really want the value 5 in there
;instead. This is where we can use the Unquote to tell that for the symbol of x, we
;actually want to use the value of it:
(let [x 5]
  `(first [~x 2 3]))
;; -> (clojure.core/first [5 2 3])
(defmacro def-hi-queen [name phrase]
  `(defn ~(symbol name) []
     (hi-queen ~phrase)))
;We use the ` (Syntax-quote) to quote the defn form. To get the name of the function,
;we use the ~ (Unquote) to evaluate the symbol of the name parameter.
;Checking this with our macroexpand-1, we can see that it is doing the right thing:
(macroexpand-1 '(def-hi-queen alice-hi-queen "My name is Alice"))
;; -> (clojure.core/defn alice-hi-queen []
;; (user/hi-queen "My name is Alice"))
;We can then use it as our def-hi-queen macro:
(def-hi-queen dormouse-hi-queen "I am the Dormouse")
;; -> #'user/dormouse-hi-queen
(dormouse-hi-queen)
;; -> "I am the Dormouse, so please your Majesty."
