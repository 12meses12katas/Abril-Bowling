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
	public void testSetXReturn10() {
		int out = game.getScore("X");
		assertEquals(10, out);
	}

	@Test
	public void testSetDashReturn0() {
		int out = game.getScore("-");
		assertEquals(0, out);
	}

	@Test
	public void testSetSlashReturn10() {
		int out = game.getScore("/");
		assertEquals(10, out);
	}

	@Test
	public void testSet1Return1() {
		assertEquals(1, game.getScore("1"));
	}

	@Test
	public void testSet2Return2() {
		assertEquals(2, game.getScore("2"));
	}

	@Test
	public void testSetDashNineReturnNine() {
		assertEquals(9, game.getScore("-9"));
	}

	@Test
	public void testSetDashFiveReturnFive() {
		assertEquals(5, game.getScore("-5"));
	}

	@Test
	public void testSetDashDashReturnTen() {
		assertEquals(0, game.getScore("--"));
	}

	@Test
	public void testSetDashSlashReturnTen() {
		assertEquals(10, game.getScore("-/"));
	}

	@Test
	public void testSetXXReturn30() {
		assertEquals(30, game.getScore("XX"));
	}

	@Test
	public void testSetX9DashReturn28() {
		assertEquals(28, game.getScore("X9-"));
	}

	@Test
	public void testSetXXXReturn60() {
		assertEquals(60, game.getScore("XXX"));
	}

	@Test
	public void testSetXFiveSlashXReturn50() {
		assertEquals(50, game.getScore("X5/X"));
	}

	@Test
	public void testSetDashSlashXReturn30() {
		assertEquals(30, game.getScore("-/X"));
	}

	@Test
	public void testSetDashSlashFiveSlashXReturn60() {
		assertEquals(45, game.getScore("-/5/X"));
	}

    @Test
    public void testSetXFiveFiveReturn26() {
        assertEquals(26, game.getScore("X53"));
    }

    @Test
    public void testSetThreeSlash10() {
        assertEquals(10, game.getScore("3/"));
    }

    
    @Test
    public void testSetXThreeSlashReturn30() {
        assertEquals(30, game.getScore("X3/"));
    }

    
	@Test
	public void testSetDash94SlashDashDashReturn19() {
		assertEquals(19, game.getScore("-94/--"));
	}

    @Test
    public void testSetLongFrame1() {
        assertEquals(90, game.getScore("9-9-9-9-9-9-9-9-9-9-"));
    }

    @Test
    public void testSetLongFrame2() {
        assertEquals(150,game.getScore("5/5/5/5/5/5/5/5/5/5/5"));
    }

	
	@Test
	public void testSetLongFrame3() {
		assertEquals(300, game.getScore("XXXXXXXXXXXX"));
	}



}
