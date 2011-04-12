<?php

class Bowling {

    private $frames = array();

    public function __construct($game) {
        $this->frames = $this->getFrames($game);
    }

    public function score() {
        $score = 0;
        $current = 0;
        while ($current < count($this->frames)) {
            $score += $this->scoreForFrame($current++);
        }
        return $score;
    }

    private function scoreForFrame($current) {
        $score = $this->scoreForSingleFrame($current);
        if ($this->frameIsStrike($this->frames[$current]) && ($current < 9)) {
            if ($current + 1 < count($this->frames)) {
                $score += $this->scoreForSingleFrame($current + 1);
            }
            if ($current + 2 < count($this->frames)) {
                $score += $this->scoreForSingleFrame($current + 2);
            }
        }
        return $score;
    }

    private function scoreForSingleFrame($current) {
        $frame = $this->frames[$current];
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

    private function rollIsMiss($char) {
        return $char === "-";
    }

    private function rollIsNumber($char) {
        return $char > '0' && $char <= '9';
    }
    private function frameIsStrike($frame) {
        return $frame == "X";
    }
    private function rollIsStrike($char) {
        return $char == "X";
    }

    private function getFrames($game) {
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
