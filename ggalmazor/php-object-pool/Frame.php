<?php

class Frame {

  private $rolls = array();

  public function addRoll($pins) {
    if ($pins == 'X') {
      $this->rolls[] = 10;
    } else if ($pins == '/') {
      $this->rolls[] = 10 - $this->rolls[0];
    } else if ($pins == '-') {
      $this->rolls[] = 0;
    } else {
      $this->rolls[] = $pins;
    }
  }

  public function size() {
    if ($this->isStrike())
      return 1;
    return 2;
  }

  public function isFull() {
    if (count($this->rolls) == 0)
      return false;
    if ($this->isStrike() && $this->rollCount() < 3)
      return false;
    if ($this->isSpare() && $this->rollCount() < 3)
      return false;
    if ($this->rollCount() < 2)
      return false;
    return true;
  }

  public function score() {
    if ($this->isStrike()) {
      return 10 + $this->strikeBonus();
    } else if ($this->isSpare()) {
      return 10 + $this->spareBonus();
    } else {
      return $this->simpleScore();
    }
  }
  
  private function rollCount() {
    return count($this->rolls);
  }

  private function isStrike() {
    return $this->rolls[0] == 10;
  }

  private function isSpare() {
    return count($this->rolls) >= 2 && $this->simpleScore() == 10;
  }

  private function simpleScore() {
    return $this->rolls[0] + $this->rolls[1];
  }

  private function strikeBonus() {
    return $this->rolls[1] + $this->rolls[2];
  }

  private function spareBonus() {
    return $this->rolls[2];
  }

}