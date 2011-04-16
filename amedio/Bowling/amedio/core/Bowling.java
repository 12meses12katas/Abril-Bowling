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
			} else if ("X".equals(actValue)) {
				if (i + 1 >= throwings.length() || i + 2 >= throwings.length())
					break;
				actResult = 10
						+ valueOfThrow(String.valueOf(throwings.charAt(i + 1)))
						+ valueOfThrow(String.valueOf(throwings.charAt(i + 2)));
			} else {
				if (i > 0 && i == (throwings.length() - 1)
						&& "/".equals(String.valueOf(throwings.charAt(i - 1))))
					break;
				actResult = Integer.parseInt(actValue);
			}

			result += actResult;
		}

		return result;
	}

	private static int valueOfThrow(String throwValue) {
		int result = 0;
		if ("X".equals(throwValue)) {
			result = 10;
		} else {
			result = Integer.parseInt(throwValue);
		}
		return result;
	}
}
