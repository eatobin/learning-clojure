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

(m/validate non-empty-string "kikka")
; => true

(m/form non-empty-string)
; => [:string {:min 1}]

(m/form short-string)

(m/validate short-string "me")

(m/validate short-string "kikka")
