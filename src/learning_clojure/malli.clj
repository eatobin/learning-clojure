(ns learning-clojure.malli
  (:require [malli.core :as m]))

(def non-empty-string
  (m/schema [:string {:min 1}]))

(def short-string
  (m/schema [:string {:min 1 :max 3}]))

(m/schema? non-empty-string)
; => true

(m/validate non-empty-string "")
; => false

(m/validate [:and :int [:> 6]] 7)
; => true

(m/validate [:catn [:s string?] [:n int?]] ["foo" 9])
;=> true

(m/explain [:catn [:s string?] [:n int?]] ["foo" 9.9])
;=>
;{:schema [:catn [:s string?] [:n int?]], :value ["foo" 9.9], :errors ({:path [:n], :in [1], :schema int?, :value 9.9})}

(m/validate non-empty-string "kikka")
; => true

(m/form non-empty-string)
; => [:string {:min 1}]

(m/form short-string)

(m/validate short-string "me")

(m/validate short-string "kikka")

(comment (def pow
           (m/-instrument
            {:schema [:=> [:cat :int] [:int {:max 6}]]}
            (fn [x] (* x x))))

         (pow 2)
         ; => 4

         (pow "2")
         ; =throws=> :malli.core/invalid-input {:input [:cat :int], :args ["2"], :schema [:=> [:cat :int] [:int {:max 6}]]}

         (pow 4)
         ; =throws=> :malli.core/invalid-output {:output [:int {:max 6}], :value 16, :args [4], :schema [:=> [:cat :int] [:int {:max 6}]]}

         (pow 4 2))
; =throws=> :malli.core/invalid-arity {:arity 2, :arities #{{:min 1, :max 1}}, :args [4 2], :input [:cat :int], :schema [:=> [:cat :int] [:int {:max 6}]]}

(comment
  ;https://github.com/metosin/malli/blob/master/docs/function-schemas.md#functions
  (require '[malli.instrument :as mi])
  (def my-adder [:=> [:cat :int :int] :int])
  (def small-int [:int {:max 6}])

  (defn plus1 [x] (inc x))
  (m/=> plus1 [:=> [:cat :int] small-int])

  (declare plus1a)
  (m/=> plus1a [:=> [:cat :int] small-int])
  (defn plus1a [x] (inc x))

  (m/function-schemas)

  (defn my-add [x y]
    (+ x y))

  (defn my-small-add [x y]
    (+ x y))

  (m/=> my-add [:=> [:cat :int :int] :int])
  (m/=> my-small-add [:=> [:cat :int :int] small-int])
  (mi/instrument!)
  (my-add 22 2)
  ;(my-add 22.0 2.0)
  (my-small-add 1 2)
  (my-small-add 11 22))

(defn my-add-2 [x y]
  (+ x y))
(m/=> my-add-2 [:=> [:cat :int :int] :int])

(def small-int-2 [:int {:max 6}])
(def my-addition [:=> [:cat :int :int] :int])

(defn small-dual [x y f]
  (f x y))
(m/=> small-dual [:=> [:cat :int :int [:=> [:cat :int :int] :int]] small-int-2])

(defn just-dual [x y f]
  (f x y))
;; (m/=> just-dual [:=> [:cat :int :int [:=> [:cat :int :int] :int]] :int])
(m/=> just-dual [:=> [:cat :int :int my-addition] :int])

(just-dual 1 2 my-add-2)
(just-dual 11 22 my-add-2)
#_:clj-kondo/ignore
(just-dual 11.0 22.0 my-add-2)

(small-dual 1 2 my-add-2)
(small-dual 11 22 my-add-2)
#_:clj-kondo/ignore
(small-dual 11.0 22.0 my-add-2)

(defn print-em [x y]
  (str x ", " y))

(small-dual 1 2 print-em)
