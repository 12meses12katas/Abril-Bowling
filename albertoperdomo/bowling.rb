class Bowling

  def self.score(rolls)
    new(rolls).score
  end

  def initialize(rolls)
    @rolls = rolls
  end

  def roll_amount(roll)
    case roll
    when "X"
      10
    when /\d|-/
      roll.to_i
    end
  end

  def split_frames(max_frames = 10)
    rolls = @rolls.chars.to_a
    [].tap do |generated_frames|
      while roll = rolls.shift and generated_frames.size < max_frames
        case roll
        when "X"
          frame = [10, roll_amount(rolls[0]), roll_amount(rolls[1])]

          generated_frames << frame
        when /\d|-/
          frame = [roll.to_i]
          next_roll = rolls.shift
          case next_roll
          when "/"
            frame << 10 - frame[0]
            frame << roll_amount(rolls[0])

          when /\d|-/
            frame << next_roll.to_i
          end

          generated_frames << frame
        end
      end
    end
  end

  def score
    split_frames.flatten.inject {|a,b| a + b}
  end

end
