<?php
class Bowling 
{
    private $rolls = array();

    private function decodeString($rolls)
    {
        $conf = array(
            '-' => function($rolls, $roll) {return 0;},
            '/' => function($rolls, $roll) {return 10 - $rolls[$roll-1]; },
            'X' => function($rolls, $roll) {return 10;},
        );

        foreach(str_split($rolls) as $roll => $pins) {
            if (array_key_exists($pins, $conf)) {
                $pins = $conf[$pins]($rolls, $roll);
            }
            $out[] = $pins;
        }
        return $out;
    }
    
    public function score($rolls) {
        $this->rolls = $this->decodeString($rolls);
        $score = 0;
        for($roll = 0, $frameIndex = 0; $frameIndex < 10; $frameIndex++) {
            if ($this->isStrike($roll)) {
                $score += 10;
                $score += $this->rolls[$roll+1] + $this->rolls[$roll+2];
                $roll++;
            } else if ($this->isSpare($roll)) {
                $score += 10; 
                $score += $this->rolls[$roll+2];
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
  
  
    private function simpleScore($roll) {
        return $this->rolls[$roll] + $this->rolls[$roll+1];
    }
}
