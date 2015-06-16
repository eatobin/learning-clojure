(def books [{:title "Book One" :author "Joe Blow" :person "Person One"}
            {:title "Book Two" :author "Joe Two" :person nil}
            {:title "The Bible" :author "G-d" :person "Person One"}])

(def people [{:name "Person One" :max-books 3}
             {:name "Person Two" :max-books 6}])

(filter (comp #{"Book One"} :title) books)
(filter (comp #{"Person One"} :name) people)
(filter (comp #{"Person One"} :person) books)
(defn get-books-for-person [person library]
  (filter (comp #{person} :person) (deref library)))

(defn add-person [name max-books]
  (swap! people conj {:name name, :max-books max-books}))

(defn create-books []
  (def books (atom [])))

(defn create-people []
  (def people (atom [])))

(defn add-book [title author person]
  (swap! books conj {:title title :author author :person person}))
