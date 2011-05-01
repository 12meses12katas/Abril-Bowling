class Frame
  attr_accessor :first_roll, :second_roll, :number
  
  AllPoints = 10
  FirstRegularFrame = 1
  FramesNumberIncrement = 1
  LastRegularFrame = 10
  NoPoints = 0
  
  def initialize
    @number = FirstRegularFrame
  end
  
  def next_frame= next_frame
    @next_frame = next_frame
    next_frame.number = number + FramesNumberIncrement
  end
  
  def total_points
    return NoPoints if extra?
    return strike_points if strike?
    return spare_points if spare?
    roll_points
  end
  
  def spare_extra_points
    return AllPoints if strike?
    first_roll.to_i
  end

  def strike_extra_points
    return AllPoints if spare?
    return spare_points if strike?
    roll_points
  end
  
  def roll_points
    @first_roll.to_i + @second_roll.to_i
  end
  
  def spare_points
    AllPoints + @next_frame.spare_extra_points
  end
  
  def strike_points
    AllPoints + @next_frame.strike_extra_points
  end
  
  def extra?
    @number > LastRegularFrame
  end
  
  def spare?
    @second_roll == "/"
  end
  
  def strike?
    @first_roll == "X"
  end

end