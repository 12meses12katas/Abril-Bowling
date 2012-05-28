package kata

class BowlingGame {
    Collection<Integer> rolls = []

    void roll(int pins) {
        rolls << pins
    }

    int score() {
        def score = 0
        def roll = 0
        for (def frame = 1; frame <= 10; frame++) {
            if (isStrike(roll)) {
                score += 10 + strikeBonus(roll)
                roll += 1
            } else if (isSpare(roll)) {
                score += 10 + spareBonus(roll)
                roll += 2
            } else {
                score += normalFrameScore(roll)
                roll += 2
            }
        }
        return score
    }

    private int strikeBonus(int roll) {
        return normalFrameScore(roll + 1)
    }

    private int spareBonus(int roll) {
        return rolls[roll + 2]
    }

    private int normalFrameScore(int roll) {
        rolls[roll] + rolls[roll + 1]
    }

    private boolean isSpare(int roll) {
        return 10 == normalFrameScore(roll)
    }

    private boolean isStrike(int roll) {
        return 10 == rolls[roll]
    }

}
