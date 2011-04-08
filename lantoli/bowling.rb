require "rspec"


class BowlingScoreCalculator

  def score(game)

    rolls = (game + "--").chars.to_a
    prev = nil
    rollsNumeric = rolls.collect do | roll |
      prev = case roll
        when "X" then 10
        when "/" then 10-prev
        when "-" then 0
        else roll.to_i
      end
    end

    score = 0
    frame = 0
    rolls.each_with_index do | roll, index |
 #     break if (frame += 1) > 10
      score += rollsNumeric[index]
      score += rollsNumeric[index+1]  if roll == "/"
      score += rollsNumeric[index+1] + rollsNumeric[index+2] if roll == "X"
    end
    score
  end
end


describe "Bowling score cases." do

  before do
    @subject = BowlingScoreCalculator.new
  end

  {
      "11111111111111111111" => 20,
      "22222222222222222222" => 40,
      "--------------------" => 0,
      "9-9-9-9-9-9-9-9-9-9-" => 90,
      "4/-6----------------" => 16,
      "7/-6----------------" => 16,
      "XXX00000000000000" => 60,
      "X7/9-X-88/-6XX72" => 145,
      "X34----------------" => 24,
      "XXXXXXXXX00" => 240,


  #    "4/4/4/4/4/4/4/4/4/4/4" => 140,
  #    "5/5/5/5/5/5/5/5/5/5/5" => 150,
  #    "14456/5/X-17/6/X2/6" => 133,
  #    "X7/9-X-88/-6XXX81" => 167,
  #   "X4/X4/X4/X4/X4/X" => 200,
  #    "XXXXXXXXXXXX" => 300,

  }.each do | game, expected_score |
    it("return score #{expected_score} given game #{game}") do
      @subject.score(game).should == expected_score
    end
  end
end
