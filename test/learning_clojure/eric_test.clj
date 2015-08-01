(ns learning-clojure.eric-test
  (:require [clojure.test :refer :all]
            [learning-clojure.eric :refer :all]
            [clj-yaml.core :as yaml]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

(deftest yaml-out-test
  (is (= "- {age: 33, name: John Smith, weight: 100}\n- {age: 27, name: Mary Smith, weight: 150}\n"
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
