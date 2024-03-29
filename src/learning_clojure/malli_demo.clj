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

#_:clj-kondo/ignore
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


(comment
  (ns learning-clojure.malli-demo)

  (defn plus1
    "Adds one to the number"
    {:malli/schema [:=> [:cat :int] :int]}
    [x] (inc x))

  ;; instrument, clj-kondo + pretty errors
  #_:clj-kondo/ignore
  (require '[malli.dev :as dev])
  (require '[malli.dev.pretty :as pretty])
  (dev/start! {:report (pretty/reporter)})

  #_:clj-kondo/ignore
  (plus1 "1234")

  (plus1 77)

  (comment
    (dev/stop!)))

; clj -Sdeps '{:deps {metosin/malli {:mvn/version "0.13.0"}}}'

[1 2 3 #_4 5]

;; https://clojure.org/guides/weird_characters#:~:text=%23_%20%2D%20Discard,-See%20%23%20for%20additional&text=%23_%20tells%20the%20reader%20to%20ignore%20the%20next%20form%20completely.&text=also%20works.,discard%20character%20works%20in%20edn.&text=The%20docs%20suggest%20that%20%22The,which%20yields%20nil%20).%22.
