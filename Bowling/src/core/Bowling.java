package core;

public class Bowling {
	public static int getPoints(String throwings) {

		int result = 0;
		String valueAt = "";
		for (int i = 0; i < throwings.length(); i++) {
			valueAt = String.valueOf(throwings.charAt(i));

			if ("X".equals(valueAt)) {

			} else if ("/".equals(valueAt)) {

			} else if ("-".equals(valueAt)) {

			} else {
				result += Integer.parseInt(valueAt);
			}
		}

		return result;
	}
}
