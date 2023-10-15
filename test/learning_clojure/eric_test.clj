; [eric@linux-x2vq learning-clojure](master)$ clojure -X:test/rich

(ns learning_clojure.eric-test
  (:require [clj-yaml.core :as yaml]
            [clojure.edn :as edn]
            [clojure.test :refer [deftest is testing]]))

;; YAML:

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

(deftest yaml-out-test
  (is (= "- {name: John Smith, weight: 100, age: 33}\n- {weight: 150, name: Mary Smith, age: 27}\n"
         (yaml/generate-string
          [{:name "John Smith", :weight 100, :age 33}
           {:weight 150, :name "Mary Smith", :age 27}]))))

(deftest yaml-in-test
  (is (= [{:weight 120, :name "Brenda Smith", :age 27} {:name "Scott Smith", :weight 175, :age 28}]
         (into [] (yaml/parse-string "
- {weight: 120, name: Brenda Smith, age: 27}
- name: Scott Smith
  weight: 175
  age: 28
")))))

;; EDN:

(def sample-map {:foo "bar" :bar "foo"})

;; Here you can see that the 'prn-str' is the writer...
(defn convert-sample-map-to-edn
  "Converting a Map to EDN"
  [a-map]
  ;; yep, converting a map to EDN is that simple"
  (prn-str a-map))

(println "Let's convert a map to EDN: " (convert-sample-map-to-edn sample-map))
;=> Let's convert a map to EDN:  {:foo "bar", :bar "foo"}

;; ...and the reader is 'read-string'
(println "Now let's covert the map back: " (edn/read-string (convert-sample-map-to-edn sample-map)))
;=> Now let's covert the map back:  {:foo bar, :bar foo}

(deftest edn-out-test
  (is (= "[{:name \"John Smith\", :weight 100, :age 33} {:weight 150, :name \"Mary Smith\", :age 27}]"
         (pr-str [{:name "John Smith", :weight 100, :age 33}
                  {:weight 150, :name "Mary Smith", :age 27}]))))

(deftest edn-in-test
  (is (= [{:name "John Smith", :weight 100, :age 33}
          {:weight 150, :name "Mary Smith", :age 27}]
         (edn/read-string "[{:name \"John Smith\", :weight 100, :age 33} {:weight 150, :name \"Mary Smith\", :age 27}]"))))
