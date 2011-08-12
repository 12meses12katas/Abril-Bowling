<?php

require_once realpath( __DIR__ . '/..' ) . '/Bowling.php';

/**
 * Test class for Bowling Kata.
 *
 */
class BowlingTest extends PHPUnit_Framework_TestCase
{
	/**
	 * Dataprovider for the Bowling test.
	 *
	 * @return array Array containing the different test cases.
	 */
	public function providerBowling()
	{
		return array(
			'testThrowing9PinsInFirstFrame'								=> array( array( 9, '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-' ), 9 ),
			'testThrowing5PinsInFirstFrame'								=> array( array( 5, '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-' ), 5 ),
			'testThrowing12PinsInFirstFrameWithoutStrike'				=> array( array( 9, 3, '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-' ), 12 ),
			'testThrowing12PinsInSecondFrameWithoutStrike'				=> array( array( '-', '-', 9, 3, '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-' ), 12 ),
			'testThrowing12PinsBetweenFirstAndSecondFrameWithoutStrike'	=> array( array( 9, '-', '-', 3, '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-' ), 12 ),
			'testThrowingSpareInFirstFrameAnd2PinsInSecondIs14'			=> array( array( 5, '/', 2, '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-' ), 14 ),
			'testThrowingSpareInFirstFrameAnd2And3PinsInSecondIs17'		=> array( array( 5, '/', 2, 3, '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-' ), 17 ),
			'testOneStrikeInFirstThrowIs10'								=> array( array( 'X', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-' ), 10 ),
			'testOneStrikeInFirstThrowAnd2PinsIs14'						=> array( array( 'X', 2, '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-' ), 14 ),
			'testOneStrikeInFirstThrowAnd2And3PinsIs20'					=> array( array( 'X', 2, 3, '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-' ), 20 ),
			'testComplexFrameThatScores81'								=> array( array( 'X', 2, 3, 4, '/', 2, 5, 'X', 'X', 2, 3, '-', '-', '-', '-', '-', '-', '-', '-' ), 81 ),
			'testEndingInSpare'											=> array( array( 2, 3, '-', '-', '-', '-', '-', '-', '-', '/', 2 ), 17 ),
			'testEndingInStrike'										=> array( array( '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', 'X', 'X', 'X' ), 30 ),
			'testFullStrikes'											=> array( array( 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' ), 300 ),
			'testEndingInStrikeAndSpare'								=> array( array( '-', '-', 2, 3, 'X', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', '-', 'X', 2, '/' ), 35 ),
			'test10PairsOf9AndMiss'										=> array( array( 9, '-', 9, '-', 9, '-', 9, '-' , 9, '-' , 9, '-' , 9, '-' , 9, '-' , 9, '-' , 9, '-'  ), 90 ),
			'test10PairsOf5AndSpareWithFinal5'							=> array( array( 5, '/', 5, '/', 5, '/', 5, '/', 5, '/', 5, '/', 5, '/', 5, '/', 5, '/', 5, '/', 5 ), 150 )
		);
	}

	/**
	 *
	 * @dataProvider providerBowling
	 * @param array $frames
	 * @param int $expectedScore
	 */
	public function testBowling( $frames, $expectedScore )
	{
		$this->bowling = new Bowling( $frames );
		$this->assertEquals( $expectedScore, $this->bowling->getScore() );
	}
}
?>
