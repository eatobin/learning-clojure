(ns learning_clojure.atomcache)

; START:create
(defn create
  []
  (atom {}))
; END:create

; START:get
(defn getski
  [cache key]
  (@cache key))
; END:get
; START:put
(defn put
  ([cache value-map]
   (swap! cache merge value-map))
  ([cache key value]
   (swap! cache assoc key value)))
; END:put
; START:usage
(def ac (create))
(put ac :first "Eric")
; {:first "Eric"}
(println (str "Item: " (getski ac :first)))
; Item: Eric
; nil
(put ac :first "Scott")
; {:first "Scott"}
(println (str "Item: " (getski ac :first)))
; Item: Scott
;nil
(put ac :last "Scott")
; {:last "Scott", :first "Scott"}
(put ac :last "Tobin")
; {:last "Tobin", :first "Scott"}
(put ac {:middle "None" :age 66})
; {:middle "None", :age 66, :last "Tobin", :first "Scott"}
; END:usage

(def m (atom {:like {:this {:nested (atom {:value 5})}}}))

@(get-in @m [:like :this :nested])
; => {:value 5}

(get-in @(get-in @m [:like :this :nested]) [:value])
; => 5

(def k (atom {:0 {:first "Eric" :last "Tobin"} :1 {:first "Scott" :last "Jones"}}))
(get-in @k [:1 :first])
; "Scott"

(defn get-inski ([m ks]
                 (reduce get @m ks)))

(get-inski k [:1 :first])
; "Scott"
