<?php

class Bowling {

    private $frames = array();

    public function __construct($game) {
        $this->frames = $this->parseFrames($game);
    }

    public function score() {
        $score = 0;
        $currentFramePosition = 0;
        while ($currentFramePosition < count($this->frames)) {
            $score += $this->scoreForFrameAt($currentFramePosition++);
        }
        return $score;
    }

    private function scoreForFrameAt($position) {
        if ($position > 9 ) {
            return 0;
        }
        $frame = $this->frames[$position];
        
        $score = $this->scoreForSingleFrame($frame);
        
        if ($this->frameIsStrike($frame)) {
            if ($this->thereIsAFrameAt($position + 2)) {
                $score += $this->scoreForSingleFrame($this->frames[$position + 2]);
                $score += $this->scoreForSingleFrame($this->frames[$position + 1]);
            } 
            
        }
        return $score;
    }

    private function scoreForSingleFrame($frame) {
        $score = 0;
        $array = preg_split('//', $frame, -1, PREG_SPLIT_NO_EMPTY);
        foreach ($array as $roll) {
            if ($this->rollIsMiss($roll)) {
                continue;
            }
            if ($this->rollIsNumber($roll)) {
                $score += (int) $roll;
            }
            if ($this->rollIsStrike($roll)) {
                $score += 10;
            }
        }
        return $score;
    }

    private function frameIsStrike($frame) {
        return $frame == "X";
    }

    private function thereIsAFrameAt($position) {
        return $position < count($this->frames);
    }

    private function rollIsMiss($char) {
        return $char === "-";
    }

    private function rollIsNumber($char) {
        return $char > '0' && $char <= '9';
    }

    private function rollIsStrike($char) {
        return $char == "X";
    }

    private function parseFrames($game) {
        $result = array();
        $array = preg_split('//', $game, -1, PREG_SPLIT_NO_EMPTY);
        $current = 0;
        while ($current < strlen($game)) {
            $char = $game[$current++];
            if ($this->rollIsStrike($char)) {
                $frame = 'X';
            } else {
                //$char2 = ""; //(string)$game{current++};
                $char2 = $game[$current++];
                $frame = "$char$char2";
            }
            array_push($result, $frame);
        }
        return $result;
    }

}
