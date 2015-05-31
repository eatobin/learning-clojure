(def names
  [
   {:id 0, :last "Tobin", :first "Eric", :middle "A."}
   {:id 1, :last "Tobin", :first "Brenda", :middle "D."}
   ])

names
(conj names {:id 2, :last "Tobin", :first "Saul", :middle nil})
names

(def names2 (conj names {:id 2, :last "Tobin", :first "Saul", :middle nil}))
names
names2
(last names2)

names2
(def names2 (conj names2 {:id 3, :last "Tobin", :first "Chula", :middle "Doggie"}))
names2
(last names2)

(:middle (nth names 1))
(:middle (last names2))
(nth names2 2)
(:first (nth names2 2))
