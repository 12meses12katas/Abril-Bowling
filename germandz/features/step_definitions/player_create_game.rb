When /^I ask for the score card$/ do
  @score_card = Bowling.create_game
end

Then /^I should obtain a score card$/ do
  @score_card.should be_a ScoreCard
end