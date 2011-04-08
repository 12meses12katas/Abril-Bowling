require "rspec"


class BowlingScoreCalculator

  def score(game)
    rolls = game.chars.to_a
    score = 0
    frame = 1
    in_middle_frame = false
    rolls.each_with_index do | roll, index |
      break if frame > 10
      score += roll_value(rolls, index)
      if roll == "/" || roll == "X"
        in_middle_frame = false
        frame += 1
        score += roll_value(rolls, index+1)
        score += roll_value(rolls, index+2) if roll == "X"
      else
        if in_middle_frame
            in_middle_frame = false
            frame += 1
        else
          in_middle_frame = true
        end
      end
    end
    score
  end

  private

  def roll_value(rolls, index)
    case rolls[index]
      when "X" then 10
      when "/" then 10 - roll_value(rolls, index-1)
      when "-" then 0
      else rolls[index].to_i
    end
  end

end

describe "Bowling score cases." do

  before do
    @subject = BowlingScoreCalculator.new
  end

  # some tests got from dobeslao
  {
      "11111111111111111111"  => 20,
      "22222222222222222222"  => 40,
      "--------------------"  => 0,
      "9-9-9-9-9-9-9-9-9-9-"  => 90,
      "4/-6----------------"  => 16,
      "7/-6----------------"  => 16,
      "XXX00000000000000"     => 60,
      "X7/9-X-88/-6XX72"      => 145,
      "X34----------------"   => 24,
      "XXXXXXXXX00"           => 240,
      "4/4/4/4/4/4/4/4/4/4/4" => 140,
      "5/5/5/5/5/5/5/5/5/5/5" => 150,
      "X4/X4/X4/X4/X4/X"      => 200,
      "XXXXXXXXXXXX"          => 300,
      "14456/5/X-17/6/X2/6" => 133,
      "X7/9-X-88/-6XXX81" => 167,

  }.each do | game, expected_score |
    it("return score #{expected_score} given game #{game}") do
      @subject.score(game).should == expected_score
    end
  end
end
