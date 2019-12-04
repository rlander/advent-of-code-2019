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
    (fn [{:keys [x y points steps]} el]
      (let [len     (read-string (subs el 1))
            [dx dy] (get directions (first el))
            points' (reduce-kv
                      (fn [m idx _]
                        (assoc m [(+ x (* idx dx)) (+ y (* idx dy))] (+ steps idx)))
                      {}
                      (vec (range len)))]
        {:x (+ x (* len dx)) :y (+ y (* len dy)) :points (conj points points') :steps (+ len steps)}))
    {:x 0 :y 0 :points {} :steps 0}
    input))

(defn intersect
  [[wire-a wire-b]]
  (clojure.set/intersection (-> wire-a keys set) (-> wire-b keys set)))

(defn part1
  [input]
  (->> input
       (map #(->> % calc-points :points))
       intersect
       (map #(+ (Math/abs (- (first %) 0)) (Math/abs (- (second %) 0))))
       sort
       second))

(defn part2
  [input]
  (let [[wirea wireb] (map #(->> % calc-points :points) input)]
    (->> [wirea wireb]
         intersect
         (map #(+ (get wirea %) (get wireb %)))
         sort
         second)))

(part1 input)
(part2 input)
