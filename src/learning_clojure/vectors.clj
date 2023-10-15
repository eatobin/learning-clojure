(ns learning_clojure.vectors)

(def names
  [{:id 0, :last "Tobin0", :first "Eric", :middle "A."}
   {:id 1, :last "Tobin1", :first "Brenda", :middle "D."}])

;; names
(conj names {:id 2, :last "Tobin2", :first "Saul", :middle nil})
;; names

(def names29 (conj names {:id 2, :last "Tobin2", :first "Saul", :middle nil}))
;; names
;; names29
(last names29)

;; names29
(def names2 (conj names29 {:id 3, :last "Tobin3", :first "Chula", :middle "Doggie"}))
;; names29
(last names29)

(:middle (nth names 1))
(:middle (last names2))
(nth names2 2)
(:first (nth names2 2))
