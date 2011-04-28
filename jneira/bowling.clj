(ns kata.bowling)

(defn knocked-pins [frame]
  (if-let [s (seq frame)]
    (apply + (take 2 s)) 0))

(defn bonus-score [throws bonus]
  (apply + bonus throws))

(def total-pins 10)

(defn regular-throw? [frame]
  (< (knocked-pins frame) total-pins))

(defn spare? [frame]
  (= (knocked-pins frame) total-pins))

(defn strike? [frame]
  (= (first frame) total-pins))

(defn next-throws [frames num]
  (when (seq frames)
    (take num (apply concat
                     (rest frames)))))

(def actual-frame first)

(defn frame-score [frames]
  (let [actual (actual-frame frames)]
    (if (regular-throw? actual)
      (knocked-pins actual)
      (let [n (cond (strike? actual) 2
                    (spare? actual) 1)
            throws (next-throws frames n)
            bonus (bonus-score throws 10)]
        bonus))))

;; This function belongs to other ns (not tested)
(defn maplist [f & colls]
  (apply map f
         (map #(take (count %) (iterate rest %)) colls)))

(defn sum-with [f coll]
  (apply + (take 10 (maplist f coll))))

(defn game-score [frames] 
  (sum-with frame-score frames))
