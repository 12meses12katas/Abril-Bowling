class Bowling

  attr_accessor :sheet

  def spare?(i)
    @sheet[i] == "/"
  end

  def strike?(i)
    @sheet[i] == "X"
  end

  def value(i)
    if strike?(i)
      10
    elsif spare?(i)
      10 - value(i-1)
    else
      @sheet[i].to_i
    end
  end

  def score(i)
    value = value(i)
    value += value(i+1) if spare?(i)
    value += value(i+1) + value(i+2) if strike?(i)
    value
  end

  def strike_in_last_frame?
    strike?(@sheet.length-3) and (@sheet.count("X") + @sheet.length) >= 22
  end

  def spare_in_last_frame?
    spare?(@sheet.length-2)
  end

  def last_index?
    if strike_in_last_frame?
      @sheet.length-3
    elsif spare_in_last_frame?
      @sheet.length-2
    else
      @sheet.length-1  
    end
  end

  def sheet_score
    score = 0
    for i in 0..last_index?
      score += score(i)
    end
    score
  end

end