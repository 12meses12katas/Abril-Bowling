<?php
/**
 * Kata 2011-04: Bowling Game
 *
 * Kata trying to resolve the BowlingGame problem using TDD.
 *
 * @category   Kata
 * @author     Guillermo Pascual <@pasku1>
 * @version    0.1
 * @link       http://github.com/12meses12katas/Abril-Bowling
 */

class BowlingGame
{
    protected $rolls = array();

    public function roll($pins)
    {
        $this->rolls[] = $pins;
    }

    public function score()
    {
        $score      = 0;
        $frameIndex = 0;

        for ($frame = 0; $frame < 10; $frame++) {
            if ($this->isStrike($frameIndex)) {
                $score += 10 + $this->strikeBonus($frameIndex);
                $frameIndex++;
            }

            else if ($this->isSpare($frameIndex)) {
                $score += 10 + $this->spareBonus($frameIndex);
                $frameIndex += 2;
            }

            else {
                $score += $this->sumOfPinsInFrame($frameIndex);
                $frameIndex += 2;
            }
        }
        return $score;
    }

    protected function isStrike($frameIndex)
    {
        return $this->rolls[$frameIndex] === 10;
    }

    protected function isSpare($frameIndex)
    {
        return $this->sumOfPinsInFrame($frameIndex) === 10;
    }

    protected function sumOfPinsInFrame($frameIndex)
    {
        return $this->rolls[$frameIndex] +
               $this->rolls[$frameIndex + 1];
    }

    protected function strikeBonus($frameIndex)
    {
        return $this->rolls[$frameIndex + 1] +
               $this->rolls[$frameIndex + 2];
    }

    protected function spareBonus($frameIndex)
    {
        return $this->rolls[$frameIndex + 2];
    }
}
