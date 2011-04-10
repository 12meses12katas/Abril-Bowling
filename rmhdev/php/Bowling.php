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
        if (in_array($frame, array('', '-', '--'))){
            return 0;
        }
        if ($this->isStrikeOrSpare($frame)){
            return 10 + $this->getScoreOfFrame(substr($frame, $this->isStrike($frame) ? 1 : 2));
        }
        return (int)substr($frame, 0, 1) + $this->getScoreOfFrame(substr($frame, 1));
    }

    /**
     * @param string $sequence
     * @return string
     */
    public function getFirstFrameForSequence($sequence) {
        return substr($sequence, 0, $this->isStrikeOrSpare($sequence) ? 3 : 2);
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
     * @param string $frameOrSequence
     * @return boolean
     */
    public function isStrikeOrSpare($frameOrSequence){
        return ($this->isStrike($frameOrSequence) || $this->isSpare($frameOrSequence)) ? true : false;
    }

    /**
     * @param string $sequence
     * @return string
     */
    public function getNextSequence($sequence) {
        return substr($sequence, $this->isStrike($sequence) ? 1 : 2);
    }
    
    /**
     * @param string $sequence
     * @return int
     */
    public function getGameScore($sequence){
        $score = 0;
        for($i=1; $i<=10; $i++){
            $frame = $this->getFirstFrameForSequence($sequence);
            $score += $this->getScoreOfFrame($frame);
            $sequence = $this->getNextSequence($sequence);
        }
        return $score;
    }
} 
