package net.corbacho.AbrilBowling;

import static org.junit.Assert.*;

import org.junit.Test;

public class BowlingTest {

	@Test
	public void testSetXReturn10() {
		int out = Bowling.GetValue("X");
		assertEquals(10, out);
	}

	@Test
	public void testSetScoreReturn0() {
		int out = Bowling.GetValue("-");
		assertEquals(0, out);
	}

	@Test
	public void testSetSlashReturn10() {
		int out = Bowling.GetValue("/");
		assertEquals(10, out);
	}

	@Test
	public void testSet1Return1() {
		assertEquals(1, Bowling.GetValue("1"));
	}

	@Test
	public void testSet2Return2() {
		assertEquals(2, Bowling.GetValue("2"));
	}

	@Test
	public void testSetScorePlusNineReturnNine() {
		assertEquals(9, Bowling.GetValue("-9"));
	}

	@Test
	public void testSetScorePlusFiveReturnFive() {
		assertEquals(5, Bowling.GetValue("-5"));
	}

	@Test
	public void testSetScorePlusXReturnTen() {
		assertEquals(10, Bowling.GetValue("-X"));
	}

	@Test
	public void testSetScorePlusSlashReturnTen() {
		assertEquals(10, Bowling.GetValue("-/"));
	}

	@Test
	public void testSetXPlusXReturn30() {
		assertEquals(30, Bowling.GetValue("XX"));
	}

	@Test
	public void testSetXPlusSlashReturn30() {
		assertEquals(30, Bowling.GetValue("X/"));
	}

	@Test
	public void testSetXPlus9Return28() {
		assertEquals(28, Bowling.GetValue("X9"));
	}

	@Test
	public void testSetXXXReturn60() {
		assertEquals(60, Bowling.GetValue("XXX"));
	}

	@Test
	public void testSetXSlashXReturn60() {
		assertEquals(60, Bowling.GetValue("X/X"));
	}

	@Test
	public void testSetSlashSlashXReturn50() {
		assertEquals(50, Bowling.GetValue("//X"));
	}

	@Test
	public void testSetSlashXSlashXReturn80() {
		assertEquals(80, Bowling.GetValue("/X/X"));
	}

	@Test
	public void testSetScoreXSlashXReturn60() {
		assertEquals(60, Bowling.GetValue("-X/X"));
	}

	@Test
	public void testSetScore9SlashScoreReturn19() {
		assertEquals(19, Bowling.GetValue("-9/-"));
	}

	@Test
	public void testSetLongFrame1() {
		assertEquals(300, Bowling.GetValue("XXXXXXXXXXXX"));
	}

	/*@Test
	public void testSetLongFrame2() {
		assertEquals(90, Bowling.GetValue("9-9-9-9-9-9-9-9-9-9-"));
	}*/

	/*@Test
	public void testSetLongFrame3() {
		assertEquals(150,Bowling.GetValue("5/5/5/5/5/5/5/5/5/5/5"));
	}*/


}
