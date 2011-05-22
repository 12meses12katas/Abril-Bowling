package com.example.bowling;

public class RecursivelyBowling
{
	private static final int ROLLS = 10;
	private static final char MISTAKE = '-';
	private static final char STRIKE = 'X';
	private static final char SPARE = '/';
	private static final char ZERO = '0';

	public static int getScore(String rolls)
	{
		int indexOfExtraRolls = indexOfExtraRolls(rolls);

		return getRollScore(rolls.replace(MISTAKE, ZERO), 0, indexOfExtraRolls);
	}

	private static int getRollScore(String rolls, int current, int indexOfExtraRolls)
	{
		if (noMoreRolls(rolls, current))
			return 0;
		else
			return getPointsAt(rolls, current) * getFactor(rolls, current, indexOfExtraRolls)
					+ getRollScore(rolls, current + 1, indexOfExtraRolls);
	}

	private static boolean noMoreRolls(String rolls, int current)
	{
		return current >= rolls.length();
	}

	private static int getPointsAt(String rolls, int current)
	{
		final int MAX_POINTS = 10;

		char rollPrev = getRollAt(rolls, current - 1);
		char roll = getRollAt(rolls, current);

		if (roll == STRIKE)
			return MAX_POINTS;
		else if (roll == SPARE)
			return MAX_POINTS - atoi(rollPrev);
		else
			return atoi(roll);
	}

	private static int getFactor(String rolls, int current, int indexOfExtraRolls)
	{
		int bonusFactor = getBonusFactor(rolls, current);

		if (isExtraRoll(current, indexOfExtraRolls))
			return bonusFactor - (1 + (current - indexOfExtraRolls));
		else
			return bonusFactor;
	}
	
	private static int getBonusFactor(String rolls, int current)
	{
		char rollPrevPrev = getRollAt(rolls, current - 2);
		char rollPrev = getRollAt(rolls, current - 1);

		if (hasDoubleBonus(rollPrevPrev, rollPrev))
			return 3;
		else if (hasBonus(rollPrevPrev, rollPrev))
			return 2;
		else
			return 1;
	}

	private static int indexOfExtraRolls(String rolls)
	{
		double currentFrame = 0.0;
		for (int i = 0; i < rolls.length(); i++)
		{
			currentFrame += getFrameDelta(rolls.charAt(i));
			
			if (currentFrame > ROLLS)
				return i;
		}		
		return rolls.length();
	}

	private static double getFrameDelta(char roll)
	{
		if (roll == STRIKE)
			return 1;
		else
			return 0.5;
	}

	private static boolean isExtraRoll(int current, int indexOfExtraRolls)
	{
		return current >= indexOfExtraRolls;
	}

	private static boolean hasDoubleBonus(char rollPrevPrev, char rollPrev)
	{
		return rollPrevPrev == STRIKE && rollPrev == STRIKE;
	}

	private static boolean hasBonus(char rollPrevPrev, char rollPrev)
	{
		return rollPrevPrev == STRIKE || rollPrev == STRIKE || rollPrev == SPARE;
	}

	private static char getRollAt(String rolls, int current)
	{
		if (current < 0)
			return ZERO;

		return rolls.charAt(current);
	}

	private static int atoi(char roll)
	{
		return roll - ZERO;
	}
}
