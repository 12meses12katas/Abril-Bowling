require "rspec"


class BowlingScoreCalculator

  def score(game)

    prev = 3  # quitar
    rolls = (game + "--").chars.to_a
    rollsNumeric = rolls.collect do | roll |
      case roll
        when "X" then 10
        when "/" then 10-prev
        when "-" then 0
        else roll.to_i
      end
    end

    score = 0
    frame = 0
    rolls.each_with_index do | roll, index |
      frame += 1
      score += rollsNumeric[index]
      score += rollsNumeric[index+1]  if roll == "/"
      score += rollsNumeric[index+1] + rollsNumeric[index+2] if roll == "X"
      break if frame == 10
    end
    score
  end
end


describe "Bowling simple cases" do

  before do
    @subject = BowlingScoreCalculator.new
  end

  SCORES =  { "1111111111" => 10, "2222222222" => 20, "XXXXXXXXXXXX" => 300}

  SCORES.each do | game, expected_score |
    it("return score #{expected_score} given game #{game}") do
      @subject.score(game).should == expected_score
    end
  end

#  STRIKES =  { "X111111111" => 12}
STRIKES = {}

  
  STRIKES.each do | game, expected_score |
    it("return score #{expected_score} given game #{game}") do
      @subject.score(game).should == expected_score
    end
  end

end
