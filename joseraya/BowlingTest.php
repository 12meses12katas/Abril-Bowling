<?php

require_once 'Bowling.php';

class BowlingTest extends PHPUnit_Framework_TestCase {

    public function testEmptyGame() {
        $game = new Bowling('');
        $this->assertEquals(0, $game->score());
    }
}
