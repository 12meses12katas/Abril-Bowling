package es.iagotb.kata.abril;

import java.util.ArrayList;

public class Turno {
	private ArrayList<Try> tries;

	private Integer points;

	public ArrayList<Try> getTries() {
		return tries;
	}

	public void setTries(ArrayList<Try> tries) {
		this.tries = tries;
	}

	public Turno() {
		// TODO Auto-generated constructor stub
		tries = new ArrayList<Try>(2);
	}

	public Turno(char charAt, char char2) {
		tries = new ArrayList<Try>(2);
		tries.add(new Try(charAt));
		switch (charAt) {
		case 'X': {
			
			break;
		}
		case '/': {
			tries.add(new Try(char2));
			break;
		}
		case '-': {
			tries.add(new Try(char2));
			break;
		}
		default: {
			tries.add(new Try(char2));
			break;
		}
		}
	}

	public Integer calc_Points(int i, ArrayList<Turno> turnos) {
		
		
		return null;
	}
}
