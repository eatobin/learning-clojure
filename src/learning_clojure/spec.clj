(ns learning-clojure.spec
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.alpha :as s]
            [clojure.spec.test.alpha :as stest]
            [clojure.spec.gen.alpha :as sgen]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [orchestra.spec.test :as ostest]
            [net.danielcompton.defn-spec-alpha :as ds]))

;; https://clojure.org/guides/spec

(ds/defn adder :- int?
  "This is a test comment"
  [x :- int?]
  (inc x))

(defn ascending?
  "clojure.core/sorted? doesn't do what we might expect, so we write our
  own function"
  [coll]
  (every? (fn [[a b]] (<= a b))
          (partition 2 1 coll)))
(def property
  (prop/for-all [v (gen/vector gen/int)]
                (let [s (sort v)]
                  (and (= (count v) (count s))
                       (ascending? s)))))
(tc/quick-check 100 property)
(def bad-property
  (prop/for-all [v (gen/vector gen/int)]
                (ascending? v)))
(tc/quick-check 100 bad-property)

(gen/sample gen/int)
(gen/sample gen/int 20)
(take 1 (gen/sample-seq gen/int))
(gen/sample (gen/vector gen/nat))
(gen/sample (gen/list gen/boolean))
(gen/sample (gen/tuple gen/nat gen/boolean gen/ratio))
(gen/sample (gen/tuple gen/nat gen/boolean gen/ratio))

(gen/sample (gen/fmap set (gen/vector gen/int)))

(def keyword-vector (gen/such-that not-empty (gen/vector gen/keyword)))
(def vec-and-elem
  (gen/bind keyword-vector
            (fn [v] (gen/tuple (gen/elements v) (gen/return v)))))

(gen/sample vec-and-elem 8)

(sgen/generate (s/gen int?))

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

(ostest/instrument)

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
