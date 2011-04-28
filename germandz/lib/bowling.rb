class Bowling
  def self.create_game score_card_type
    types = {caf: ScoreCardCAF, live: ScoreCardLive}
    types[score_card_type.to_sym].new
  end
end

class ScoreCard
  attr_reader :type
  
  def total_points
    4
  end
  
  def initialize type
    @type = type
  end
end

class ScoreCardCAF < ScoreCard
  def initialize
    super(:caf)
  end
end

class ScoreCardLive < ScoreCard
  def initialize
    super(:live)
  end
end

