<?php

class Bowling {
   
    private $score = 0;
    public function __construct($game) {
        $array = preg_split('//', $game, -1, PREG_SPLIT_NO_EMPTY);
        foreach($array as $char) {
            if ($char==="-") {
                continue;
            }
            $this->score += (int)$char;
        }
    }
    
    public function score() {
        return $this->score;
    }

 
}
