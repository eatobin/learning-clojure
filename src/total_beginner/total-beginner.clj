(def books (atom [{:title "Book One" :author "Joe Blow" :person "Person One"}
                  {:title "Book Two" :author "Joe Two" :person nil}
                  {:title "The Bible" :author "G-d" :person "Person One"}]))

(def people [{:name "Person One" :max-books 3}
             {:name "Person Two" :max-books 6}])

(filter (comp #{"Book One"} :title) books)
(filter (comp #{"Person One"} :name) people)
(filter (comp #{"Person One"} :person) books)
(defn get-books-for-person [person]
  (filter (comp #{person} :person) (deref books)))

(defn add-person [name max-books]
  (swap! people conj {:name name, :max-books max-books}))

(defn add-book [title author person]
  (swap! books conj {:title title :author author :person person}))

(defn create-library []
  (def books (atom []))
  (def people (atom [])))

(def library-name "my-library")

(defn get-available-books []
  (filter (comp nil? :person) (deref books)))

(defn get-book [title]
  (first (filter (comp #{title} :title) (deref books))))

(defn check-in [title]
  (when ((complement nil?) (:person (get-book title)))
    (swap! books assoc-in [(.indexOf @books (get-book title)) :person] nil)))

(if ((complement nil?) (check-in "Book Two")) "yes")

(swap! books assoc-in [2 :author] "Ericky")

(def books2 [{:title "Book One" :author "Joe Blow" :person "Person One"}
             {:title "Book Two" :author "Joe Two" :person nil}
             {:title "The Bible" :author "G-d" :person "Person One"}])

(first (filter (comp #{"Book One"} :title) books2))

(def v [[1] "two" "three" {:type 199}])
(.indexOf v {:type 199})
(.indexOf books2 {:title "Book One" :author "Joe Blow" :person "Person One"})
(.indexOf @books (get-book "Book Two"))
