(ns bowling
  (:use (midje sweet)))
 
(unfinished frame-score)

(fact "The game score is the total of all frame scores"
      (game-score ..frames..) => ..game-score..
      (provided
       (reduce (exactly frame-score) ..frames..) => ..game-score..))

(defn game-score [frames]
  (reduce frame-score frames))

(fact "Each game includes ten turns or frames"
      "Not validated") 

(unfinished tries)

(fact "In each frame, the bowler gets up to
       two tries to knock down all the pins."
      (count (tries ..frame..))=> 2 )

(defn tries [frame] [nil nil])

(unfinished knocked-pins-this-throw)
(unfinished regular-throw?)

(fact "If in two tries, he fails to knock them all down,
       his score for that frame is the total number of pins
       knocked down in his two tries."
      (frame-score ..frame..) => ..pins..
      (provided
       (knocked-pins-this-throw ..frame..) => ..pins..
       (regular-throw? ..pins..) => truthy))

(defn frame-score [frame]
  (let [pins (knocked-pins-this-throw frame)]
    (if (regular-throw? pins) pins)))

(unfinished spare?)
(unfinished knocked-pins-next-throw)
(unfinished bonus-score)

(fact "If in two tries he knocks them all down,
       this is called a spare and his score for the frame is
       ten plus the number of pins knocked down on his next throw
       (in his next turn)"
      (frame-score ..frames..) => ..result..
      (provided
       (knocked-pins-this-throw ..frames..) => ..pins..
       (spare? ..pins..) => truthy
       (knocked-pins-next-throw ..frames..) => ..next-pins..
       (bonus-score 10 ..next-pins..) => ..result..))

(defn frame-score [frames]
  (let [pins
        (knocked-pins-this-throw frames)
        next-throw-pins
        (knocked-pins-next-throw frames)]
    (if (spare? pins) (bonus-score 10 next-throw-pins)
        (if (regular-throw? pins) pins))))

(unfinished strike?)
(unfinished next-throws)
(unfinished actual-frame)
(unfinished knocked-pins)

(defn regular-throw? [frame])

(fact "If on his first try in the frame he knocks down all the pins,
       this is called a strike. His turn is over, and his score
       for the frame is ten plus the simple total of the pins
       knocked down in his next two rolls."
      (frame-score ..frames..) => ..result..
      (provided
       (actual-frame ..frames..) => ..actual..
       (strike? ..actual..) => truthy
       (next-throws ..frames.. 2) => ..throws..
       (bonus-score ..throws.. 10) => ..result..))

(future-fact "If he gets a spare or strike in the last (tenth) frame, the bowler gets to throw one or two more bonus balls, respectively. These bonus throws are taken as part of the same turn. If the bonus throws knock down all the pins, the process does not repeat: the bonus throws are only used to calculate the score of the final frame.")
