# -*- coding: utf-8 -*-

import unittest

from bowling import Game, NumberOfFramesError, PinsDownError


class TestBowling (unittest.TestCase):
    """
    Unit tests for a bowling game.
    """

    def setUp (self):
        self.game = Game ()

    def test_number_of_pins_down (self):
        """
        Tests the number of pins down in a roll.
        The number of pins down can't be less than zero and greater than ten.
        """
        self.assertRaises (PinsDownError, self.game.roll, -1)
        self.assertRaises (PinsDownError, self.game.roll, 11)
        self.game.roll (5)
        self.assertEqual (self.game.score, 5)
        self.assertRaises (PinsDownError, self.game.roll, 6)
        self.game.roll (4)
        self.assertEqual (self.game.score, 9)

    def test_number_of_frames (self):
        """
        Tests the number of frames and rolls in a game.
        A bowling game has ten frames, and each frame has two rolls.
        """
        for i in range (10):
            self.game.roll (1)
            self.game.roll (1)
            self.assertEqual (len (self.game.frames[i]), 2)

        self.assertEqual (len (self.game.frames), 10)
        self.assertRaises (NumberOfFramesError, self.game.roll, 1)

    def test_frame_with_strike (self):
        """
        Tests a frame with an strike.
        When the bowler gets an strike, the frame is over.
        """
        self.game.roll (10)
        self.assertEqual (len (self.game.frames), 1)
        self.assertEqual (len (self.game.frames[0]), 1)

    def test_final_score_without_strikes_and_spares (self):
        """
        Tests the final score of a game without any strike or spare.
        """
        for i in range (10):
            self.game.roll (1)
            self.game.roll (1)

        self.assertEqual (self.game.score, 20)

    def test_spare_score (self):
        """
        Tests the partial score of an spare.
        The score of an spare is ten plus the score of the following roll.
        """
        self.game.roll (9)
        self.game.roll (1)
        self.game.roll (1)
        self.assertEqual (self.game.score, 12)

    def test_strike_score (self):
        """
        Tests the partial score of an strike.
        The score of an strike is ten plus the score of the following two rolls.
        """
        self.game.roll (10)
        self.game.roll (1)
        self.game.roll (1)
        self.assertEqual (self.game.score, 14)

    def test_final_score_with_all_spares (self):
        """
        Tests the final score of a bowling game with all spares.
        """
        for i in range (10):
            self.game.roll (1)
            self.game.roll (9)

        self.assertEqual (self.game.score, 109)
        # When the last frame is an spare, bowler gets an extra roll
        self.game.roll (1)
        self.assertEqual (self.game.score, 110)

    def test_final_score_with_all_strikes (self):
        """
        Tests the final score of a bowling game with all strikes.
        """
        for i in range (10):
            self.game.roll (10)

        self.assertEqual (self.game.score, 270)
        # When the last frame is an strike, bowler gets two extra rolls
        self.game.roll (10)
        self.game.roll (10)
        self.assertEqual (self.game.score, 300)


if __name__ == '__main__':
    unittest.main ()

