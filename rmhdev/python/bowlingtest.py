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
        (2  , "11------------------"),
        (10 , "1/------------------"),
        (12 , "X1------------------"), # two frames!
        (16 , "X12-----------------"),
        (13 , "12X-----------------"),
        (14 , "1/2-----------------"),
        (30 , "1/X-----------------"),
        (34 , "1/X2----------------"), # three frames!
        (60 , "XXX-----------------"),
        (32 , "1/1/1/--------------"),
        (300, "XXXXXXXXXXXX"),
        (90 , "9-9-9-9-9-9-9-9-9-9-"),
        (150, "5/5/5/5/5/5/5/5/5/5/5"),
    );

    def testGetScore(self):
        """getScore should give known result with known input"""
        for number, sequence in self.knownValues:
            result = bowling.getScore(sequence)
            self.assertEqual(number, result)

if __name__ == "__main__":
    unittest.main()

