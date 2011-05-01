package net.corbacho.AbrilBowling;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BowlingTest {

    Bowling game = null;

    @Before
    public void initialize() throws Exception {
        game = new Bowling();
    }

    @Test
    public void testSlash() {
        int out = game.getScore("--------------------");
        assertEquals(0, out);
    }

    @Test
    public void testOne() {
        int out = game.getScore("11111111111111111111");
        assertEquals(20, out);
    }

    @Test
    public void testTwo() {
        int out = game.getScore("22222222222222222222");
        assertEquals(40, out);
    }

    @Test
    public void testFour() {
        int out = game.getScore("44444444444444444444");
        assertEquals(80, out);
    }

    @Test
    public void testSpare() {
        int out = game.getScore("-/-/-/-/-/-/-/-/-/-/-");
        assertEquals(100, out);
    }

    
    @Test
    public void testSetLongFrame1() {
        assertEquals(90, game.getScore("9-9-9-9-9-9-9-9-9-9-"));
    }

    @Test
    public void testSetLongFrame2() {
        assertEquals(150, game.getScore("5/5/5/5/5/5/5/5/5/5/5"));
    }

    @Test
    public void testSetLongFrame3() {
        assertEquals(148, game.getScore("3/5/5/5/5/5/5/5/5/5/3"));
    }

    @Test
    public void testSetLongFrame4() {
        assertEquals(144, game.getScore("1/2/3/4/5/6/7/8/9/-/-"));
    }
    
    @Test
    public void testSetLongFrame5() {
        assertEquals(300, game.getScore("XXXXXXXXXXXX"));
    }

    @Test
    public void testSetLongFrame6() {
        assertEquals(140, game.getScore("X9-X9-X9-X9-X9-"));
    }
   
    @Test
    public void testSetLongFrame7() {
        assertEquals(195, game.getScore("X5/X5/X5/X5/X5/5"));
    }

    @Test
    public void testSetLongFrame8() {
        assertEquals(140, game.getScore("X54X54X54X54X54"));
    }
    
    @Test
    public void testSetLongframe9() {
        assertEquals(155, game.getScore("-/5/X-/5/X-/5/X--"));
    }


}
