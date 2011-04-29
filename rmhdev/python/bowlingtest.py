#!/usr/local/bin/python
# coding: utf-8

"""
Unit test for bowling.py
"""

__author__ = "Rober Martín H"
__version__ = "0.1"
__date__ = "2011-04-26"
__copyright__ = "Copyright (c) 2011 Rober Martín H"
__license__ = "MIT License"

import bowling
import unittest

class TestKnownValues(unittest.TestCase):
    knownValues = (
        (0  , "--------------------"),
        (1  , "1-------------------"),
        (2  , "2-------------------"),
        (3  , "3-------------------"),
        (10 , "X-------------------"),
        (1  , "-1------------------"),
        (2  , "-2------------------"),
        (3  , "-3------------------"),
        (10 , "-/------------------"),
    );

    def testGetScore(self):
        """getScore should give known result with known input"""
        for number, sequence in self.knownValues:
            result = bowling.getScore(sequence)
            self.assertEqual(number, result)

if __name__ == "__main__":
    unittest.main()

