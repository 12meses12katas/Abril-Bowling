<?php

require 'FramePool.php';

class BowlingGame {

  private static $instance = null;
  private $rollQueue;
  private $framePool;
  
  private function __construct() {
  }
  
  public static function factory($rolls) {
    if (self::$instance == null) {
      self::$instance = new self;
    }
    self::$instance->resetFrames()->setRollQueue(str_split($rolls))->fillFrames();
    return self::$instance;
  }
  
  public function score() {
    $score = 0;
    foreach($this->framePool->toArray() as $frame) {
      $score += $frame->score();
    }
    return $score;
  }
  
  private function resetFrames() {
    $this->framePool = new FramePool(10);
    return $this;
  }
  
  private function setRollQueue($rollQueue) {
    $this->rollQueue = $rollQueue;
    return $this;
  }
  
  private function fillFrames() {
      $frame = $this->framePool->getFrameInCourse();
      if (!$frame)
        return;
      $this->fillFrame($frame);
      $this->removeFrameFromQueue($frame);
      if (count($this->rollQueue) > 0)
        $this->fillFrames();
  }
  
  private function fillFrame($frame) {
    $currentRollNumber = 0;
    while (!$frame->isFull()) {
      $frame->addRoll($this->rollQueue[$currentRollNumber]);
      $currentRollNumber++;
    }
  }
  
  private function removeFrameFromQueue($frame) {
    array_splice($this->rollQueue, 0, $frame->size());
  }

}

