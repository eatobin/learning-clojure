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

(if-let [result nil]
  result
  "I'll go to a cafe.")
;; "I'll go to a cafe."

(if (= 1 1) 100 nil)
;; 100
(if-let [result (if (= 1 1)
                  100
                  nil)]
  result
  "I'll go to a cafe.")
;; 100
(if-let [result (if (not= 1 1) 100 nil)]
  result
  "I'll go to a cafe.")
;; "I'll go to a cafe."




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
