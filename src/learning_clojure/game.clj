(defn start-over []
  (def small (atom 1))
  (def big (atom 100))
  (guess-my-number))

(defn guess-my-number []
  (quot (+ @small @big) 2))

(defn smaller []
  (reset! big (dec (guess-my-number)))
  (guess-my-number))

(defn bigger []
  (reset! small (inc (guess-my-number)))
  (guess-my-number))
