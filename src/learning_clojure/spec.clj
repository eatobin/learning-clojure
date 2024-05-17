(ns learning-clojure.spec
  (:require [clojure.spec.alpha :as s]
            [clojure.spec.gen.alpha :as sgen]
            [clojure.spec.test.alpha :as stest]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [net.danielcompton.defn-spec-alpha :as ds]
            [orchestra.core :as oc]
            [orchestra.spec.test :as ostest])
  (:import (java.time LocalDateTime)))

;; https://clojure.org/guides/spec

;for clj-kondo
#_:clj-kondo/ignore
(declare my-inc a adder x v)

(oc/defn-spec my-inc integer?
  [a integer?]                                     ; Each argument is followed by its spec.
  (+ a 1))

(my-inc 44)

(ds/defn adder :- double?
  "This is a test comment"
  [x :- int?]
  (+ x 0.88))
(adder 44)

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

;Taylor Wood: https://blog.taylorwood.io/2017/10/15/fspec.htm

(defn digits
  "Takes just an int and returns the set of its digit characters."
  [just-an-int]
  (into #{} (str just-an-int)))
(s/fdef digits
  :args (s/cat :just-an-int int?)
  :ret (s/coll-of char? :kind set? :min-count 1))

(defn big-fun
  [why when & {:keys [who which what]}]
  (format "%s %s %s %s %s" why when who which what))

(s/fdef big-fun
  :args (s/cat :why string?
               :when #(instance? LocalDateTime %)
               :kwargs (s/keys* :req-un [(or ::who
                                             (and ::which ::what))])))

(s/conform (s/cat :why string?
                  :when #(instance? LocalDateTime %)
                  :kwargs (s/keys* :req-un [(or ::who
                                                (and ::which ::what))]))
           ["because" (LocalDateTime/now) :which 1 :what 4])
(s/conform (s/cat :why string?
                  :when #(instance? LocalDateTime %)
                  :kwargs (s/keys* :req-un [(or ::who
                                                (and ::which ::what))]))
           ["said so" (LocalDateTime/now) :who "I"])
;(s/explain (s/cat :why string?
;                  :when #(instance? LocalDateTime %)
;                  :kwargs (s/keys* :req-un [(or ::who
;                                                (and ::which ::what))]))
;           ["because" (LocalDateTime/now) :which 1])

(big-fun "because" (LocalDateTime/now) :which 1 :what 4)    ;; valid
(big-fun "said so" (LocalDateTime/now) :who "I")            ;; also valid
(big-fun "because" (LocalDateTime/now) :which 1)            ;; invalid

(defn such-arity
  ([] "nullary")
  ([_] "unary")
  ([_ _ & _] "one two many"))
(s/fdef such-arity
  :args (s/alt :nullary (s/cat)
               :unary (s/cat :one any?)
               :variadic (s/cat :one any?
                                :two any?
                                :many (s/* any?))))

(s/conform (s/alt :nullary (s/cat)
                  :unary (s/cat :one any?)
                  :variadic (s/cat :one any?
                                   :two any?
                                   :many (s/* any?)))
           [])
(s/conform (s/alt :nullary (s/cat)
                  :unary (s/cat :one any?)
                  :variadic (s/cat :one any?
                                   :two any?
                                   :many (s/* any?)))
           [33])
(s/conform (s/alt :nullary (s/cat)
                  :unary (s/cat :one any?)
                  :variadic (s/cat :one any?
                                   :two any?
                                   :many (s/* any?)))
           [33 66])
(s/conform (s/alt :nullary (s/cat)
                  :unary (s/cat :one any?)
                  :variadic (s/cat :one any?
                                   :two any?
                                   :many (s/* any?)))
           [33 66 99])

(such-arity)
(such-arity 33)
(such-arity 33 66 88)

(defn slarp [path & [blurp? glurf?]]
  (str path blurp? glurf?))
(s/fdef slarp
  :args (s/cat :path string?
               :rest (s/? (s/cat :blurp? int?
                                 :glurf? boolean?))))

(s/conform (s/cat :path string?
                  :rest (s/? (s/cat :blurp? int?
                                    :glurf? boolean?)))
           ["test"])
(s/conform (s/cat :path string?
                  :rest (s/? (s/cat :blurp? int?
                                    :glurf? boolean?)))
           ["thePath" 42 true])

(slarp "thePath")
(slarp "thePath" 42 true)
(s/conform (s/cat :path string?
                  :rest (s/? (s/cat :blurp? int?
                                    :glurf? boolean?)))
           ["thePath" 42 true])

(s/def ::pick-fn
  (s/with-gen
    (s/fspec :args (s/cat :a any? :b any?)
             :ret any?
             :fn #(or (= (:ret %) (:a (:args %)))
                      (= (:ret %) (:b (:args %)))))
    #(gen/return (fn [a b] (rand-nth [a b])))))
(defn battle
  "Returns the victor."
  [contestant-1 contestant-2]
  (if (even? (System/nanoTime)) contestant-1 contestant-2))
(s/def battle ::pick-fn)
;(stest/check `battle)

(s/check-asserts true)

(defn stringer-bell [s]
  (s/assert (s/nilable string?) s)
  (println s "\007"))

(stringer-bell "dog")
(stringer-bell nil)
;(stringer-bell 66)

;End of Taylor Wood

(ranged-rand 8 50)
;(ranged-rand 8 5)
(stest/check `ranged-rand)

(digits 789)
;(digits 7.8)
(stest/check `digits)

(gen/generate (s/gen int?))
(gen/sample (s/gen string?))

#_:clj-kondo/ignore
(s/def ::suit #{:club :diamond :heart :spade})
(gen/sample (s/gen #{:club :diamond :heart :spade}))

(s/exercise-fn `ranged-rand)

(defn ascending?
  "clojure.core/sorted? doesn't do what we might expect, so we write our
  own function"
  [coll]
  (every? (fn [[a b]] (<= a b))
          (partition 2 1 coll)))
(def property
  (prop/for-all [v (gen/vector gen/small-integer)]
                (let [s (sort v)]
                  (and (= (count v) (count s))
                       (ascending? s)))))
(tc/quick-check 100 property)
(def bad-property
  (prop/for-all [v (gen/vector gen/small-integer)]
                (ascending? v)))
(tc/quick-check 100 bad-property)

(gen/sample gen/small-integer)
(gen/sample gen/small-integer 20)
(take 1 (gen/sample-seq gen/small-integer))
(gen/sample (gen/vector gen/nat))
(gen/sample (gen/list gen/boolean))
(gen/sample (gen/tuple gen/nat gen/boolean gen/ratio))
(gen/sample (gen/tuple gen/nat gen/boolean gen/ratio))

(gen/sample (gen/fmap set (gen/vector gen/small-integer)))

(def keyword-vector (gen/such-that not-empty (gen/vector gen/keyword)))
(def vec-and-elem
  (gen/bind keyword-vector
            (fn [v] (gen/tuple (gen/elements v) (gen/return v)))))

(gen/sample vec-and-elem 8)

(sgen/generate (s/gen int?))

(+ (- 10 6)
   (+ (+ 44 4) (* 4 4)))

(println "Let's see where this prints!")

(ostest/instrument)
