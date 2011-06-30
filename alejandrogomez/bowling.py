#!/usr/bin/env python
# encoding: utf-8

import unittest


class Bowling:
    def __init__(self):
        self._score = 0

    def score(self, rolls):
        strike = lambda x: x is 'X'
        spare = lambda x: x is '/'
        miss = lambda x: x is '-'

        def get_score(throw):
            throw_ = rolls[throw]
            if strike(throw_): 
                return 10
            elif spare(throw_): 
                return 10 - get_score(throw - 1) 
            elif miss(throw_): 
                return 0
            else:
                return int(throw_)

        self._score = 0
        throws = range(len(rolls))
        for throw in throws:
            throw_ = rolls[throw]
            self._score += get_score(throw)
            if strike(throw_):
                self._score += get_score(throw + 1) + get_score(throw + 2)
                # last throw
                if throw == len(rolls) - 3: 
                    break
            elif spare(throw_): 
                self._score += get_score(throw + 1)
                # last throw
                if throw == len(rolls) - 2:
                    break
        return self._score


class BowlingTests(unittest.TestCase):
    _ROLLS = {
                "XXXXXXXXXXXX" : 300,
                "9-9-9-9-9-9-9-9-9-9-" : 90,
                "5/5/5/5/5/5/5/5/5/5/5" : 150,
                "X--X--X--X--XX-/" : 80,
                "--------------------" : 0,
             }
            
    def setUp(self):
        self.sut = Bowling()
    
    def test_score(self):
        for roll in self._ROLLS:
            score = self._ROLLS[roll]
            self.assertEqual(score, self.sut.score(roll))


if __name__ == '__main__':
    unittest.main()
