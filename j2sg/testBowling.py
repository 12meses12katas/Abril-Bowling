#!/usr/bin/env python
# -*- coding: utf-8 -*-

# 12 Meses 12 Katas - Abril Bowling
# By j2sg - jjslzgc@gmail.com

import unittest, bowling

class TestBowling(unittest.TestCase):
	# Test Cases
	tcs={	'XXXXXXXXXXXX':300,
		'9-9-9-9-9-9-9-9-9-9-':90,
<<<<<<< HEAD
		'5/5/5/5/5/5/5/5/5/5/5':150,
                '--------------------':0,
		'-/-/-/-/-/-/-/-/-/-/-':100,
		'17524181276236547114':77,
		'436/5-8-7/X726/X65':124}
=======
		'5/5/5/5/5/5/5/5/5/5/5':150}
>>>>>>> upstream/master

	# Set Software Under Test
	def setUp(self):
		self.sut=bowling.Bowling()
	
	# Unit Testing
	def test_play(self):
		for key in self.tcs:
			self.assertEqual(self.tcs[key],self.sut.play(key))

if __name__ == '__main__':
	unittest.main()

