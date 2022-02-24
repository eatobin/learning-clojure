(ns learning-clojure.malli-demo
  (:require [malli.core :as m]
            [malli.dev :as dev]))

(defn kikka
  "schema via var metadata"
  {:malli/schema [:=> [:cat :int] :int]}
  [x] (inc x))

(defn kukka
  "schema via separate declaration"
  [x] (inc x))
(m/=> kukka [:=> [:cat :int] :int])

(dev/start!)

(comment
  (kikka 1 2)
  (kukka 1 2)
  (kikka "1")
  (kukka "1"))
