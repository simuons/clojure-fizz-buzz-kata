(ns fizz-buzz-kata
  (:require [clojure.test :refer :all]))

(defn fizz-buzz [n]
  (condp #(zero? (mod %2 %1)) n
    15 "FizzBuzz"
    3 "Fizz"
    5 "Buzz"
    n))

(deftest fizz-buzz-examples
  (are [n result]
    (= result (fizz-buzz n))
    1 1
    2 2
    3 "Fizz"
    5 "Buzz"
    15 "FizzBuzz"))

(def fizz-buzz-seq (map fizz-buzz (iterate inc 1)))

(println (take 15 fizz-buzz-seq))

; ---------------------------------------------------------------------------------------------------------------------

(def numbers (iterate inc 1))
(defn words [div word] (map #(if (zero? (mod % div)) word) numbers))
(def fizz (words 3 "Fizz"))
(def buzz (words 5 "Buzz"))
(def quxx (words 7 "Quxx"))

(def division-game
  (map
    (fn [number & words]
      (or (not-empty (apply str words)) number))
    numbers
    fizz
    buzz
    quxx))

(println (take 30 division-game))

(run-tests)
