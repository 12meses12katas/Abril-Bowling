require "spec_helper"

describe ScoreCard do
  
  let(:score_card) { ScoreCard.new }
  
  describe "adding notations" do
    it "should accept a full notation" do
      score_card.notation = "73"
      score_card.notation.should == "73"
    end
    it "should replace other notation" do
      score_card.notation = "3"
      score_card.notation = "43"
      score_card.notation.should == "43"
    end
    it "should not add a second roll strike frames" do
      score_card.notation = "X12"
      score_card.frames[0].second_roll.should be nil
    end
  end
  
  describe "score" do
    describe "without strikes or spares" do
      it "should be the sum of downs when rolled many" do
        score_card.notation = "34"
        score_card.total_score.should be 7
      end
      it "should be sum 0 when is a miss" do
        score_card.notation = "-2"
        score_card.total_score.should be 2
      end
    end
    describe "spares" do
      it "should sum the complement to 10 in the second roll" do
        score_card.notation = "3/--"
        score_card.total_score.should be 10
      end
      it "should add the next roll" do
        score_card.notation = "3/51"
        score_card.total_score.should be 21
      end
      it "should add the extra roll" do
        score_card.notation = "3/33"
        score_card.total_score.should be 19
      end
    end
  end
end