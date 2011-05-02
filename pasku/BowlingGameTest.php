<?php

require_once 'BowlingGame.php';

class BowlingGameTest extends PHPUnit_Framework_TestCase
{
    protected $_game;

    public function setUp()
    {
        $this->game = new BowlingGame();
    }

    public function testScoreForGutterGameIs0()
    {
        $this->rollMany(20, 0);
        $this->assertEquals(0, $this->game->score());
    }

    public function testScoreForAllOnesIs20()
    {
        $this->rollMany(20, 1);
        $this->assertEquals(20, $this->game->score());
    }

    public function testScoreForOneSpareAnd3Is16()
    {
        $this->rollSpare();
        $this->rollMany(17, 0);
        $this->assertEquals(16, $this->game->score());
    }

    public function testScoreForOneStrikeAnd3And4Is24()
    {
        $this->rollStrike();
        $this->rollMany(17, 0);
        $this->assertEquals(24, $this->game->score());
    }

    public function testScoreForPerfectGameIs300()
    {
        $this->rollMany(12, 10);
        $this->assertEquals(300, $this->game->score());
    }

    protected function rollMany($n, $pins)
    {
        for ($i = 0; $i < $n; $i++) {
            $this->game->roll($pins);
        }
    }

    protected function rollSpare()
    {
        $this->game->roll(5);
        $this->game->roll(5);
        $this->game->roll(3);
    }

    private function rollStrike()
    {
        $this->game->roll(10);
        $this->game->roll(3);
        $this->game->roll(4);
    }
}
