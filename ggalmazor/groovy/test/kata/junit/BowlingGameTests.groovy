package kata.junit

import org.junit.Test
import org.junit.Before
import static org.junit.Assert.assertThat
import static org.hamcrest.CoreMatchers.*

import kata.BowlingGame

class BowlingGameTests {
    def bg

    @Before
    void setUp() {
        bg = new BowlingGame()
    }

    @Test
    void "gutter game should score zero"() {
        rollMany bg, 20, 0
        assertThat bg.score(), is(0)
    }

    @Test
    void "an all ones game should score 20"() {
        rollMany bg, 20, 1
        assertThat bg.score(), is(20)
    }

    @Test
    void "one spare should score extra the next roll's pins"() {
        rollSpare bg
        rollMany bg, 17, 0
        assertThat bg.score(), is(16)
    }

    @Test
    void "one strike should score extra the next two rolls' pins"() {
        rollStrike bg
        rollMany bg, 16, 0
        assertThat bg.score(), is(26)
    }

    private def rollStrike(BowlingGame bg) {
        rollOne bg, 10
        rollOne bg, 5
        rollOne bg, 3
    }

    private def rollSpare(BowlingGame bg) {
        rollOne bg, 4
        rollOne bg, 6
        rollOne bg, 3
    }

    private void rollMany(BowlingGame bg, int rolls, int pins) {
        for (int roll = 0; roll < rolls; roll++)
            rollOne bg, pins
    }

    private void rollOne(BowlingGame bg, int pins) {
        bg.roll pins
    }
}