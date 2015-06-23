(defn create-library [name]
  (def library-name name)
  (def books (atom []))
  (def people (atom [])))

(defn add-book [title author person]
  (swap! books conj {:title title :author author :person person}))

(defn add-person [name max-books]
  (swap! people conj {:name name, :max-books max-books}))

(defn get-book [title]
  (first (filter (comp #{title} :title) (deref books))))

(defn get-person [name]
  (first (filter (comp #{name} :name) (deref people))))

(defn get-available-books []
  (filter (comp nil? :person) (deref books)))

(defn get-books-for-person [person]
  (filter (comp #{person} :person) (deref books)))

(defn check-out [title name]
  (when (and (nil? (:person (get-book title)))
             (< (count (get-books-for-person name))
                (:max-books (get-person name))))
    (swap! books assoc-in [(.indexOf @books (get-book title)) :person] name)))

(defn check-in [title]
  (when ((complement nil?) (:person (get-book title)))
    (swap! books assoc-in [(.indexOf @books (get-book title)) :person] nil)))

(defn library-to-string []
  (str library-name ": "
       (count (deref books)) " books; "
       (count (deref people)) " people."))

(defn book-to-string [title]
  (if (nil? (:person (get-book title)))
        "Available"
        (str "Checked out to " (:person (get-book title)))))

(defn person-to-string [name]
  (str name " (" (:max-books (get-person name)) " books)"))
