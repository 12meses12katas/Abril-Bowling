package es.iagotb.kata.abril;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.text.Segment;

import es.iagotb.kata.abril.Turno;


public class BowlingGame {

	private static final int DEFAULT_NUM_TURNOS = 10;
	private ArrayList<Turno> turnos;
	private Integer total_points;
	
	public BowlingGame(Integer num_turnos) {
		// TODO Auto-generated constructor stub
		turnos = new ArrayList<Turno>(num_turnos);
	}
	
	public BowlingGame(Integer num_turnos, String sequence_rolls) {
		// TODO Auto-generated constructor stub
		turnos = new ArrayList<Turno>(sequence_rolls.length());
		parseSequence(sequence_rolls,turnos);
		
		calc_total_points();
	}
	
	private void calc_total_points() {
		int i = 0;
		for (Turno turno: turnos) {
			total_points = turno.calc_Points(i,turnos);
			i++;
			
		}
		
	}

	private void parseSequence(String sequence_rolls, ArrayList<Turno> turnos) {
		for (int i = 0; i < sequence_rolls.length()/2; i+=2) {
			turnos.add(new Turno(sequence_rolls.charAt(i-1),sequence_rolls.charAt(i)));
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BowlingGame juego = new BowlingGame(DEFAULT_NUM_TURNOS);
		
	}

}
