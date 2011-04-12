<?php

class Bowling {
    const STRIKE='X';
    const MISS='/';
    
    private $frames = array();

    public function __construct($game) {
        $this->frames = $this->parseFrames($game);
    }

    public function score() {
        $score = 0;
        $currentFramePosition = 0;
        while ($this->shouldCountFrameAt($currentFramePosition)) {
            $score += $this->scoreForFrameAt($currentFramePosition);

            if ($this->hasAStrikeAt($currentFramePosition)) {
                $score += $this->scoreForFrameAt($currentFramePosition + 1);
                
                if ($this->hasAStrikeAt($currentFramePosition + 1)) {
                    $score += $this->scoreForFrameAt($currentFramePosition + 2);
                }
            }
            ++$currentFramePosition;
        }
        return $score;
    }

    private function shouldCountFrameAt($position) {
        if (!$this->hasAFrameAt($position)) {
            return false;
        }
        if ($this->hasAStrikeAt($position)) {
            if (!$this->hasAFrameAt($position + 1)) {
                return false;
            }
            if ($this->hasAStrikeAt($position + 1)) {
                return $this->hasAFrameAt($position + 2);
            }
        }
        return $position<10;
    }

    private function scoreForFrameAt($position) {
        $frame = $this->frames[$position];
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

    private function hasAStrikeAt($position) {
        $frame = $this->frames[$position];
        return $frame == Bowling::STRIKE;
    }

    private function hasAFrameAt($position) {
        return $position < count($this->frames);
    }

    private function rollIsMiss($char) {
        return $char == Bowling::MISS;
    }

    private function rollIsNumber($char) {
        return $char > '0' && $char <= '9';
    }

    private function rollIsStrike($char) {
        return $char == Bowling::STRIKE;
    }

    private function parseFrames($game) {
        $result = array();
        $current = 0;
        while ($current < strlen($game)) {
            $char = $game[$current++];
            if ($this->rollIsStrike($char)) {
                $frame = Bowling::STRIKE;
            } else {
                $char2 = $game[$current++];
                $frame = "$char$char2";
            }
            array_push($result, $frame);
        }
        return $result;
    }

}
