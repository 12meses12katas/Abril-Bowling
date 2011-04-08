<?php

class Bowling {

    private $frames = array();

    public function __construct($game) {
        $this->frames = $this->getFrames($game);
    }

    public function score() {
        $score = 0;
        $current = 0;
        while($current < count($this->frames)) {
            $frame = $this->frames[$current++];
            $score += $this->scoreForFrame($frame);
        }
        return $score;
    }

    private function scoreForFrame($frame) {
        $score = 0;
        $array = preg_split('//', $frame, -1, PREG_SPLIT_NO_EMPTY);
        foreach($array as $roll) {
            if ($this->isMiss($roll)) {
                continue;
            }
            if ($this->isNumber($roll)) {
                $score += (int) $roll;
            }
        }
        return $score;
    }
    
    private function isMiss($char) {
        return $char === "-";
    }

    private function isNumber($char) {
        return $char > '0' && $char <= '9';
    }

    private function getFrames($game) {
        $result = array();
        $array = preg_split('//', $game, -1, PREG_SPLIT_NO_EMPTY);
        $current = 0;
        while ($current < strlen($game)) {
            $char = $game[$current++];
            if ($char == 'X') {
                $frame = 'X';
            } else {
                $char2="";//(string)$game{current++};
                $frame = "$char$char2";
            }
            array_push($result, $frame);
        }
    
        return $result;
    }

}
