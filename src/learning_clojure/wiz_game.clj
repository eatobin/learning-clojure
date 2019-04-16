(ns learning-clojure.wiz-game)

(def nodes {'living-room '(you are in the living-room.
                               a wizard is snoring loudly on the couch.)
            'garden      '(you are in a beautiful garden.
                               there is a well in front of you.)
            'attic       '(you are in the attic.
                               there is a giant welding torch in the corner.)})

(defn describe-location [location nodes]
  (get nodes location))

(def edges {'living-room '((garden west door)
                            (attic upstairs ladder))
            'garden      '(living-room east door)
            'attic       '(living-room downstairs ladder)})

(defn describe-path [edge]
  (list 'there 'is 'a (first (rest (rest edge))) 'going (first (rest edge)) 'from 'here))
