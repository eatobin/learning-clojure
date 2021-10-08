(ns learning-clojure.dynamic)

(def ^:dynamic *box* 42)

(binding [*box* 66] *box*)
*box*

(with-redefs [*box* 2] *box*)
(binding [*box* 99]
  (println *box*))

(binding [*box* (inc *box*)]
  (println *box*))

*box*

(def weird 33)
weird

(with-redefs [weird 5] weird)
weird

(binding [*box* 1] (set! *box* 2) *box*)
*box*

(def ^:dynamic d)
d
(binding [d 0] (prn d) (set! d 1) (prn d))
d
