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

#_:clj-kondo/ignore
(comment
  (kikka 1 2)
  (kukka 1 2)
  (kikka "1")
  (kukka "1")
  (kikka 1.0)
  (kikka 1))

(dev/stop!)

(defn plus1 [x] (inc x))
(m/=> plus1 [:=> [:cat :int] [:int {:max 6}]])

(dev/start!)
; =prints=> ..instrumented #'user/plus1
; =prints=> started instrumentation

(plus1 "6")
; =throws=> :malli.core/invalid-input {:input [:cat :int], :args ["6"], :schema [:=> [:cat :int] [:int {:max 6}]]}

(plus1 6)
; =throws=> :malli.core/invalid-output {:output [:int {:max 6}], :value 9, :args [8], :schema [:=> [:cat :int] [:int {:max 6}]]}

(m/=> plus1 [:=> [:cat :int] :int])
; =prints=> ..instrumented #'user/plus1

(plus1 6)
; => 7

(dev/stop!)
; =prints=> ..unstrumented #'user/plus1
; =prints=> stopped instrumentation
