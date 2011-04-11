-module(bowling).
-include_lib("eunit/include/eunit.hrl").

-export ([score/1, test_all/0]).

%%% --- Tests first :) ---

test_all() ->
  eunit:test(bowling).

score_empty_rolls_test() ->
  ?assertMatch(0, score("")).

score_one_point_roll_test() ->
  ?assertMatch(1, score("1")).

score_two_in_two_rolls_test() ->
  ?assertMatch(2, score("11")).

score_bad_game_test() ->
  ?assertMatch(20, score("11111111111111111111")).

score_spare_test() ->
  ?assertMatch(11 + 2, score("1/11")).

score_strike_test() ->
  ?assertMatch(12 + 2 + 2, score("X1111")).

score_strike_after_spare_test() ->
  ?assertMatch(20 + 12 + 2 + 2, score("1/X1111")).

score_strike_after_strike_test() ->
  ?assertMatch(21 + 12 + 2 + 2, score("XX1111")).

score_spare_after_strike_test() ->
  ?assertMatch(20 + 11 + 2, score("X1/11")).

score_spare_in_last_frame_with_normal_score_test() ->
  ?assertMatch(18 + 11, score("1111111111111111111/1")).

score_spare_in_last_frame_with_strike_test() ->
  ?assertMatch(18 + 20, score("1111111111111111111/X")).

score_strike_in_last_frame_with_normal_scores_test() ->
  ?assertMatch(18 + 12, score("111111111111111111X11")).

score_strike_in_last_frame_with_strike_and_normal_test() ->
  ?assertMatch(18 + 21, score("111111111111111111XX1")).

score_strike_in_last_frame_with_two_strikes_test() ->
  ?assertMatch(18 + 30, score("111111111111111111XXX")).

score_twelve_strikes_test() ->
  ?assertMatch(300, score("XXXXXXXXXXXX")).

score_ten_pairs_of_nine_and_miss_test() ->
  ?assertMatch(90, score("9-9-9-9-9-9-9-9-9-9-")).

score_ten_pairs_of_five_and_spare_with_final_five_test() ->
  ?assertMatch(150, score("5/5/5/5/5/5/5/5/5/5/5")).

sanity_score_test_() ->
  lists:map(
    fun([Expected, Rolls]) -> ?_assertMatch(Expected, score(Rolls)) end,
    [
      [31, "5/5/3---------------"],
      [46, "5/5/5/3-------------"],
      [24, "X34----------------"],
      [0, "--------------------"],
      [133, "14456/5/X017/6/X2/6"]
    ]
  ).

%%% --- Implementation ---

score("") ->
  0;
score([_FirstRoll, $/, NextRoll]) -> %% Final frame with spare special case
  10 + char_to_score(NextRoll);
score([$X, NextRoll, NextNextRoll]) -> %% Final frame with strike special case
  10 + char_to_score(NextRoll) + char_to_score(NextNextRoll);
score([$X, NextRoll, $/|Rolls]) -> %% Spare after strike special case
  20 + score([NextRoll, $/|Rolls]);
score([$X, NextRoll, NextNextRoll|Rolls]) ->
  10 + char_to_score(NextRoll) + char_to_score(NextNextRoll) + score([NextRoll, NextNextRoll|Rolls]);
score([_FirstRoll, $/, NextRoll|Rolls]) ->
  10 + char_to_score(NextRoll) + score([NextRoll|Rolls]);
score([Roll|Rolls]) ->
  char_to_score(Roll) + score(Rolls).

char_to_score($X) -> 10;
char_to_score($-) -> 0;
char_to_score(Roll) when Roll >= $0, Roll =< $9 -> Roll - $0.