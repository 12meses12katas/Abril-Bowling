require "spec_helper"

describe Frame do
  
  let(:frame) { Frame.new }
  let(:simple_frame) { Frame.new.tap { |frame| frame.first_roll, frame.second_roll = "4", "3" }  }
  let(:spare_frame) { Frame.new.tap { |frame| frame.first_roll, frame.second_roll = "3", "/" }  }
  let(:strike_frame) { Frame.new.tap { |frame| frame.first_roll = "X" }  }
  
  
  it "should be extra after number 10" do
    frame.number = 11
    frame.extra?.should be true
  end
  
  it "should be numbered at creation" do
    frame.number.should == 1
  end

  it "should be renumbered after been added as next frame" do
    frame.next_frame = strike_frame
    strike_frame.number.should == 2
  end
  
  describe "simple points" do
    it "should be the sum where are two numbers" do
      frame.first_roll, frame.second_roll = "3", "2"
      frame.total_points.should == 5
    end
    it "should sum 0 for misses" do
      frame.first_roll, frame.second_roll = "3", "-"
      frame.total_points.should == 3
    end
  end
  
  describe "spares" do
    it "should be when second roll is /" do
      frame.first_roll, frame.second_roll = "3", "/"
      frame.spare?.should be true
    end
    it "should add the first roll of next frame" do
      frame.first_roll, frame.second_roll = "3", "/"
      frame.next_frame = simple_frame
      frame.total_points.should == 14
    end
    it "should add 10 when next roll is strike" do
      frame.first_roll, frame.second_roll = "3", "/"
      frame.next_frame = strike_frame
      frame.total_points.should == 20
    end
  end
  
  describe "strikes" do
    it "should add the following two rolls" do
      frame.first_roll = "X"
      next_frame = Frame.new
      next_frame.first_roll, next_frame.second_roll = "4", "3"
      frame.next_frame = next_frame
      frame.total_points.should == 17
    end
    it "should add the following two rolls including spares" do
      frame.first_roll = "X"
      spare_frame.next_frame = simple_frame
      frame.next_frame = spare_frame
      frame.total_points.should == 20
    end
    it "should add the following two rolls including strikes" do
      frame.first_roll = "X"
      next_frame = strike_frame
      strike_frame.next_frame = simple_frame
      frame.next_frame = next_frame
      frame.total_points.should == 24
    end
    it "should sum 30 for 3 consecutive strikes" do
      frame.first_roll = "X"
      frame.next_frame = strike_frame
      strike_frame.next_frame = strike_frame
      frame.total_points.should == 30
    end
    it "should not count rolls for extra frames" do
      simple_frame.number = 11
      simple_frame.total_points.should == 0
    end
  end
end