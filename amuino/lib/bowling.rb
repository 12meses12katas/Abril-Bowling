class Array 
  def second
    self[1]
  end
    
  def to_frame
    if first == "X"
      Frame.new(:strike, 10)
    elsif second == "/"
      Frame.new(:spare, first.to_i)
    else
      Frame.new(:miss, first.to_i)
    end
  end
end

class Frame
  attr_accessor :kind, :simple_score
  
  def initialize(kind, simple_score)
    @kind = kind
    @simple_score = simple_score
    @score = kind == :miss ? @simple_score : 10
  end
  
  def score(next_frames = [])
    case kind
      when :miss then 
        simple_score
      else 
        10 + bonus_frames(next_frames).inject(0) {|accum, f| accum + f.simple_score }
    end
  end
  
  def bonus_frames(next_frames)
    case kind 
      when :spare  then 
        next_frames[0..0]
      when :strike then       
        next_frames[0..1]
    end
  end
end

class BowlingTotalizer
  def initialize(game)
    @frames = BowlingTotalizer.parse(game)
  end
  
  def total
    frames = @frames.clone
    accum = 0
    10.times do
      accum += frames.shift.score(frames)
    end
    accum
  end
  
  def self.parse(game)
    raw_frames(game).collect &:to_frame
  end
  
  def self.raw_frames(game)
    game.scan(%r{(X|[0-9])(-|/)?})
  end
end