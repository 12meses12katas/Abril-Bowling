<?php

/**
  * Kata 2011-04: Bowling. PhpUnit test file.
  *
  * Kata trying to resolve the Bowling problem using TDD.
  *
  * LICENSE: MIT license
  *
  * @category   Kata
  * @author     Rober Martín H "rmhdev"
  * @license    MIT License
  * @version    0.1
  * @link       http://github.com/12meses12katas/Abril-Bowling
 */

include __DIR__ . "/../Bowling.php";

class BowlingTest extends PHPUnit_Framework_TestCase {

    public function testProviderValueOfFrame(){
        $testCases = array();
        $testCases['with - should return 0'] = array('-', 0);
        $testCases['with 1- should return 1'] = array('1-', 1);
        $testCases['with 11 should return 2'] = array('11', 2);
        $testCases['with 12 should return 3'] = array('12', 3);
        $testCases['with 5/- should return 10'] = array('5/-', 10);
        $testCases['with 5/1 should return 11'] = array('5/1', 11);
        $testCases['with X-- should return 10'] = array('X--', 10);
        $testCases['with X1- should return 11'] = array('X1-', 11);
        $testCases['with X11 should return 12'] = array('X11', 12);
        $testCases['with X5/ should return 20'] = array('X5/', 20);
        $testCases['with 5/X should return 20'] = array('5/X', 20);
        $testCases['with X-X should return 20'] = array('X-X', 20);
        $testCases['with XXX should return 30'] = array('XXX', 30);

        return $testCases;
    }

    /**
     * @dataProvider testProviderValueOfFrame
     */
    public function testValueOfFrame($frame, $expected){
        $b = new Bowling();
        $this->assertEquals($expected, $b->getValueOfFrame($frame));
    }

}
