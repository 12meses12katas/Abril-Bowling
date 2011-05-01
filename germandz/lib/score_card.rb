require "frame"

class ScoreCard
  attr_reader :notation, :frames
  
  def notation= notation
    @notation = notation
    @frames = []
    last_frame = nil
    rolls = notation.split(//)
    while (!rolls.empty?) do
      frame = Frame.new
      frame.first_roll  = rolls.shift
      frame.second_roll = rolls.shift unless rolls.empty? || frame.strike?
      last_frame.next_frame = frame unless last_frame.nil?
      @frames << frame
      last_frame = frame
    end
  end
  
  def total_score
    @frames.inject(0) do |sum,frame|
      sum += frame.total_points
    end
  end
end