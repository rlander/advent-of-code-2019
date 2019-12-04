(ns aoc.day3)

(def input
  (->> "resources/day3.input"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #","))))

(def directions {\R [1 0] \L [-1 0] \U [0 1] \D [0 -1]})

(defn calc-points
  [input]
  (reduce
    (fn [points el]
      (let [len (read-string (subs el 1))
            [[x y] steps] (apply max-key val points)
            [dx dy] (get directions (first el))
            points' (reduce-kv
                      (fn [res idx item]
                        (assoc res [(+ x (* idx dx)) (+ y (* idx dy))] (+ steps idx)))
                      {}
                      (vec (range (+ 1 len))))]
        (conj points points')))
    {[0,0] 0}
    input))

(defn intersect
  [[wire-a wire-b]]
  (clojure.set/intersection (-> wire-a keys set) (-> wire-b keys set)))

(defn part1
  [input]
  (->> input
       (map calc-points)
       intersect
       (map #(+ (Math/abs (- (first %) 0)) (Math/abs (- (second %) 0))))
       sort
       second))

(defn part2
  [input]
  (let [[wirea wireb] (map calc-points input)]
    (->> [wirea wireb]
         intersect
         (map #(+ (get wirea %) (get wireb %)))
         sort
         second)))

(part1 input)
(part2 input)
