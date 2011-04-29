#!/usr/local/bin/python
# coding: utf-8

"""
Kata trying to resolve the Bowling problem using TDD
"""

__author__ = "Rober Martín H"
__version__ = "0.1"
__date__ = "2011-04-30"
__copyright__ = "Copyright (c) 2011 Rober Martín H"
__license__ = "MIT License"

def getScore(sequence): 
    score = 0
    for i in range(10):
        frame = getFirstFrame(sequence)
        sequence = removeFirstPins(sequence)
        score += getScoreOfFrame(frame)
    return score

def getFirstFrame(sequence):
    if isSpare(sequence) or isStrike(sequence):
        return sequence[:3]
    return sequence[:2]

def removeFirstPins(sequence):
    if isStrike(sequence):
        return sequence[1:]
    return sequence[2:]

def getScoreOfFrame(frame):
    score = last = 0
    for pin in frame: 
        if pin == "X":
            score += 10
        elif pin == "/":
            score += 10 - last
        elif pin != "-":
            last = int(pin)
            score += last
    return score

def isStrike(frameOrSequence):
    return frameOrSequence[0] == "X"

def isSpare(frameOrSequence): 
    return len(frameOrSequence) > 1 and frameOrSequence[1] == "/"
