package es.iagotb.kata.abril;

import java.util.ArrayList;

public class Try {

	private static final int DEFAULT_NUM_PINS = 10;
	private boolean isSpare = false;
	private boolean isStrike = false;
	private Integer pins_knocked;

	public Integer getPins_knocked() {
		return pins_knocked;
	}

	public void setPins_knocked(Integer pins_knocked) {
		this.pins_knocked = pins_knocked;
	}

	public Try() {
		// TODO Auto-generated constructor stub
		
	}
	
	public Try(char charAt) {
		switch(charAt){		
		
			case 'X':
			{
				isStrike = true;
				pins_knocked = DEFAULT_NUM_PINS;
				break;
			}
			case '/':
			{
				isSpare= true;
				break;
			}
			case '-':
			{
				pins_knocked = 0;
				break;
			}
			default:
			{			
				pins_knocked = Integer.valueOf(charAt);
				break;
			}
		}
	}

	public boolean isStrike() {
		return isStrike;
	}

	public void setStrike(boolean isStrike) {
		this.isStrike = isStrike;
	}

	public boolean isSpare() {
		return isSpare;
	}

	public void setSpare(boolean isSpare) {
		this.isSpare = isSpare;
	}
}
