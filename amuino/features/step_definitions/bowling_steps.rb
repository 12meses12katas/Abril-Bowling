require "#{File.dirname(__FILE__)}/../../lib/bowling"

# encoding: utf-8
Dado /^que las tiradas han sido "([^"]*)"$/ do |game|
  @totalizer = BowlingTotalizer.new(game)
end

Cuando /^calcule la sume$/ do
  @total = @totalizer.total
end

Entonces /^el resultado es "([^"]*)"$/ do |sum|
  @total.should == sum.to_i
end
