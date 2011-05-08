package com.japaz.katas.ab;

public class BowlingScorer {
	private int currentFrame = 0;
	private boolean secondRollInAFrame = false;
	private String sequenceOfRolls;
	private int score;

	public BowlingScorer(String sequenceOfRolls) {
		currentFrame = 0;
		secondRollInAFrame = false;
		score = 0;
		this.sequenceOfRolls = sequenceOfRolls;  
	}

	public int calculateScore() {
		int currentRoll = 0;

		while (currentFrame < 10) {
			int rollValue = sequenceOfRolls.charAt(currentRoll);
			
			switch(rollValue) {
			case 'X' :  score+=10;
						processNextRoll(currentRoll);
						processTheRollAfterNext(currentRoll);
						endFrame();
						break;
			case '9' :
			case '8' :
			case '7' :
			case '6' :
			case '5' :
			case '4' :
			case '3' :
			case '2' :
			case '1' :
			case '0' :  processNumericValue(rollValue);
						if (secondRollInAFrame) {
							endFrame();
						} else {
							secondRollInAFrame = true;
						}
						break;
			case '/' :  score=score+10-Character.digit(sequenceOfRolls.charAt(currentRoll-1),10);
						processNextRoll(currentRoll);
						endFrame();
						break;
			case '-' :  
					    endFrame();
						break;
			}
			currentRoll++;
		}
		return score;
	}

	private void processNextRoll(int currentRoll) {
		int rollValue = sequenceOfRolls.charAt(currentRoll+1);
		processXOrNumeric(rollValue);
	}
	
	private void processTheRollAfterNext(int currentRoll) {
		int rollValue = sequenceOfRolls.charAt(currentRoll+2);
		processXOrNumeric(rollValue);
		processSlash(currentRoll, rollValue);
	}

	private void processXOrNumeric(int rollValue) {
		switch (rollValue) {
		case 'X' : score+=10;
					break;
		case '9' :
		case '8' :
		case '7' :
		case '6' :
		case '5' :
		case '4' :
		case '3' :
		case '2' :
		case '1' :
		case '0' :  processNumericValue(rollValue);
					break;
		}
	}

	private void processNumericValue(int rollValue) {
		score+=Character.digit(rollValue, 10);
	}

	private void processSlash(int currentRoll, int rollValue) {
		switch (rollValue) {
		case '/' : score=score+10-Character.digit(sequenceOfRolls.charAt(currentRoll+1), 10);
					break;
		}
	}

	private void endFrame() {
		currentFrame++;
		secondRollInAFrame = false;
	}
}