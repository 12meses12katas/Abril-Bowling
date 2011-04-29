class ScoreCard
  def notation= notation
    @notation = notation
  end
  
  def notation
    @notation
  end
  
  def total_score
    @notation.split(//).inject(0) do |sum,roll|
      sum += roll.to_i
    end
  end
end