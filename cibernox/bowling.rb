class Frame
  
  attr_accessor :first, :second, :sig

  def natural_score
    (first || 0) + (second || 0)
  end

  def acumulative_score
    return natural_score if natural_score < 10                                            # Normal
    return natural_score + sig.natural_score if first < 10 && sig                # Spare
    natural_score + (sig ? sig.natural_score + (sig.sig ? sig.sig.natural_score  : 0) : 0) # Strike
  end

  def initialize(ary)
    self.first, self.second = *ary
  end

end

class Bowling
  attr_accessor :frames

  ##
  #  ary contains the frames' scores. 
  #  It can be an array^2 ([[1,2],[10,nil],[3,7],[5,5]...]) 
  #  or an array of strings where 'X' means Strike, '/' mean Spare, and "3,4" is a normal frame
  #  but it can't contains both types
  ##
  def initialize(ary)
    formated_ary = if ary.first.is_a? String
        ary.map{ |e| e == "X" ? [10, nil] : (e == "/" ? [5,5] : e.split(',').map(&:to_i) ) }
      else
        ary
      end
    self.frames = formated_ary.map{ |e| Frame.new(e) }
    self.frames.each_with_index{ |frame, index| frame.sig = self.frames[index + 1] if index <= 10 }
  end

  def total_score
    self.frames[0..9].inject(0){|acum, frame| acum + frame.acumulative_score }
  end

end