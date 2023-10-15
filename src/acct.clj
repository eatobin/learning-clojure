;; clj -X:repl/socket-repl

(ns acct
  (:require [clojure.spec.alpha :as s]))

(def email-regex #"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,63}$")
(s/def :acct/email-type (s/and string? #(re-matches email-regex %)))

(s/def :acct/acctid int?)
(s/def :acct/first-name string?)
(s/def :acct/last-name string?)
(s/def :acct/email :acct/email-type)

(s/def :acct/person (s/keys :req [:acct/first-name :acct/last-name :acct/email]
                            :opt [:acct/phone]))

(s/def :acct/an-id (s/keys :req [:acct/acctid]))

(s/valid? :acct/an-id {:acct/acctid 77}) ; true
(s/valid? :acct/an-id {:acct/acctid "77"}) ; false

(s/valid? :acct/person
          {:acct/first-name "Bugs"
           :acct/last-name "Bunny"
           :acct/email "bugs@example.com"})
;;=> true

;; Fails required key check
; (s/explain :acct/person
;   {:acct/first-name "Bugs"})
;; #:acct{:first-name "Bugs"} - failed: (contains? % :acct/last-name)
;;   spec: :acct/person
;; #:acct{:first-name "Bugs"} - failed: (contains? % :acct/email)
;;   spec: :acct/person

;; Fails attribute conformance
; (s/explain :acct/person
;   {:acct/first-name "Bugs"
;    :acct/last-name "Bunny"
;    :acct/email "n/a"})
;; "n/a" - failed: (re-matches email-regex %) in: [:acct/email]
;;   at: [:acct/email] spec: :acct/email-type

; (s/def "nope" string?) ; fails - k not a namespaced keyword or symbol

(s/def :unq/person
  (s/keys :req-un [:acct/first-name :acct/last-name :acct/email]
          :opt-un [:acct/phone]))

(s/conform :unq/person
           {:first-name "Bugs"
            :last-name "Bunny"
            :email "bugs@example.com"})
;;=> {:first-name "Bugs", :last-name "Bunny", :email "bugs@example.com"}

; (s/explain :unq/person
;   {:first-name "Bugs"
;    :last-name "Bunny"
;    :email "n/a"})
;; "n/a" - failed: (re-matches email-regex %) in: [:email] at: [:email]
;;   spec: :acct/email-type

(s/explain :unq/person
           {:first-name "Bugs"})
;; {:first-name "Bugs"} - failed: (contains? % :last-name) spec: :unq/person
;; {:first-name "Bugs"} - failed: (contains? % :email) spec: :unq/person
(println "***")
(println "")
