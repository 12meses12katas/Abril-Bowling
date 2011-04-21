(ns kata.bowling
  (:use (midje sweet)))

(unfinished sum-with)

(unfinished actual-frame)

(unfinished regular-throw?)
(unfinished knocked-pins)

(unfinished spare?)
(unfinished strike?)

(unfinished next-throws)
(unfinished bonus-score)

(defn frame-score [frames]
  (let [actual (actual-frame frames)]
    (if (regular-throw? actual)
      (knocked-pins actual)
      (let [n (cond (strike? actual) 2
                    (spare? actual) 1)
            throws (next-throws frames n)
            bonus (bonus-score throws 10)]
        bonus))))

(defn game-score [frames] 
  (sum-with frame-score frames))

(fact "The game score is the total of all frame scores"
      (game-score ..frames..) => ..game-score..
      (provided
       (sum-with (exactly frame-score) ..frames..)
       => ..game-score..))

(defn sum-with [f coll])

(fact "Each game includes ten turns or frames"
      "Not validated")

(defn tries [frame] [nil nil])

(fact "In each frame, the bowler gets up to
       two tries to knock down all the pins."
      (count (tries ..frame..))=> 2)

(against-background
 [(actual-frame ..frames..) => ..actual..
  (regular-throw? ..actual..) => false
  (spare? ..actual..) => false
  (strike? ..actual..) => false]

(fact "If in two tries, he fails to knock them all down,
       his score for that frame is the total number of pins
       knocked down in his two tries."
      (frame-score ..frames..) => ..result..
      (provided
       (regular-throw? ..actual..) => truthy
       (knocked-pins ..actual..) => ..result..))

(fact "If in two tries he knocks them all down,
       this is called a spare and his score for the frame is
       ten plus the number of pins knocked down on his next throw
       (in his next turn)"
      (frame-score ..frames..) => ..result..
      (provided
       (spare? ..actual..) => truthy
       (next-throws ..frames.. 1) => ..throws..
       (bonus-score ..throws.. 10) => ..result..))

(fact "If on his first try in the frame he knocks down all the pins,
       this is called a strike. His turn is over, and his score
       for the frame is ten plus the simple total of the pins
       knocked down in his next two rolls."
      (frame-score ..frames..) => ..result..
      (provided
       (strike? ..actual..) => truthy
       (next-throws ..frames.. 2) => ..throws..
       (bonus-score ..throws.. 10) => ..result..))

(fact "If he gets a spare or strike in the last (tenth) frame,
       the bowler gets to throw one or two more bonus balls,
       respectively.
       These bonus throws are taken as part of the same turn.
       If the bonus throws knock down all the pins,
       the process does not repeat: the bonus throws are only used
       to calculate the score of the final frame."
     "Implicit in previous facts" ))

;; Bottom up

(defn knocked-pins [frame]
  (if-let [s (seq frame)]
    (apply + (take 2 s)) 0))

(facts "About knocked pins"
       "It returns 0 if the input is nil or empty"
       (knocked-pins nil) => 0
       (knocked-pins []) => 0
       "It returns the sum of knocked-pins of a frame with one or two throws"
       (knocked-pins [5 5]) => 10
       (knocked-pins [10]) => 10
       "If the frame has more than 2 throws it ignores them"
       (knocked-pins [5 5 5]) => 10)

(defn bonus-score [throws bonus]
  (apply + bonus throws))

(facts "About bonus score"
       "It returns nil if any of inputs is nil or throws is empty"
       (bonus-score nil 0) => 0
       (bonus-score [] 0)=> 0
       "It returns the sum of the knocked pins plus the bonus"
       (bonus-score [5] 10) => 15
       (bonus-score [5 5] 10) => 20)

(def total-pins 10)
(defn regular-throw? [frame]
  (< (knocked-pins frame) total-pins))

(facts "About regular throw?"
       "It returns true if input is nil or empty"
       (regular-throw? nil) => true
       (regular-throw? [])=> true
       "It return true only if the sum of knocked pins is
        less than total pins"
       (regular-throw? [5 4]) => true
       (provided
        (knocked-pins [5 4]) => 9)
       (regular-throw? [10 0]) => false
       (regular-throw? [5 5]) => false)

(defn spare? [frame]
  (= (knocked-pins frame) total-pins))

(facts "About spare?"
       "It returns false if input is nil or empty"
       (spare? nil) => false
       (spare? []) => false
       "It returns true if the sum of knocked pins is equal to total-pins"
       (spare? [3 7]) => true
       (provided (knocked-pins [3 7]) => 10)
       (spare? [10 0]) => true)

(defn strike? [frame]
  (= (first frame) total-pins))

(facts "About strike?"
       "It return false if input is nil or empty"
       (strike? nil) => false
       (strike? []) => false
       "It returns true only if all pins was knocked in the first throw"
       (strike? [10]) => true
       (strike? [6 4]) => false)

(defn next-throws [frames num]
  (when (seq frames)
    (take num (apply concat
                     (rest frames)))))

(facts "About next throws"
       "It returns nil if frames is nil or empty"
       (next-throws nil 2) => nil
       (next-throws [] 2) => nil
       "It returns the next n throws in the game if "
       (next-throws [[:actual-1-throw :actual-2-throw]
                     [1 1]] 2) => [1 1]
       (next-throws [[:actual-unique-throw]
                     [1 1]] 2) => [1 1])

(def actual-frame first)

(facts "About actual-frame"
       "It returns nil if the input is nil or empty"
       (actual-frame nil) => nil
       (actual-frame []) => nil
       "It returns the first element of the frames"
       (actual-frame [[1] [1 1]]) => [1])

(facts "About frame score"
       "It returns the score of a frame following the top-down facts"
       "Regular throw"
       (frame-score [[1 1] anything]) => 2
       "Spare"
       (frame-score [[5 5] [5 anything]]) => 15
       "Strike"
       (frame-score [[10] [5 5] anything]) => 20
       (frame-score [[10] [10] [10] anything]) => 30)

;; This function belongs to other ns (not tested)
(defn maplist [f & colls]
  (apply map f
         (map #(take (count %) (iterate rest %)) colls)))

(defn sum-with [f coll]
  (apply + (take 10 (maplist f coll))))

(facts "About game score"
       "It returns the score of a game"
       "12 rolls: 12 strikes"
       (game-score (repeat 12 [10])) => 300
       "21 rolls: 10 pairs of 5 and spare, with a final 5"
       (game-score (concat (repeat 10 [5 5]) [[5]])) => 150
       "20 rolls: 10 pairs of 9 and miss"
       (game-score (repeat 10 [9 0])) => 90)
