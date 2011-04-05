<?php

class Game {

  private $rolls = array();
  
  public function __construct($rolls) {
    foreach(str_split($rolls) as $roll => $pins) {
      switch($pins) {
        case '-': $pins = 0; break;
        case '/': $pins = 10 - $rolls[$roll-1]; break;
        case 'X': $pins = 10;
      }
      $this->rolls[] = $pins;
    }
  }
  
  public function score() {
    $score = 0;
    for($roll = 0, $frame = 0; $frame < 10; $frame++) {
      if ($this->isStrike($roll)) {
        $score += 10 + $this->strikeBonus($roll);
        $roll++;
      } else if ($this->isSpare($roll)) {
        $score += 10 + $this->spareBonus($roll);
        $roll +=2;
      } else {
        $score += $this->simpleScore($roll);
        $roll +=2;
      }
    }
    return $score;
  }
  
  private function isStrike($roll) {
    return $this->rolls[$roll] == 10;
  }
  
  private function isSpare($roll) {
    return $this->simpleScore($roll) == 10;
  }
  
  private function strikeBonus($roll) {
    return $this->rolls[$roll+1] + $this->rolls[$roll+2];
  }
  
  private function spareBonus($roll) {
    return $this->rolls[$roll+2];
  }
  
  private function simpleScore($roll) {
    return $this->rolls[$roll] + $this->rolls[$roll+1];
  }

}