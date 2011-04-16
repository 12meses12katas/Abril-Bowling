package core;

import junit.framework.Assert;

import org.junit.Test;

public class BowlingTest {

	@Test
	public void testGetPoints() {
		int expected = 0;
		int actual = Bowling.getPoints("");
		Assert.assertEquals(expected, actual);
		
		expected = 20;
		actual = Bowling.getPoints("11111111111111111111");
		Assert.assertEquals(expected, actual);
		
		expected = 10;
		actual = Bowling.getPoints("1-1-1-1-1-1-1-1-1-1-");
		Assert.assertEquals(expected, actual);
		
		expected = 150;
		actual = Bowling.getPoints("5/5/5/5/5/5/5/5/5/5/5");
		Assert.assertEquals(expected, actual);
		
		/*expected = 300;
		actual = Bowling.getPoints("XXXXXXXXXXXX");
		Assert.assertEquals(expected, actual);
		
		expected = 90;
		actual = Bowling.getPoints("9-9-9-9-9-9-9-9-9-9-");
		Assert.assertEquals(expected, actual);*/
	}

}
