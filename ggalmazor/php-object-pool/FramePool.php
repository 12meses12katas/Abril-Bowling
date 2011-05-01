<?php

require 'Frame.php';

class FramePool {

  private $frames = array();

  public function __construct($size) {
    for ($frameNumber = 0; $frameNumber < $size; $frameNumber++) {
      $this->frames[$frameNumber] = new Frame();
    }
  }

  public function getFrameInCourse() {
    return array_shift($this->getNonFullFrames());
  }

  public function toArray() {
    return $this->frames;
  }

  private function getNonFullFrames() {
    $nonFullFrames = array_filter($this->frames, function($frame) {
          return (!$frame->isFull());
        });
    return $nonFullFrames;
  }

}