(ns clojure-intro
  (:require [clojure.repl :refer :all]))

; Dynamic functional language
; Dialect of List
; Created by Rich Hickey
; Runs on JVM
; Immutable data structures
; Built with concurrency in mind: refs, atoms, agents
; Has no more parenthesis than Java function(arg1, arg2) -> (function args1, arg2) -> (function arg1 arg2)
; Homoiconicity
; REPL

; Forms

; Literals
{
 :nil         nil
 :boolean     [true false]
 :long        42
 :double      42.0
 :big-integer 42N
 :big-decimal 42M
 :ratio       1/3
 :character   \p
 :string      "Quick Brown Fox"
 :keyword     :quick-brown-fox
 :symbol      [+ java.lang.String]                  ; cannot start with a number but can consist of alphanumeric characters, plus +, -, *, /, !, ?, ., and _
 :regex       #"[\w\d]+"
 :vector      [1 1 2 3 5 8 13 21]
 :list        (:quick :brown :fox)
 :map         {:name "Alan" :age 42}
 :set         #{1 2 3}}

; List

'(1 2 4)
(list 1 2 3)

; Vector

[1 2 3]
(vec '(1 2 3))
(vector 1 2 3)

; Set

#{1 2 3}
(hash-set 1 2 3)
(set '(1 2 3))
(sorted-set 3 1 2)

; Map

{1 "a" 2 "b"}

(hash-map 1 "a" 2 "b")
(sorted-map 2 "b" 1 "a")

({1 "a" 2 "b"} 1)

{:a "a" :b "b"}

(:a {:a "a" :b "b"})

({:a "a" :b "b"} :a)

(get {:a "a" :b "b"} :a :key-not-found)

; Symbol, Vars, Bindings

(def answer 42)

(eval 'answer)

(var-get #'answer)

(let [answer 42] answer)

; Functions

(fn [name] (str "Hello " name))

(def greeting (fn [name] (str "Hello " name)))

(defn greeting [name]
  (str "Hello " name))

(defn greeting
  ([] (greeting "incognito"))
  ([name] (str "Hello " name)))

(defn make-greater [prefix] #(str prefix ", " %))
(def aloha (make-greater "Aloha"))
(aloha "Kimona")

; Lambdas

#(str %)
#(str %1 %2)
#(str %2)
#(str %1 %&)

; Flow Control

(if (even? 42) "truthy" "falsy")

(do (println "side-effect") (str "result"))

(when true (println "exec") (str "x"))

(when-not false (println "exec") (str "x"))

(cond
  (> 0 1) "ans1"
  (< 0 1) "ans2"
  :else "otherwise")

(loop [result [] x 5]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))

(defn countdown [result x]
  (if (zero? x)
    result
    (recur (conj result x) (dec x))))

; Destructuring

(defn greet-author [author] (str "Hello," (:first-name author)))
(defn greet-author [{fname :first-name}] (str "Hello, " fname))

(let [[x y] [1 2 3]]
  [x y])

(let [[_ _ z] [1 2 3]]
  z)

(let [[a b c & d :as e] [1 2 3 4 5 6 7]]
  [a b c d e])

(let [[[x1 y1] [x2 y2]] [[1 2] [3 4]]]                      ; Can be nested
  [x1 y1 x2 y2])

(let [[a b & c :as str] "asdjhhfdas"]                       ; Strings work too
  [a b c str])

(let [{a :a, b :b, c :c, :as m :or {a 2 b 3}} {:a 5 :c 6}]  ; Maps
  [a b c m])

(let [{:keys [a b c] :or {a 2 b 3}} {:a 5 :c 6}]            ; :keys shorthand
  [a b c])

; REPL

; *1, *2, *3 - hold the result of the last three expressions that were evaluated
(str *1 *2 *3)
; *e - holds the result of the last exception.
(str *e)
; doc - prints the docstring for a var given its name
(doc identity)
; find-doc - prints the docstring for any var whose doc or name matches the pattern
(find-doc #"\?$")
; apropos - returns a seq of definitions matching a regex
; source - prints the source for a symbol
(source identity)
; pst - print stack trace for a given exception or *e by default
(pst 5)
