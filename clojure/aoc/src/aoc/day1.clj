(ns aoc.day1)

(def input
  (->> "resources/day1.input"
       slurp
       clojure.string/split-lines
       (map read-string)))

(defn round [n] (int (Math/floor n)))

(defn calc-fuel
  [module]
  (-> module
      (/ 3)
      round
      (- 2)
      (as-> res (if (neg? res) 0 res))))

(defn part1
  [input]
  (->> input
       (map calc-fuel)
       (reduce +)))

(defn calc-fuel2
  [module]
  (reduce
    (fn [acc n]
      (if (= n 0)
        (reduced acc)
        (+ acc n)))
    (rest (iterate calc-fuel module))))

(defn part2
  [input]
  (->> input
       (map calc-fuel2)
       (reduce +)))

(println (part1 input))
(println (part2 input))
