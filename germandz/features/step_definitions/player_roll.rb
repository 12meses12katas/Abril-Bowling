Given /^a score card with "([^"]*)"$/ do |notation|
  @score_card = Bowling.create_game
  @score_card.notation = notation
end

When /^I ask the total points$/ do
  @total_score = @score_card.total_score
end

Then /^I should see (\d+)$/ do |total_score|
  @total_score.should == total_score.to_i
end
