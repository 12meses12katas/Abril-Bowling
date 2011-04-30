require 'rspec'
require File.dirname(__FILE__) + '/../lib/bowling.rb'

describe Bowling do

  specify "simple case" do
    "--------------------".scores(0)
    "1-------------------".scores(1)
    "11------------------".scores(2)
    "9-9-9-9-9-9-9-9-9-9-".scores(90)
  end

  specify "spare" do
    "1/------------------".scores(10)
    "1/1-----------------".scores(12)
    "------------------2/3".scores(13)
    "5/5/5/5/5/5/5/5/5/5/5".scores(150)
  end

  specify "strike" do
    "X------------------".scores(10)
    "X11----------------".scores(14)
    "------------------X11".scores(12)
    "----------------X22".scores(18)
    "X----------------X11".scores(22)
    "XXXXXXXXXXXX".scores(300)
  end

end


class String
  def scores(expected)
    game = Bowling.new
    game.sheet = self
    game.sheet_score.should == expected
  end
end





