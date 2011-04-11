package net.corbacho.AbrilBowling;

public class Bowling {

	public static int GetCharValue(char myChar){

		Character myCharacter = myChar;
		int value = 0;
		if (myCharacter.equals('X') || myCharacter.equals('/')) {
			value = 10;
		} else if (myCharacter.equals('-')) {
			value = 0;
		} else {
			value = Integer.parseInt(myCharacter.toString());
		}
		return value;
	}


	public static int GetValue(String myString) {

		int score = 0;
		for (int i = 0; i < myString.length(); i++) {
			//TODO Modificar esta condicion
			if ((myString.length() > 10) && (i < myString.length()-2)) {
				Character myCharacter = myString.charAt(i);

				if (myCharacter.equals('X') || myCharacter.equals('/') ) {
					score += 10;

					if (i + 1 < myString.length()) {
						score += GetCharValue(myString.charAt(i+1));

						if ((myCharacter.equals('X')) && (i+2<myString.length())) {
							score += GetCharValue(myString.charAt(i+2));
						}
					}

				} else if (myCharacter.equals('-')) {
					score += 0;
				} else {
					score += Integer.parseInt(myCharacter.toString());
				}

			}

			}

		return score;
	}

}
