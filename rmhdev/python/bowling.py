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
    if sequence == "":
        return 0
    score = 0
    frame = getFirstFrame(sequence)
    for pin in frame: 
        if pin == "-":
            score += 0
        elif pin == "X":
            score += 10
        elif pin == "/":
            score = 10
        else:
            score += int(pin)
    restOfSequence = removeFirstFrame(sequence)
    return score + getScore(restOfSequence)

def getFirstFrame(sequence):
    return sequence[:2]

def removeFirstFrame(sequence):
    if sequence[0] == "X":
        return sequence[1:]
    return sequence[2:]
