(ns aoc.day4)

(def input (range 168630 718099))

(defn int->list [n]
  (->> n
      str
      (map #(Character/getNumericValue %))))

(def part1-filter
  (comp
    (map int->list)
    (filter #(= % (sort %)))
    (filter #(not= (count %) (count (dedupe %))))))

(defn solve
  [f input]
  (count
    (transduce f conj input)))

(defn part1
  [input]
  (solve part1-filter input))

(defn has-adj?
  [l]
  (some #{2} (vals (frequencies l))))

(defn part2
  [input]
  (solve (comp part1-filter (filter #(has-adj? %))) input))

(part1 input)
(part2 input)
