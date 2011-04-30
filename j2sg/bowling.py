#!/usr/bin/env python
# -*- coding: utf-8 -*-

# 12 Meses 12 Katas - Abril Bowling
# By j2sg - jjslzgc@gmail.com

class Bowling:
	def __init__(self):
		self.last='' # Last Throw
	
	def play(self,rolls): # Play to Ten-Pin Bowling
		if rolls is '': return 0
		score,turns=self.getScore(rolls[0])
		self.last=rolls[0]
		for nturn in range(0,turns):
			if nturn<len(rolls[1:]):
				score+=(self.getScore(rolls[1+nturn]))[0]
		return score+[0,self.play(rolls[1:])][turns==0 or (turns>0 and turns<len(rolls[1:]))]

	def getScore(self,throw): # Return tuple with (score,#turns)
		if throw is 'X': return (10,2)
		elif throw is '/': return (10-(self.getScore(self.last))[0],1)
		elif throw in [str(n) for n in range(1,10)]: return (int(throw),0)
		elif throw is '-': return (0,0)
