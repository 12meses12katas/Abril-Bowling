require "spec_helper"

describe Bowling do
  describe "#start" do
    it "should create a ScoreCard" do
      Bowling.create_game.should be_a ScoreCard
    end
  end
end
