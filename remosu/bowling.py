#!/usr/bin/env python

def get_value(rolls, i):
    if rolls[i] == 'X':
        return 10
    if rolls[i] == '-':
        return 0
    if rolls[i] == '/':
        return 10 - get_value(rolls, i-1)
    return int(rolls[i])

def score(rolls):
    result = 0
    for i, roll in enumerate(rolls):
        if roll == 'X':
            result += 10 + get_value(rolls, i+1) + get_value(rolls, i+2)
            if i == (len(rolls) - 3):
                break
        elif roll == '/':
            result += get_value(rolls, i) + get_value(rolls, i+1)
            if i == (len(rolls) - 2):
                break
        else:
            result += get_value(rolls, i)
    return result

if __name__ == '__main__':
    assert score('XXXXXXXXXXXX') == 300
    assert score('9-9-9-9-9-9-9-9-9-9-') == 90
    assert score('5/5/5/5/5/5/5/5/5/5/5') == 150
