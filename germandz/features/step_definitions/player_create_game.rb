Given /^a score card type named "([^"]*)"$/ do |score_card_type|
   @score_card_type = score_card_type
end

When /^I ask for the score card$/ do
  @score_card = Bowling.create_game(@score_card_type)
end

Then /^I should obtain a score card with type "([^"]*)"$/ do |type_name|
  @score_card.type.to_s.should == type_name
end