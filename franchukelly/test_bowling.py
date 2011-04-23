# -*- coding: utf-8 -*-

import unittest

from bowling import Game, PinsDownError


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


if __name__ == '__main__':
    unittest.main ()

