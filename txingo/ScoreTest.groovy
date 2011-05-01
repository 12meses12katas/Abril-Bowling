import static org.junit.Assert.*;
import groovy.util.GroovyTestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test 


class ScoreTest extends GroovyTestCase {
    
    private Score score;
    
    @Before
    public void setUp() throws Exception {
        score = new Score();
    }
    
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void testSimpleStrike(){
        assertEquals 10, score.compute("X------------------")
    }
    
    @Test
    public void testTwoStrikes(){
        assertEquals 30, score.compute("XX----------------")
    }
    
    @Test
    public void testLastStrike(){
        assertEquals 20, score.compute("X----------------X--")
    }
    
    @Test
    public void testPerfectGame() {
        assertEquals 300, score.compute("XXXXXXXXXXXX")
    }
    
    @Test
    public void test9AndNothing() {
        assertEquals 9, score.compute("9-------------------")
    }
    
    @Test
    public void testTwice9AndNothing() {
        assertEquals 18, score.compute("9-9-----------------")
    }
    
    @Test
    public void testAlmostPerfectGame() {
        assertEquals 90, score.compute("9-9-9-9-9-9-9-9-9-9-")
    }
    
    @Test
    public void testFiveAndScore() {
        assertEquals 150, score.compute("5/5/5/5/5/5/5/5/5/5/5")
    }
    
    /**
     * Test from: https://github.com/12meses12katas/Abril-Bowling/blob/master/dobeslao
     */
    @Test
    public void testDobeslao() {
        assertEquals 0, score.compute("--------------------")
        assertEquals 20, score.compute("11111111111111111111")
        assertEquals 60, score.compute("33333333333333333333")
        assertEquals 94, score.compute("123456789/123456789/1")
        assertEquals 130, score.compute("3/3/3/3/3/3/3/3/3/3/3")
        assertEquals 140, score.compute("4/4/4/4/4/4/4/4/4/4/4")
        assertEquals 60, score.compute("XXX--------------")
        assertEquals 133, score.compute("14456/5/X017/6/X2/6")
        assertEquals 167, score.compute("X7/9X-88/-6XXX81")
        assertEquals 240, score.compute("XXXXXXXXX--")
        assertEquals 24, score.compute("X34----------------")
        assertEquals 145, score.compute("X7/9-X-88/-6XX72")
    }
    
    @Test
    public void testAlternateStrikesAndSpares() {
        assertEquals 200, score.compute("X4/X4/X4/X4/X4/X")
    }
}
