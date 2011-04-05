<?php

require '../Game.php';

class GameTests extends PHPUnit_Framework_TestCase {

  public function testGutterGame() {
    $game = new Game("--------------------");
    $this->assertEquals(0, $game->score());
  }

  public function testAllOnes() {
    $game = new Game("11111111111111111111");
    $this->assertEquals(20, $game->score());
  }

  public function testOneSpare() {
    $game = new Game("4/300000000000000000");
    $this->assertEquals(16, $game->score());
  }

  public function testOneStrike() {
    $game = new Game("X530000000000000000");
    $this->assertEquals(26, $game->score());
  }

  public function testAllStrikes() {
    $game = new Game("XXXXXXXXXXXX");
    $this->assertEquals(300, $game->score());
  }

}
