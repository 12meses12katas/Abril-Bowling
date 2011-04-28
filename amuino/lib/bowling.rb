class Array 
  def second
    self[1]
  end
    
  def to_frame
    if first == "X"
      Frame.new(:strike, [10, nil])
    elsif second == "/"
      Frame.new(:spare, [first.to_i, 10-first.to_i])
    elsif second == "-"
      Frame.new(:miss, [first.to_i, 0])
    else 
      Frame.new(:normal, [first.to_i, second.to_i])
    end
  end
end

class Frame
  attr_accessor :kind, :simple_score
  
  def initialize(kind, simple_score)
    @kind = kind
    @simple_score = simple_score
    @score = simple_score.compact.inject(0, :+)
  end
  
  def score(next_frames = [])
    case kind
      when :miss then 
        @score
      when :normal then 
        @score
      else 
        10 + bonus_throws(next_frames)
    end
  end
  
  def bonus_throws(next_frames)
    return 0 if next_frames.nil? || next_frames.empty?
    case kind 
      when :spare  then 
        next_frames.first.simple_score.first.to_i
      when :strike then       
        next_frames[0..1].collect { |frame| frame.simple_score }.flatten.compact[0..1].inject(0, :+)
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
    game.scan(%r{(X)|(-|[0-9])([1-9]|-|/)?}).collect {|x| x.compact}
  end
end