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
