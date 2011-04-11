require "bowling"

describe Array do
  
  context "#second" do
    it "should return the second element" do
      [:a, :b].second.should == :b
    end
    
    it "should be nil if there is no second element" do
      [:a].second == nil
    end
  end
  
  context "#to_frame" do
    it "should create a strike" do
      array = ["X", nil]
      array.to_frame.kind == :strike
      array.to_frame.simple_score == 10
    end

    it "should create a spare" do
      array = ["5", "/"]
      array.to_frame.kind == :spare
      array.to_frame.simple_score == 5
    end

    it "should create a miss" do
      array = ["6", "-"]
      array.to_frame.kind == :miss
      array.to_frame.simple_score == 6
    end
  end  
end

describe Frame do
  context "#score" do
    it "should return the simple_score for a miss" do
      Frame.new(:miss, 5).score.should == 5
    end

    it "should return 10 for a spare" do
      Frame.new(:spare, 5).score.should == 10
    end

    it "should return 10 for a strike" do
      Frame.new(:strike, 10).score.should == 10
    end
    
    it "should sum the next 2 frames for a strike" do
      Frame.new(:strike, 10).score([Frame.new(:miss, 5), Frame.new(:miss, 2)]).should == 17
    end

    it "should sum the next frame for a spare" do
      Frame.new(:spare, 10).score([Frame.new(:miss, 5)]).should == 15
    end
  end
end

describe BowlingTotalizer do
  context "#raw_frames" do
    it "should parse a strike" do
      BowlingTotalizer.raw_frames("X").should == [["X", nil]]
    end
    
    it "should parse a spare" do
      BowlingTotalizer.raw_frames("5/").should == [["5", "/"]]
    end
    
    it "should parse a miss" do
      BowlingTotalizer.raw_frames("5-").should == [["5", "-"]]
    end
    
    it "should parse a mix of strikes, spares and misses" do
      BowlingTotalizer.raw_frames("5-X1/5-").should == [["5", "-"], ["X", nil], ["1", "/"], ["5", "-"]]
      BowlingTotalizer.raw_frames("5/5/5/5/5/5/5/5/5/5/5-").should == 
        [["5", "/"]]*10 + [["5", "-"]]
    end
  end
  
  context "#parse" do
    it "should parse a strike" do
      BowlingTotalizer.parse("X").collect(&:kind).should == [:strike]
    end
    
    it "should parse a spare" do
      BowlingTotalizer.parse("5/").collect(&:kind).should == [:spare]
    end
    
    it "should parse a miss" do
      BowlingTotalizer.parse("5-").collect(&:kind).should == [:miss]
    end
    
    it "should parse a mix of strikes, spares and misses" do
      BowlingTotalizer.parse("5-X1/5-").collect(&:kind).should == [:miss, :strike, :spare, :miss]
    end
  end
end