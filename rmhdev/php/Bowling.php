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

    /**
     * @param string $frame
     * @return int
     */
    public function getScoreOfFrame($frame) {
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

    /**
     * @param string $sequence
     * @return string
     */
    public function getFirstFrameForSequence($sequence) {
        $firstTwo = substr($sequence, 0, 2);
        if ($this->isStrike($firstTwo) || $this->isSpare($firstTwo)){
            return substr($sequence, 0, 3);
        }
        return $firstTwo;
    }

    /**
     * @param string $frameOrSequence
     * @return boolean
     */
    public function isStrike($frameOrSequence){
        return (strpos($frameOrSequence, 'X') === 0) ? true : false;
    }

    /**
     * @param string $frameOrSequence
     * @return boolean
     */
    public function isSpare($frameOrSequence){
        return (strpos($frameOrSequence, '/') === 1) ? true : false;
    }

    /**
     * @param string $sequence
     * @return string
     */
    public function getNextSequence($sequence) {
        if (strlen($sequence) <= 3){
            return '';
        }
        return substr($sequence, $this->isStrike($sequence) ? 1 : 2);
    }
    
    /**
     * @param string $sequence
     * @return int
     */
    public function getGameScore($sequence){
        $score = 0;
        while ($sequence){
            $frame = $this->getFirstFrameForSequence($sequence);
            $score += $this->getScoreOfFrame($frame);
            $sequence = $this->getNextSequence($sequence);
        }

        return $score;
    }
} 
