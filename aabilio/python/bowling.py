#!/usr/bin/python
# -*- coding: utf-8 -*-

# 12meses12katas: Abril Bowling
# aabilio - aabilio@gmail.com

import sys

class Bowling(object):
    def __init__(self, scores=None):
        self.__scores = scores
    def getScores(self):
        return self.__scores
    def setScores(self, scores):
        self.__scores = scores
    scores = property(getScores, setScores)
    
    def __isNumOrGuion(self, x):
        '''
            Comprueba si el parámetro x que se le pasa es un número de 1 a 9 o un '-'
        '''
        for i in range(1, 10):
            if x == str(i) or x == '-': return True
        return False

    def __buscarFinal(self, s):
        '''
            return: la posición final del último tira "normal"
        '''
        if s[-3] is 'X': return len(s)-2
        elif s[-2] is '/': return len(s)-1
        else: return len(s)
    
    def __num(self, x):
        '''
            return: el número correspondiente (10 si X ó /, 0 si -,...)
        '''
        if x is 'X' or x is '/': return 10
        elif x is '-': return 0
        else: return int(x)
        
    def play(self):
        '''
            return: El resultado final de la suma. No se valida ninguno de los datos inroducidos.
        '''
        rsult = 0
        s = self.__scores
        end = self.__buscarFinal(s)
        
        for i in range(0, end):
            if s[i] is 'X':
                rsult += 10 + self.__num(s[i+1]) + self.__num(s[i+2])
            elif s[i] is '/':
                rsult += 10 + self.__num(s[i+1])
            else:
                try: # Chapucilla para que sea más legible
                    if s[i+1] is '/':
                       pass
                    else:
                        rsult += self.__num(s[i])
                except IndexError:
                    rsult += self.__num(s[i])
        
        return rsult
        
    
if __name__ == "__main__":
    if len(sys.argv) < 2:
        sys.exit("[!!!] You must enter punctuation line")
    elif len(sys.argv) > 2:
        sys.exit("[!!!] Enter only the score line")
    else:
        partida = Bowling()
        partida.scores = sys.argv[1]
        tgs = partida.play()
        
        print tgs
