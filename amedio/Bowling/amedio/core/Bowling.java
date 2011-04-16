package core;

public class Bowling {
	public static int getPoints(String throwings) {
		int result = 0;
		int actResult = 0;

		for (int i = 0; i < throwings.length(); i++) {
			String actValue = String.valueOf(throwings.charAt(i));

			if ("-".equals(actValue)) {
				actResult = 0;
			} else if ("/".equals(actValue)) {
				actResult = 10 - actResult
						+ valueOfThrow(String.valueOf(throwings.charAt(i + 1)));
			} else {
				actResult = Integer.parseInt(actValue);
			}

			result += actResult;
		}

		return result;
	}

	private static int valueOfThrow(String throwValue) {
		int result = 0;
		result = Integer.parseInt(throwValue);
		return result;
	}
}
