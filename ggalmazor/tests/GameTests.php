<?php

require '../Game.php';

class GameTests extends PHPUnit_Framework_TestCase {

  public function getGameTestCases() {
    return array(
        'Score of a gutter game should sum zero' => array(0, '--------------------'),
        'Score should sum the pins thrown in each roll' => array(20, '11111111111111111111'),
        'One spare should sum next roll\'s pins to its score' => array(16, '4/300000000000000000'),
        'One strike should sum the simple score of the next frame' => array(26, 'X530000000000000000'),
        'A perfect game should sum a score of 300' => array(300, 'XXXXXXXXXXXX')
    );
  }
  
  /**
   * @dataProvider getGameTestCases
   */
  public function testGames($expectedScore, $rolls) {
    $game = new Game($rolls);
    $this->assertEquals($expectedScore, $game->score());
  }
  
}
