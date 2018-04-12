(ns learning-clojure.spec
  (:require [clojure.spec.test.alpha :as st]
            [orchestra.core :refer [defn-spec]]))

(defn-spec my-inc integer?
           [a integer?]                                     ; Each argument is followed by its spec.
           (+ a 1))

(st/instrument)

(my-inc 6)
(my-inc "test")
