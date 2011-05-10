import junit.framework.TestCase
/**
 * Created by IntelliJ IDEA.
 * User: ruben
 * Date: 30/04/11
 * Time: 10:16
 * To change this template use File | Settings | File Templates.
 */
class BowlingTest extends TestCase{


    public void test_Simple_Pins_Knock_Down(){

        assertEquals(2 , new Frame(rollSecuence : "11").pinsKnockDown() )
        assert new Frame(rollSecuence : "22").pinsKnockDown() == 4

    }

    public void test_simple_Pins_Knock_Dow_with_fail(){

        assertEquals(1, new Frame(rollSecuence : "1-").pinsKnockDown())
    }

    public void test_Pins_Knock_Dow_with_spare(){

        assertEquals(10, new Frame(rollSecuence : "1/").pinsKnockDown())

    }

    public void test_Pins_Knock_Dow_with_strike(){

        assertEquals(10, new Frame(rollSecuence : "x").pinsKnockDown())

    }

      public void test_Two_simple_Frame(){

        assertEquals(4, new Bowling().score("1111"))
        assertEquals(8, new Bowling().score("2222"))

    }

     public void test_Two_simple_Frame_with_spare(){

        assertEquals(13, new Bowling().score("1/11"))

    }

     public void test_Two_simple_Frame_with_strike(){

        assertEquals(14, new Bowling().score("x11"))

    }

      public void test_Three_Frame_with_strike_and_spare(){

        assertEquals(32, new Bowling().score("x1/1-"))

    }

    public void test_Three_Frame_with_strike_and_strike(){

        assertEquals(33, new Bowling().score("xx1-"))

    }

    public void test_Three_Frame_with_spare_and_strike(){

        assertEquals(32, new Bowling().score("3/x1-"))

    }

    public void test_best_score(){

        assertEquals(300, new Bowling().score("xxxxxxxxxxxx"))

    }

    public void test_kata_sample(){

        assertEquals(150, new Bowling().score("5/5/5/5/5/5/5/5/5/5/5"))

    }




}
