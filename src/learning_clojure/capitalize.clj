(ns learning_clojure.capitalize)

(defn capitalize [word]

  ;loop binds initial values once,
  ;then binds values from each recursion call
  (loop [word word
         blank '()]

    ;test for return or recur
    (if (clojure.string/blank? word)

      ;return results
      (apply str (reverse blank))

      ;recur calls loop with new values
      (recur (apply str (rest word))
             (conj blank (Character/toUpperCase (first word)))))))
