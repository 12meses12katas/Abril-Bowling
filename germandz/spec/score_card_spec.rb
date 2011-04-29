require "spec_helper"

describe ScoreCard do
  
  let(:score_card) { ScoreCard.new }
  
  describe "adding notations" do
    it "should accept a full notation" do
      score_card.notation = "7"
      score_card.notation.should == "7"
    end
    it "should replace other notation" do
      score_card.notation = "3"
      score_card.notation = "43"
      score_card.notation.should == "43"
    end
  end
  
  describe "score" do
    describe "without strikes or spares" do
      it "should be count of down when rolled one" do
        score_card.notation = "3"
        score_card.total_score.should be 3
      end
      it "should be the sum of downs when rolled many" do
        score_card.notation = "34"
        score_card.total_score.should be 7
      end
    end
  end
end