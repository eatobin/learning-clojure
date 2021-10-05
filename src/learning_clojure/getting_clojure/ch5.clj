(ns learning-clojure.getting-clojure.ch5)

(defn normalize-book-1 [book]
  (if (vector? book)
    {:title (first book) :author (second book)}
    (if (contains? book :title)
      book
      {:title (:book book) :author (:by book)})))

(comment
  (normalize-book-1 ["A Book" "Me"])
  (normalize-book-1 {:title "Next Book" :author "You"})
  (normalize-book-1 {:book "Last Book" :by "No One"}))

(defn dispatch-book-format [book]
  (cond
    (vector? book) :vector-book
    (contains? book :title) :standard-map
    (contains? book :book) :alternative-map))

(defmulti normalize-book dispatch-book-format)

(defmethod normalize-book :vector-book [book]
  {:title (first book) :author (second book)})

(defmethod normalize-book :standard-map [book]
  book)

(defmethod normalize-book :alternative-map [book]
  {:title (:book book) :author (:by book)})

(comment
  (normalize-book {:title "War and Peace" :author "Tolstoy"})
  (normalize-book {:book "Emma" :by "Austen"})
  (normalize-book ["1984" "Orwell"]))

(defn dispatch-published [book]
  (cond
    (< (:published book) 1928) :public-domain
    (< (:published book) 1978) :old-copyright
    :else :new-copyright))

(defmulti compute-royalties dispatch-published)

(defmethod compute-royalties :public-domain [_] 0)

(defmethod compute-royalties :old-copyright [book]
  ;; Compute royalties based on old copyright law.
  (* 0.1 (:price book)))

(defmethod compute-royalties :new-copyright [book]
  (* 0.2 (:price book)))

(comment
  (format "$%.2f" (double (compute-royalties {:price 10.00 :published 1901})))
  (format "$%.2f" (double (compute-royalties {:published 1950 :price 20.00})))
  (format "$%.2f" (double (compute-royalties {:price 30.00 :published 2001}))))

(def books [{:title "Pride and Prejudice" :author "Austen" :genre :romance}
            {:title "World War Z" :author "Brooks" :genre :zombie}])

;we could certainly create a multimethod based on the genre:
;; Remember you can use keys like :genre like functions on maps.
(defmulti book-description :genre)
(defmethod book-description :romance [book]
  (str "The heart warming new romance by " (:author book)))
(defmethod book-description :zombie [book]
  (str "The heart consuming new zombie adventure by " (:author book)))
(defmethod book-description :zombie-romance [book]
  (str "The heart warming and consuming new romance by " (:author book)))

;But what if much later someone comes up with a new genre?
(def ppz {:title  "Pride and Prejudice and Zombies"
          :author "Grahame-Smith"
          :genre  :zombie-romance})

(comment
  (map book-description books)
  (book-description ppz))
