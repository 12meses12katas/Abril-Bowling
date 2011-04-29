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
    if sequence[:2] == "-1":
        return 1
    if sequence[:2] == "-2":
        return 2
    if sequence[:2] == "-3":
        return 3
    if sequence[:2] == "-/":
        return 10
    if sequence[0] == "-":
        return 0
    if sequence[0] == "X":
        return 10

    return int(sequence[0])
