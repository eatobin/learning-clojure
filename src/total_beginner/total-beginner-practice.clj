(def books (atom [{:title "Book One" :author "Joe Blow" :person "Person One"}
                  {:title "Book Two" :author "Joe Two" :person nil}
                  {:title "The Bible" :author "G-d" :person "Person One"}]))

(def people (atom [{:name "Person One" :max-books 2}
                   {:name "Person Two" :max-books 6}]))

(filter (comp #{"Book One"} :title) books)
(filter (comp #{"Person One"} :name) people)
(filter (comp #{"Person One"} :person) books)

(defn get-books-for-person [person]
  (filter (comp #{person} :person) (deref books)))

(defn add-person [name max-books]
  (swap! people conj {:name name, :max-books max-books}))

(defn add-book [title author person]
  (swap! books conj {:title title :author author :person person}))

(defn create-library [name]
  (def library-name name)
  (def books (atom []))
  (def people (atom [])))

(defn get-available-books []
  (filter (comp nil? :person) (deref books)))

(defn get-book [title]
  (first (filter (comp #{title} :title) (deref books))))

(defn check-in [title]
  (when ((complement nil?) (:person (get-book title)))
    (swap! books assoc-in [(.indexOf @books (get-book title)) :person] nil)))

(if ((complement nil?) (check-in "Book Two")) "yes")

(swap! books assoc-in [2 :author] "Ericky")

(def v [[1] "two" "three" {:type 199}])
(.indexOf v {:type 199})
(.indexOf @books (get-book "Book Two"))

(and (nil? (:person (get-book "Book One"))) true)
(count (get-books-for-person "Person One"))

(defn get-person [name]
  (first (filter (comp #{name} :name) (deref people))))

(defn check-out [title name]
  (when (and (nil? (:person (get-book title)))
             (< (count (get-books-for-person name))
                (:max-books (get-person name))))
    (swap! books assoc-in [(.indexOf @books (get-book title)) :person] name)))

(defn person-to-string [name]
  (let [books-out (count (get-books-for-person name))]
  (str name " (" (:max-books (get-person name)) " books) (has " books-out " of my books)" \newline)))

(defn library-to-string []
  (str library-name ": "
       (count (deref books)) " books; "
       (count (deref people)) " people."))

  (defn book-to-string [title]
    (if (nil? (:person (get-book title))) "Available"
        (str "Checked out to " (:person (get-book title)))))

  (nil? (:person (get-book "Book Two")))
  (str "Checked out to " (:person (get-book "Book One")))

(defn book-to-string [title]
  (if (nil? (:person (get-book title))) "Available"
      (str "Checked out to " (:person (get-book title)))))

(defn book-to-string [title]
  (let [available
        (if (nil? (:person (get-book title)))
          "Available"
          (str "Checked out to " (:person (get-book title))))]
  (str title " by " (:author (get-book title)) "; " available \newline)))

(defn get-title [book]
  (:title book))

(print (apply str (map book-to-string (map get-title (deref books)))))

(defn get-name [person]
  (:name person))

(print (str (apply str (map person-to-string (map get-name (deref people)))) \newline))

(print (str (apply str (map person-to-string (let [name (map get-name (deref people))])) "has " (count (get-books-for-person name))) \newline))



