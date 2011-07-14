package kata;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {

    private List<Integer> rolls = new ArrayList<Integer>();

    public void roll(int pins) {
        rolls.add(pins);
    }

    public Integer score() {
        int score = 0;
        int roll = 0;
        for (int frame = 1; frame <= 10; frame++) {
            if (isStrike(roll)) {
                score += 10 + strikeBonus(roll);
                roll += 1;
            } else if (isSpare(roll)) {
                score += 10 + spareBonus(roll);
                roll += 2;
            } else {
                score += normalFrameScore(roll);
                roll += 2;
            }
        }
        return score;
    }

    private boolean isStrike(int roll) {
        return 10 == rolls.get(roll);
    }

    private boolean isSpare(int roll) {
        return 10 == normalFrameScore(roll);
    }

    private int strikeBonus(int roll) {
        return normalFrameScore(roll + 1);
    }

    private Integer spareBonus(int roll) {
        return rolls.get(roll + 2);
    }

    private int normalFrameScore(int roll) {
        return rolls.get(roll) + rolls.get(roll + 1);
    }
}
