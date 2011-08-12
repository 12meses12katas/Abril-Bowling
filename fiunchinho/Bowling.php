<?php
/**
 * Description of Bowling: http://codingdojo.org/cgi-bin/wiki.pl?KataBowling
 *
 * @author fiunchinho
 */
class Bowling
{
	/**
	 * Number of points scored when you get a spare.
	 *
	 * @var integer
	 */
	const SPARE_SCORE = 10;

	/**
	 * Number of points scored when you get a strike.
	 *
	 * @var integer
	 */
	const STRIKE_SCORE = 10;

	/**
	 * Number of frames a game has.
	 *
	 * @var integer
	 */
	const FRAMES_PER_GAME = 10;

	/**
	 * Array storing all the throws by a player.
	 *
	 * @var array
	 */
	public $frames = array();

	/**
	 * Number of points scored during the game.
	 *
	 * @var integer
	 */
	public $score = 0;

	/**
	 *
	 * @param array $frames All the throws by a player.
	 */
	public function __construct( array $frames )
	{
		$this->frames = $frames;
	}

	/**
	 * It goes through the throws calculating the players score.
	 *
	 * @return integer Scored points during the game.
	 */
	public function getScore()
	{
		$frameNumber = $throwNumber = 0;
		for (	$firstThrowInFrame = true, $numberOfThrows = count( $this->frames ) ;
				$frameNumber < self::FRAMES_PER_GAME && $throwNumber < $numberOfThrows  ;
				$throwNumber++
			)
		{
			switch ( $this->frames[$throwNumber] )
			{
				case 'X':
					$firstThrowInFrame = true;
					$this->score += $this->getStrikeScore( $throwNumber );
					$frameNumber++;
					break;
				case '/':
					$firstThrowInFrame = true;
					$this->score += $this->getSpareScore( $throwNumber );
					$frameNumber++;
					break;
				case ( $firstThrowInFrame = !$firstThrowInFrame ):
					$this->score += $this->getFrameScore( $throwNumber );
					$frameNumber++;
			}
		}
		return $this->score;
	}

	/**
	 * Just adds up the current throw score, and the previous one.
	 *
	 * @param integer $throwNumber
	 * @return integer Score for that frame.
	 */
	protected function getFrameScore( $throwNumber )
	{
		return $this->frames[$throwNumber] + $this->frames[$throwNumber - 1];
	}

	/**
	 * It checks spare's next position to calculate the total score.
	 *
	 * @param integer $throwNumber The number of the throw within the frame of throws
	 * @return integer Score for that spare throw.
	 */
	protected function getSpareScore( $throwNumber )
	{
		switch ( $this->frames[$throwNumber + 1] )
		{
			case 'X':
				return self::SPARE_SCORE + self::STRIKE_SCORE;
				break;
			case '-':
				return self::SPARE_SCORE;
				break;
			default:
				return self::SPARE_SCORE + $this->frames[$throwNumber + 1];
				break;
		}
	}

	/**
	 * It checks 2 positions beyond strikes's position to calculate the total score. If that position is a miss, the strike is like a spare.
	 *
	 * @param integer $throwNumber The number of the throw within the frame of throws
	 * @return integer Score for that strike throw.
	 */
	protected function getStrikeScore( $throwNumber )
	{
		switch ( $this->frames[$throwNumber + 2] )
		{
			case 'X':
				return self::STRIKE_SCORE + self::STRIKE_SCORE + self::STRIKE_SCORE;
				break;
			case '/':
				return self::STRIKE_SCORE + self::SPARE_SCORE;
				break;
			case '-':
				return $this->getSpareScore( $throwNumber );
				break;
			default:
				return $this->frames[$throwNumber + 2] + $this->getSpareScore( $throwNumber );
				break;
		}
	}
}
?>
