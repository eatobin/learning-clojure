(ns learning_clojure.multi)

(defmulti encounter
          (fn [x y]
            [(:Species x) (:Species y)]))
(defmethod encounter [:Bunny :Lion]
  [_ _]
  :run-away)
(defmethod encounter [:Lion :Bunny]
  [_ _]
  :eat)
(defmethod encounter [:Lion :Lion]
  [_ _]
  :fight)
(defmethod encounter [:Bunny :Bunny]
  [_ _]
  :mate)
(def b1 {:Species :Bunny :other :stuff})
(def b2 {:other :stuff :Species :Bunny})
(def l1 {:Species :Lion :other :stuff})
(def l2 {:other :stuff :Species :Lion})

(encounter b1 b2)
;-> :mate
(encounter b1 l2)
;-> :run-away
(encounter l1 b2)
;-> :eat
(encounter l1 l2)
;-> :fight

(def bunny {:Species :Bunny})
(def lion {:Species :Lion})
(encounter bunny bunny)
(encounter lion bunny)
(encounter bunny lion)
(encounter lion lion)


(defmulti full-moon-behavior
          (fn [were-creature]
            (:were-type were-creature)))
(defmethod full-moon-behavior :wolf
  [were-creature]
  (str
    (:name were-creature) " will howl and murder"))
(defmethod full-moon-behavior :simmons
  [were-creature]
  (str
    (:name were-creature) " will encourage people and sweat to the oldies"))
(defmethod full-moon-behavior nil
  [were-creature]
  (str (:name were-creature) " will stay at home and eat ice cream"))
(defmethod full-moon-behavior :default
  [were-creature]
  (str (:name were-creature) " will stay up all night fantasy footballing"))

(full-moon-behavior {:were-type :wolf
                     :name      "Rachel from next door"})
; => "Rachel from next door will howl and murder"

(full-moon-behavior {:name      "Andy the baker"
                     :were-type :simmons})
; => "Andy the baker will encourage people and sweat to the oldies"

(full-moon-behavior {:were-type nil
                     :name      "Martin the nurse"})
; => "Martin the nurse will stay at home and eat ice cream"
(full-moon-behavior {:were-type :office-worker
                     :name      "Jimmy from sales"})
; => "Jimmy from sales will stay up all night fantasy footballing"

(defmulti types
  (fn [x y]
    [(class x) (class y)]))
(defmethod types [String String]
  [_ _]
  "Two strings!")
(defmethod types [Double Double]
  [_ _]
  "Two doubles")
(types "String 1" "String 2")
(types 44.4 55.5)
