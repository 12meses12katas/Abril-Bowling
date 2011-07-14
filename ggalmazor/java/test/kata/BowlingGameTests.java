package kata;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class BowlingGameTests {

    private BowlingGame bg;

    @Before
    public void setUp() {
        bg = new BowlingGame();
    }

    @Test
    public void gutter_game_scores_zero() {
        rollMany(bg, 20, 0);
        assertThat(bg.score(), is(0));
    }

    @Test
    public void all_ones_should_score_twenty() {
        rollMany(bg, 20, 1);
        assertThat(bg.score(), is(20));
    }

    @Test
    public void a_spare_should_sum_extra_score_of_the_next_rolls_pins() {
        rollSpare();
        rollMany(bg, 17, 0);
        assertThat(bg.score(), is(16));
    }

    @Test
    public void a_strike_should_sum_extra_score_of_the_next_two_rolls_pins() {
        rollStrike();
        rollMany(bg, 16, 0);
        assertThat(bg.score(), is(26));
    }

    private void rollStrike() {
        rollOne(bg, 10);
        rollOne(bg, 5);
        rollOne(bg, 3);
    }

    private void rollSpare() {
        rollOne(bg, 4);
        rollOne(bg, 6);
        rollOne(bg, 3);
    }

    private void rollMany(BowlingGame bg, int rolls, int pins) {
        for (int roll = 0; roll < rolls; roll++) {
            rollOne(bg, pins);
        }
    }

    private void rollOne(BowlingGame bg, int pins) {
        bg.roll(pins);
    }
}
