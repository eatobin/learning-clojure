(ns learning-clojure.spec
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as stest]
            [clojure.spec.gen.alpha :as gen]))

;; https://clojure.org/guides/spec


(defn ranged-rand
  "Returns random int in range start <= rand < end"
  [start end]
  (+ start (long (rand (- end start)))))

(s/fdef ranged-rand
        :args (s/and (s/cat :start int? :end int?)
                     #(< (:start %) (:end %)))
        :ret int?
        :fn (s/and #(>= (:ret %) (-> % :args :start))
                   #(< (:ret %) (-> % :args :end))))

(defn digits
  "Takes just an int and returns the set of its digit characters."
  [just-an-int]
  (into #{} (str just-an-int)))

(s/fdef digits
        :args (s/cat :just-an-int int?)
        :ret (s/coll-of char? :kind set? :min-count 1))

(stest/instrument)

(ranged-rand 8 50)
;(ranged-rand 8 5)
(stest/check `ranged-rand)

(digits 789)
;(digits 7.8)
(stest/check `digits)

(gen/generate (s/gen int?))
(gen/sample (s/gen string?))

(s/def ::suit #{:club :diamond :heart :spade})
(gen/sample (s/gen #{:club :diamond :heart :spade}))

(s/exercise-fn `ranged-rand)
