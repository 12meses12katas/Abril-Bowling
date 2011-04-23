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


if __name__ == '__main__':
    unittest.main ()

