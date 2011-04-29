#!/usr/local/bin/python
# coding: utf-8

"""
Kata trying to resolve the Bolwling problem using TDD
"""

__author__ = "Rober Martín H"
__version__ = "0.1"
__date__ = "2011-04-26"
__copyright__ = "Copyright (c) 2011 Rober Martín H"
__license__ = "MIT License"

def getScore(sequence):
    score = 0
    for pin in sequence[:2]: 
        if pin == "-":
            score += 0
        elif pin == "X":
            score += 10
        elif pin == "/":
            score = 10
        else:
            score += int(pin)
    return score 
