(defn my-plan [weather]
  (if (= :good weather)
    "I'll go to a park."
    "I'll go to a cafe."))

(my-plan :good)
(my-plan :bad)

(if true
    "I'll go to a park."
    "I'll go to a cafe.")

(let [result true]
  (if result
    "I'll go to a park."
    "I'll go to a cafe."))

(if-let [result "I'll go to a park."]
    result
    "I'll go to a cafe.")

(if-let [result "works!"]
    result)

;; in real repl only (read-line):
;;
;; (if-let [result (read-line)]
;;     result
;;     "I'll go to a cafe.")

;; (defn input []
;;    (println "What is your decision?")
;;    (if-let [v (not-empty (read-line))]
;;       v
;;       (do
;;          (println "That is not valid...")
;;          (recur))))

;; --- 3 functions:
;; (defn valid? [x]
;;   (if (= x "Go!")
;;     x
;;     nil))

;; (defn input []
;;    (println "What is your decision?")
;;    (if-let [v (valid? (read-line))]
;;       v
;;       (do
;;          (println "That is not valid...")
;;          (recur))))

;; (let [yayinput (read-line)]
;;   (if (= yayinput "1234")
;;     (println "Correct")
;;     (println "Wrong")))
;; ---

(let [[color size] ["blue" "small"]]
  (str "The " color " door is " size))

(let [[color size :as original] ["blue" "small"]]
  {:color color :size size :original original})

(let [[color [size] :as original] ["blue" ["small"]]]
  {:color color :size size :original original})

(let [{myFlower :flower1 yourFlower :flower2}
      {:flower1 "red" :flower2 "blue"}]
  (str "The flowers are " myFlower " and " yourFlower))
