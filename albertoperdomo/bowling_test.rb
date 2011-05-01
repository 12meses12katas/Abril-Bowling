require "minitest/autorun"
require "bowling"

class BowlingTest < MiniTest::Unit::TestCase

  def assert_score(expected_result, rolls, msg = nil)
    actual_result = Bowling.score(rolls)
    assert_equal expected_result, actual_result, msg || "Expected #{expected_result} for rolls '#{rolls}' but was #{actual_result}"
  end

  def test_12_rolls_12_strikes
    assert_score 300, "XXXXXXXXXXXX"
  end

  def test_10_pairs_of_9_and_miss
    assert_score 90, "9-9-9-9-9-9-9-9-9-9-"
  end

  def test_10_pairs_of_5_and_spare_with_a_final_5
    assert_score 150, "5/5/5/5/5/5/5/5/5/5/5"
  end

  def test_11_rolls_with_a_final_3
    assert_score 95, "--34--1/425/XX112/3"
  end

  def test_split_frames
    assert_equal [[4, 5], [4, 6, 10], [10, 1, 0]], Bowling.new("454/X1-").split_frames(3)
    assert_equal 10, Bowling.new("XXXXXXXXXXXX").split_frames.size
  end
end

