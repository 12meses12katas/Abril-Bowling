package com.example.bowling;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RecursivelyBowlingTest
{
	@Test
	public void ningunTurno() throws Exception
	{
		assertEquals(0, RecursivelyBowling.getScore(""));
	}

	@Test
	public void unTurno() throws Exception
	{
		assertEquals(0, RecursivelyBowling.getScore("--"));
		assertEquals(10, RecursivelyBowling.getScore("X"));
		assertEquals(9, RecursivelyBowling.getScore("63"));
		assertEquals(10, RecursivelyBowling.getScore("-/"));
		assertEquals(10, RecursivelyBowling.getScore("6/"));
	}

	@Test
	public void dosTurnos() throws Exception
	{
		assertEquals(10, RecursivelyBowling.getScore("--X"));
		assertEquals(30, RecursivelyBowling.getScore("XX"));
		assertEquals(20, RecursivelyBowling.getScore("3/5"));
		assertEquals(30, RecursivelyBowling.getScore("X3/"));
	}

	@Test
	public void cuatroTurnos() throws Exception
	{
		assertEquals(20 + 20 + 20 + 10, RecursivelyBowling.getScore("X3/X2/"));
		assertEquals(14 + 4 + 14 + 9, RecursivelyBowling.getScore("X4-5/45"));
		assertEquals(30 + 30 + 20 + 10, RecursivelyBowling.getScore("XXXX"));
		assertEquals(10 + 10, RecursivelyBowling.getScore("X--X"));
		assertEquals(14 + 2 + 2 + 2 + 8 + 0 + 10, RecursivelyBowling.getScore("X222/-/"));
	}

	@Test
	public void kataSuggestedTest() throws Exception
	{
		assertEquals(90, RecursivelyBowling.getScore("9-9-9-9-9-9-9-9-9-9-"));
	}

	@Test
	public void kataExtraRollsSuggestedTest() throws Exception
	{
		assertEquals(300, RecursivelyBowling.getScore("XXXXXXXXXXXX"));
		assertEquals(150, RecursivelyBowling.getScore("5/5/5/5/5/5/5/5/5/5/5"));
	}
}
