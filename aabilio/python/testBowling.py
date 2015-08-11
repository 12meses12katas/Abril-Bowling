#!/usr/bin/env python
# -*- coding: utf-8 -*-

# 12 Meses 12 Katas - Abril Bowling
# By j2sg - jjslzgc@gmail.com

import unittest, bowling

class TestBowling(unittest.TestCase):
    # Test Cases
    tcs={	'XXXXXXXXXXXX':300,
		'9-9-9-9-9-9-9-9-9-9-':90,
		'5/5/5/5/5/5/5/5/5/5/5':150}

    # Set Software Under Test
    def setUp(self):
        self.sut=bowling.Bowling()

	# Unit Testing
    def test_play(self):
        for key in self.tcs:
            self.sut.scores = key # Para adaptarlo a mi función play()
            self.assertEqual(self.tcs[key],self.sut.play())

if __name__ == '__main__':
    unittest.main()
