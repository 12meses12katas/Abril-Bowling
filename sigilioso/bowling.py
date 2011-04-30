# -*- coding: utf-8 -*-
import sys

# 12 Meses 12 Katas - Bowling
# Author: sigilioso <sigilioso@gmail.com>

def get_score(rolls):
    """
    Get total score for a sequence of rolls for one line of American Ten-Pin
    Bowling
    """
    def nbowls(throw):
        # How many bowls have been thrown in a throw
        if throw == 'X' or throw == '/': 
            return 10
        elif throw == '-': 
            return 0
        else :
            return int(throw)

    score = 0
    dropped = {}
    nframe = 10
    # Do we have bonus?
    if rolls[-3] == 'X':
        dropped[11], dropped[12] = nbowls(rolls[-2]), nbowls(rolls[-1])
        rolls = rolls[:-2]
    elif rolls[-2] == '/':
        dropped[11] = nbowls(rolls[-1])
        rolls = rolls[:-1]
    # Get the total score 
    i = len(rolls)-1
    while(i > -1):
        if rolls[i] == 'X': # Strike!
            dropped[nframe] = 10 
            i -= 1
            score += 10 + dropped[nframe+1] + dropped[nframe+2]
        elif rolls[i] == '/': # Spare :-)
            dropped[nframe] = nbowls(rolls[i-1])
            i -= 2
            score += 10 + dropped[nframe]
        else :
            dropped[nframe] = nbowls(rolls[i-1]) + nbowls(rolls[i])
            score += dropped[nframe]
            i -= 2
        nframe -= 1
    return score

if __name__ == "__main__":
    print 'Total Score: %s' % get_score(sys.argv[1])
