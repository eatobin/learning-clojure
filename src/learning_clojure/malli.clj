(ns learning-clojure.malli
  (:require [malli.core :as m]))

;(def non-empty-string
;  (m/schema [:string {:min 1}]))
;
;(def short-string
;  (m/schema [:string {:min 1 :max 3}]))
;
;(m/schema? non-empty-string)
;; => true
;
;(m/validate non-empty-string "")
;; => false
;
;(m/validate non-empty-string "kikka")
;; => true
;
;(m/form non-empty-string)
;; => [:string {:min 1}]
;
;(m/form short-string)
;
;(m/validate short-string "me")
;
;(m/validate short-string "kikka")

;;Maps

(def non-empty-string
  (m/from-ast {:type       :string
               :properties {:min 1}}))

(m/schema? non-empty-string)
; => true

(m/validate non-empty-string "")
; => false

(m/validate non-empty-string "kikka")
; => true

(m/ast non-empty-string)
; => {:type :string,
;     :properties {:min 1}}

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

  (m/=> plus1 [:=> [:cat :int] small-int])
  (defn plus1 [x] (inc x))

  (defn my-add [x y]
    (+ x y))

  (defn my-small-add [x y]
    (+ x y))

  (m/=> my-add [:=> [:cat :int :int] :int])
  (m/=> my-small-add [:=> [:cat :int :int] small-int])
  (mi/instrument!)
  (my-add 22 2)
  (my-add 22.0 2.0)
  (my-small-add 1 2)
  (my-small-add 11 22))

