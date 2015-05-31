42
12.43
1/3
4/2
(/ 1 3)
(/ 1 3.0)
(/ 1.0 3)
(/ 1.0 3.0)
"jam"
\j
true
false
nil
(+ 1 1)
(+ 1 (+ 8 3))
;; My last comment!
'(1 2 "jam" :marmalade-jar)
(list 1 2 :me "you")
'(1, 2, "jam", :bee)
(first (list :rabbit :pocket-watch :marmalade :door))
(rest '(:rabbit :pocket-watch :marmalade :door))
(first (rest '(:rabbit :pocket-watch :marmalade :door)))
(first (rest (rest '(:rabbit :pocket-watch :marmalade :door))))
(first (rest (rest (rest '(:rabbit :pocket-watch :marmalade :door)))))
(first (rest (rest (rest (rest '(:rabbit :pocket-watch :marmalade :door))))))
(cons 5 '())
(cons 5 nil)
(cons 4 (cons 5 nil))
(cons 3 (cons 4 (cons 5 nil)))
(cons 2 (cons 3 (cons 4 (cons 5 nil))))
'(1 2 3 4 5)
(list 1 2 3 4 5)
[:jar1 1 2 3 :jar2]
(first [:jar1 1 2 3 :jar2])
(rest [:jar1 1 2 3 :jar2])
(nth [:jar1 1 2 3 :jar2] 0)
(nth [:jar1 1 2 3 :jar2] 4)
(last [:rabbit :pocket-watch :marmalade])
(last '(:rabbit :pocket-watch :marmalade))
(count [1 2 3 4])
(conj [:toast :butter] :jam)
(conj [:toast :butter] :jam :honey)
(conj '(:toast :butter) :jam)
(conj '(:toast :butter) :jam :honey)
{:jam1 "strawberry" :jam2 "blackberry"}
{:jam1 "strawberry", :jam2 "blackberry"}
(get {:jam1 "strawberry", :jam2 "blackberry"} :jam2)
(get {:jam1 "strawberry", :jam2 "blackberry"} :jam3 "not found")
(:jam2 {:jam1 "strawberry", :jam2 "blackberry" :jam3 "marmalade"})
(keys {:jam1 "strawberry", :jam2 "blackberry", :jam3 "marmalade"})
(vals {:jam1 "strawberry", :jam2 "blackberry", :jam3 "marmalade"})
(assoc {:jam1 "red" :jam2 "black"} :jam1 "orange")
