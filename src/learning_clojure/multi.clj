(ns learning_clojure.multi)

(defmulti encounter (fn [x y] [(:Species x) (:Species y)]))
(defmethod encounter [:Bunny :Lion] [b l] :run-away)
