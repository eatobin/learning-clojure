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
