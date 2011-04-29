#!/usr/local/bin/python
# coding: utf-8

"""
Unit test for bowling.py
"""

__author__ = "Rober Martín H"
__version__ = "0.1"
__date__ = "2011-04-30"
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
        (12 , "X1------------------"),
        (16 , "X12-----------------"),
        (13 , "12X-----------------"),
        (14 , "1/2-----------------"),
        (30 , "1/X-----------------"),
        (30 , "X1/-----------------"),
        (34 , "1/X2----------------"),
        (60 , "XXX-----------------"),
        (32 , "1/1/1/--------------"),
        (300, "XXXXXXXXXXXX"),
        (90 , "9-9-9-9-9-9-9-9-9-9-"),
        (150, "5/5/5/5/5/5/5/5/5/5/5"),
        (89 , "349-3--95/X3--1X-7"),
        (137, "729-9-9-9/9/XX8-71"),
        (133, "9/8/X7/53348/-78/-/8"),
        (100, "1/-99-6-157-8/7-9-XX-"),
    );

    def testGetScore(self):
        """getScore should give known result with known input"""
        for number, sequence in self.knownValues:
            result = bowling.getScore(sequence)
            self.assertEqual(number, result)

if __name__ == "__main__":
    unittest.main()

