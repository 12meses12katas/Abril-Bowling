<?php
require '../Bowling.php';
class BowlingTests extends PHPUnit_Framework_TestCase 
{
    // examples taken from: https://gist.github.com/911944
    public function dataProvider() {
        return array(
            'score = 300' => array(300, 'XXXXXXXXXXXX'),
            'score = 0'   => array(0,   '--------------------'),
            'score = 90'  => array(90,  '9-9-9-9-9-9-9-9-9-9-'),
            'score = 150' => array(150, '5/5/5/5/5/5/5/5/5/5/5'),
            'score = 137' => array(137, '729-9-9-9/9/XX8-71'),
            'score = 135' => array(135, '-69/X63719-5/X7/7-'),
            'score = 100' => array(100, '1/-99-6-157-8/7-9-XX-'),
            'score = 92'  => array(92,  '9/9-8--/72-481-63-8-'),
            'score = 112' => array(112, '357-3/-333X9/8/629/6'),
            'score = 157' => array(157, '8/X71718-9-XX9/X7-'),
            'score = 133' => array(133, '9/8/X7/53348/-78/-/8'),
            'score = 146' => array(146, 'XX6-XX-97-7-9/9/7'),
            'score = 179' => array(179, '9/XX7/639/X9/9/9-'),
            'score = 89'  => array(89,  '349-3--95/X3--1X-7'),
            'score = 113' => array(113, '9/449-9-9/9-9-9-9-X8-'),
        );
    }

    /**
     * @dataProvider dataProvider
     */
    public function testGames($score, $rolls) 
    {
        $bowling = new Bowling();
        $this->assertEquals($score, $bowling->score($rolls));
    }
}
