class Frame
  
  attr_accessor :index, :first, :second, :previous, :acumulative_score

  def natural_score
    first + second
  end

  def acumulate_score(acum)
    self.acumulative_score = natural_score + acum
    self.previous.acumulate_score(self.natural_score) if self.previous
  end

  def initialize(idx, ary, prev = nil)
    self.index = idx
    self.first, self.second = *ary
    self.previous = prev
  end

end

class Bowling
  attr_accessor :frames

  def initialize(ary)
    self.frames = []
    raise "Invalid number of frames" if ary.size != 10
    ary.each_with_index do |e, i|
      prev = i == 0 ? nil : self.frames[i - 1]
      self.frames << Frame.new(i, e, prev)
    end
  end

end