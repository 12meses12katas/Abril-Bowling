Given /^a score card with "([^"]*)"$/ do |notation|
  @score_card = @score_card = Bowling.create_game("caf")
end

When /^I ask the total points$/ do
  @total_points = @score_card.total_points
end

Then /^I should see (\d+)$/ do |total_points|
  @total_points.should == total_points.to_i
end
