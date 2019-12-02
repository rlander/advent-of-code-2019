(ns aoc.day2)

(def input
  (->> "resources/day2.input"
       slurp
       (re-seq #"\d+")
       (mapv read-string)))

(def ops {1 + 2 *})

(defn part1
  [input noun verb]
  (loop [pos 0
         instructions (assoc input 1 noun 2 verb)]
    (if (= 99 (get instructions pos))
      (first instructions)
      (let [[op a b res] (subvec instructions pos (+ pos 4))
             op' (get ops op)]
        (recur (+ pos 4) (assoc instructions res (op' (nth instructions a) (nth instructions b))))))))

(defn part2
  [input]
  (first
    (for [noun (range 136) verb (range 136) :when (= 19690720 (part1 input noun verb))]
      (+ (* 100 noun) verb))))

(part1 input 12 2)
(part2 input)
