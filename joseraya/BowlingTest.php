<?php

require_once 'Bowling.php';

class BowlingTest extends PHPUnit_Framework_TestCase {

    public function testEmptyGame() {
        $game = new Bowling('');
        $this->assertEquals(0, $game->score());
    }

    public function testAllFramesNineAndMiss() {
        $game = new Bowling('9-9-9-9-9-9-9-9-9-9-');
        $this->assertEquals(90, $game->score());
    }
    
    public function testOneStrike() {
        $game = new Bowling('X121212121212121212');
        $this->assertEquals(43, $game->score());
    }
    
    public function testAllStrikes() {
        $game = new Bowling('XXXXXXXXXXXX');
        $this->assertEquals(300, $game->score());
    }
}
