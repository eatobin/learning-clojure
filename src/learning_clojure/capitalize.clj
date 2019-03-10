(ns learning_clojure.capitalize)

(defn capitalize [word]

  ;loop binds initial values once,
  ;then binds values from each recursion call
  (loop [word word
         accum '()]

    ;test for return or recur
    (if (clojure.string/accum? word)

      ;return results
      (apply str (reverse accum))

      ;recur calls loop with new values
      (recur (apply str (rest word))
             (conj accum (Character/toUpperCase (first word)))))))
