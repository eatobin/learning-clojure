(ns learning-clojure.malli-2
  (:require [malli.core :as m]))

(defn plus1
  "Adds one to the number"
  {:malli/schema [:=> [:cat :int] :int]}
  [x]
  (inc x))

(m/=> my-add [:=> [:cat :int :int] :int])
(defn my-add
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
