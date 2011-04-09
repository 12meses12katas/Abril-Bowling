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

    public function testProviderFirstFrameForSequence(){
        $testCases = array();
        $testCases['with ---- should return -']     = array('----', '-');
        $testCases['with 1--- should return 1-']    = array('1---', '1-');
        $testCases['with 22-- should return 22']    = array('22--', '22');
        $testCases['with 5/-- should return 5/-']   = array('5/--', '5/-');
        $testCases['with X--- should return X--']   = array('X---', 'X--');
        $testCases['with X11- should return X11']   = array('X11-', 'X11');
        $testCases['with X5/- should return X5/']   = array('X5/-', 'X5/');
        $testCases['with 5/X- should return 5/X']   = array('5/X-', '5/X');
        // Watch out! Last round is special
        $testCases['with XXX should return XXX']    = array('XXX', 'XXX');
        $testCases['with 5/1 should return 5/1']    = array('5/1', '5/1');
        $testCases['with 1 should return 1']        = array('1', '1');

        return $testCases;
    }

    public function testProviderIsStrike(){
        $testCases = array();
        $testCases['with XXX should return true'] = array('XXX', true);
        $testCases['with 1- should return false'] = array('1-', false);
        $testCases['with 5/1 should return false'] = array('5/-', false);
        $testCases['with X5/- should return true'] = array('X5/-', true);

        return $testCases;
    }
    

    /**
     * @dataProvider testProviderValueOfFrame
     */
    public function testValueOfFrame($frame, $expected){
        $b = new Bowling();
        $this->assertEquals($expected, $b->getValueOfFrame($frame));
    }

    /**
     * @dataProvider testProviderFirstFrameForSequence
     */
    public function testFirstFrameForSequence($sequence, $expected){
        $b = new Bowling();
        $this->assertEquals($expected, $b->getFirstFrameForSequence($sequence));
    }

    /**
     * @dataProvider testProviderIsStrike
     */
    public function testIsStrike($frame, $expected){
        $b = new Bowling();
        $this->assertEquals($expected, $b->isStrike($frame));
    }

}
