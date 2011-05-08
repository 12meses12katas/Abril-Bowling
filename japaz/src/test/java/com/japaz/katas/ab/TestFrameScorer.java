package com.japaz.katas.ab;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestFrameScorer {
	@Test
	public void test_12_Strikes_Is_300() {
		String sequenceOfRolls = "XXXXXXXXXXXX";
		int score = new BowlingScorer(sequenceOfRolls).calculateScore();
		Assert.assertEquals("12 Strikes must be 300 points", 300, score);
	}
	
	@Test
	public void test_10_Pairs_Of_9_And_Miss_Is_90() {
		String sequenceOfRolls = "9-9-9-9-9-9-9-9-9-9-";
		int score = new BowlingScorer(sequenceOfRolls).calculateScore();
		Assert.assertEquals("10 Pairs of 9 and miss must be 90", 90, score);
	}
	
	@Test
	public void test_10_Pairs_Of_5_And_Spare_With_Final_5_Is_150() {
		String sequenceOfRolls = "5/5/5/5/5/5/5/5/5/5/5";
		int score = new BowlingScorer(sequenceOfRolls).calculateScore();
		Assert.assertEquals("10 Pairs of 5 and spare, with a final 5 must be 150", 150, score);
	}

	@Test
	public void test_Strikes_Must_Count_Next_Two_Shoots_Double() {
		String sequenceOfRolls = "X535-5-5-5-5-5-5-5-";
		int score = new BowlingScorer(sequenceOfRolls).calculateScore();
		Assert.assertEquals(66, score);
	}

	@Test
	public void test_Strikes_Must_Count_Next_Two_Shoots_Double_Even_A_Miss() {
		String sequenceOfRolls = "X5-5-5-5-5-5-5-5-5-";
		int score = new BowlingScorer(sequenceOfRolls).calculateScore();
		Assert.assertEquals(60, score);
	}

	@Test
	public void test_Strikes_Must_Count_Next_Two_Shoots_Double_Even_An_Spare() {
		String sequenceOfRolls = "X5/5-5-5-5-5-5-5-5-";
		int score = new BowlingScorer(sequenceOfRolls).calculateScore();
		Assert.assertEquals(75, score);
	}

	@Test
	public void test_Strikes_Must_Count_Next_Two_Shoots_Double_Even_A_Miss_At_The_Last() {
		String sequenceOfRolls = "1-1-1-1-1-1-1-1-1-X5-";
		int score = new BowlingScorer(sequenceOfRolls).calculateScore();
		Assert.assertEquals(24, score);
	}

	@Test
	public void test_Strikes_Must_Count_Next_Two_Shoots_Double_Even_An_Spare_At_The_Last() {
		String sequenceOfRolls = "1-1-1-1-1-1-1-1-1-X5/";
		int score = new BowlingScorer(sequenceOfRolls).calculateScore();
		Assert.assertEquals(29, score);
	}
}
