(ns learning-clojure.malli-2)

(defn plus1
  "Adds one to the number"
  {:malli/schema [:=> [:cat :int] :int]}
  [x]
  (inc x))

(defn my-add
  {:malli/schema [:=> [:cat :int :int] :int]}
  [x y]
  (+ x y))

(comment
  (my-add 1 2)
  (my-add 1.0 2.0))

;; instrument, clj-kondo + pretty errors
(require '[malli.dev :as dev])
(require '[malli.dev.pretty :as pretty])
(dev/start! {:report (pretty/reporter)})

(comment
  (plus1 "123")
  (plus1 123))

(comment
  (dev/stop!))
