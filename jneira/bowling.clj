(ns bowling
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

;; Fact 1

(fact "The game score is the total of all frame scores"
      (game-score ..frames..) => ..game-score..
      (provided
       (sum-with (exactly frame-score) ..frames..)
       => ..game-score..))

;; Fact 

(fact "Each game includes ten turns or frames"
      "Not validated")

;; Fact 2

(defn tries [frame] [nil nil])

(fact "In each frame, the bowler gets up to
       two tries to knock down all the pins."
      (count (tries ..frame..))=> 2)

(background (actual-frame ..frames..) => ..actual..
            (regular-throw? ..actual..) => false
            (spare? ..actual..) => false
            (strike? ..actual..) => false)

;; Fact 3

(fact "If in two tries, he fails to knock them all down,
       his score for that frame is the total number of pins
       knocked down in his two tries."
      (frame-score ..frames..) => ..result..
      (provided
       (regular-throw? ..actual..) => truthy
       (knocked-pins ..actual..) => ..result..))

;; Fact 4

(fact "If in two tries he knocks them all down,
       this is called a spare and his score for the frame is
       ten plus the number of pins knocked down on his next throw
       (in his next turn)"
      (frame-score ..frames..) => ..result..
      (provided
       (spare? ..actual..) => truthy
       (next-throws ..frames.. 1) => ..throws..
       (bonus-score ..throws.. 10) => ..result..))

;; Fact 5

(fact "If on his first try in the frame he knocks down all the pins,
       this is called a strike. His turn is over, and his score
       for the frame is ten plus the simple total of the pins
       knocked down in his next two rolls."
      (frame-score ..frames..) => ..result..
      (provided
       (strike? ..actual..) => truthy
       (next-throws ..frames.. 2) => ..throws..
       (bonus-score ..throws.. 10) => ..result..))

;; Fact 6

(fact "If he gets a spare or strike in the last (tenth) frame,
       the bowler gets to throw one or two more bonus balls,
       respectively.
       These bonus throws are taken as part of the same turn.
       If the bonus throws knock down all the pins,
       the process does not repeat: the bonus throws are only used
       to calculate the score of the final frame."
     "Implicit in previous facts" )

