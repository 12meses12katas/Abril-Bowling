#!/usr/bin/env python


def score(rolls):
    def get_value(i):
        if rolls[i] == 'X':
            return 10
        if rolls[i] == '-':
            return 0
        if rolls[i] == '/':
            return 10 - get_value(i-1)
        return int(rolls[i])

    result = 0
    for i, roll in enumerate(rolls):
        if roll == 'X':
            result += 10 + get_value(i+1) + get_value(i+2)
            if i == (len(rolls) - 3):
                break
        elif roll == '/':
            result += get_value(i) + get_value(i+1)
            if i == (len(rolls) - 2):
                break
        else:
            result += get_value(i)
    return result

if __name__ == '__main__':
    assert score('XXXXXXXXXXXX') == 300
    assert score('9-9-9-9-9-9-9-9-9-9-') == 90
    assert score('5/5/5/5/5/5/5/5/5/5/5') == 150
    assert score('--34--1/425/XX112/3') == 95
    assert score("--------------------") == 0
    assert score("11111111111111111111") == 20
    assert score("33333333333333333333") == 60
    assert score("123456789/123456789/1") == 94
    assert score("3/3/3/3/3/3/3/3/3/3/3") == 130
    assert score("4/4/4/4/4/4/4/4/4/4/4") == 140
    assert score("XXX--------------") == 60
    assert score("14456/5/X017/6/X2/6") == 133
    assert score("X7/9X-88/-6XXX81") == 167
    assert score("XXXXXXXXX--") == 240
    assert score("X34----------------") == 24
    assert score("X7/9-X-88/-6XX72") == 136

    assert score('XXXXXXXXXXXX') == 300
    assert score('--------------------') == 0
    assert score('9-9-9-9-9-9-9-9-9-9-') == 90
    assert score('5/5/5/5/5/5/5/5/5/5/5') == 150
    assert score('729-9-9-9/9/XX8-71'    ) == 137
    assert score('-69/X63719-5/X7/7-'    ) == 135
    assert score('1/-99-6-157-8/7-9-XX-' ) == 100
    assert score('9/9-8--/72-481-63-8-'  ) == 92
    assert score('357-3/-333X9/8/629/6'  ) == 112
    assert score('8/X71718-9-XX9/X7-'    ) == 157
    assert score('9/8/X7/53348/-78/-/8'  ) == 133
    assert score('XX6-XX-97-7-9/9/7'     ) == 146
    assert score('9/XX7/639/X9/9/9-'     ) == 179
    assert score('349-3--95/X3--1X-7'    ) == 82
    assert score('9/449-9-9/9-9-9-9-X8-' ) == 113
    print ':-D'
