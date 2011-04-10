<?php

/**
  * Kata 2011-04: Bowling
  *
  * Kata trying to resolve the Bowling problem using TDD.
  *
  * LICENSE: MIT license
  *
  * @category   Kata
  * @author     Rober Martín H "rmhdev"
  * @license    MIT License
  * @version    0.1
  * @link       http://github.com/12meses12katas/Abril-Bowling
 */

class Bowling {


    public function getValueOfFrame($frame) {
        if ($frame == '--'){
            return 0;
        }
        $total = 0;
        $last = 0;
        $bowls = str_split($frame);
        foreach ($bowls as $bowl){
            if ($bowl == 'X'){
                $total += 10;
                $last = 0;
            }
            elseif ($bowl == '/') {
                $total = $total + 10 - $last;
                $last = 0;
            }
            else {
                $total += (int)$bowl;
                $last = (int)$bowl;
            }
            
        }
        return $total;
    }

    public function getFirstFrameForSequence($sequence) {
        $firstTwo = substr($sequence, 0, 2);
        if ((strpos($firstTwo, 'X') !== false) || (strpos($firstTwo, '/') !== false)){
            return substr($sequence, 0, 3);
        }
        return $firstTwo;
    }

    public function isStrike($frame){
        return (strpos($frame, 'X') === 0) ? true : false;
    }
    
    /**
     * @param string $sequence
     * @return int
     */
    public function getGameScore($sequence){
        $score = 0;
        for($i=1; $i<=10; $i++){
            $frame = $this->getFirstFrameForSequence($sequence);
            $score += $this->getValueOfFrame($frame);
            $sequence = substr($sequence, $this->isStrike($frame) ? 1 : 2);
        }

        return $score;
    }
} 
