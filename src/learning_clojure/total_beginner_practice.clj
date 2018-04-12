(def books (atom [{:title "Book One" :author "Joe Blow" :person "Person One"}
                  {:title "Book Two" :author "Joe Two" :person nil}
                  {:title "The Bible" :author "G-d" :person "Person One"}]))

(def people (atom [{:name "Person One" :max-books 2}
                   {:name "Person Two" :max-books 6}]))

(filter (comp #{"Book One"} :title) books)
(filter (comp #{"Person One"} :name) people)
(filter (comp #{"Person One"} :person) books)

(defn get-books-for-person [person]
  (into [] (filter (comp #{person} :person) (deref books))))

(defn add-person [name max-books]
  (swap! people conj {:name name, :max-books max-books}))

(defn add-book [title author person]
  (swap! books conj {:title title :author author :person person}))

(defn get-available-books []
  (filter (comp nil? :person) (deref books)))

(defn get-book [title]
  (first (filter (comp #{title} :title) (deref books))))

(defn remove-book [title]
  (reset! books (into [] (remove #(= (get-book title) %) (deref books)))))

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
  (str "Testr Library: "
       (count (deref books)) " books; "
       (count (deref people)) " people." \newline))

(nil? (:person (get-book "Book Two")))
(str "Checked out to " (:person (get-book "Book One")))

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

(print (str (apply str (map person-to-string (map get-name (deref people)))) "has " (count (get-books-for-person name))) \newline)

(require '[clj-yaml.core :as yaml])

(yaml/generate-string
  [{:name "John Smith", :weight 100, :age 33}
   {:weight 150, :name "Mary Smith", :age 27}])
;; => "- {age: 33, name: John Smith, weight: 100}\n- {age: 27, name: Mary Smith, weight: 150}\n"

(into [] (yaml/parse-string "
                            - {weight: 120, name: Brenda Smith, age: 27}
                            - name: Scott Smith
                            weight: 175
                            age: 28
                            "))
;; => [{:weight 120, :name "Brenda Smith", :age 27} {:name "Scott Smith", :weight 175, :age 28}]

(spit "flubber.yaml" (yaml/generate-string
                       [{:name "Scott Smith", :age 33}
                        {:name "Brenda Smith", :age 27}]))

(into [] (yaml/parse-string
           (slurp "flubber.yaml")))
;; => [{:age 33 :name "Scott Smith"} {:age 27 :name "Brenda Smith"}]

(spit "flubber2.yaml" (yaml/generate-string
                        [{:title "Book One" :author "Joe Blow" :person "Person One"}
                         {:title "Book Two" :author "Joe Two" :person nil}
                         {:title "The Bible" :author "G-d" :person "Person One"}]))

(def data
  (atom
    (yaml/parse-string
      (slurp "flubber2.yaml"))))
;; => #'total-beginner.begin/data
(deref data)
;; => {:books ({:person "Person One", :title "Book One", :author "Joe Blow"} {:person nil, :title "Book Two", :author "Joe Two"} {:person "Person One", :title "The Bible", :author "G-d"}), :people ({:name "Person One", :max-books 2} {:name "Person Two", :max-books 6})}
(into [] (:people (deref data)))
;; => [{:name "Person One", :max-books 2} {:name "Person Two", :max-books 6}]
(into [] (:books (deref data)))
;; => [{:person "Person One", :title "Book One", :author "Joe Blow"} {:person nil, :title "Book Two", :author "Joe Two"} {:person "Person One", :title "The Bible", :author "G-d"}]

(def books
  (atom
    (into [] (:books (yaml/parse-string
                       (slurp "flubber2.yaml"))))))

(def people
  (atom
    (into [] (:people (yaml/parse-string
                        (slurp "flubber2.yaml"))))))
;; => #'total-beginner.begin/books
;; => #'total-beginner.begin/people
(deref books)
;; => [{:person "Person One", :title "Book One", :author "Joe Blow"} {:person nil, :title "Book Two", :author "Joe Two"} {:person "Person One", :title "The Bible", :author "G-d"}]
(deref people)
;; => [{:name "Person One", :max-books 2} {:name "Person Two", :max-books 6}]
