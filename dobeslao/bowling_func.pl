#!/usr/bin/perl

use strict;
use warnings;

sub isStrike {
	my @roll = @_;
	return $roll[0] == 10;
}

sub isSpare {
	my @roll = @_;
	return getFrameScore(@roll) == 10;
}

sub getFrameScore {
	my @roll = @_;
	return $roll[0] + $roll[1];
}

sub getSpareBonus {
	my @nextroll = @_;
	return $nextroll[0];
}

sub getStrikeBonus {
	my @nextroll = @_;
	return $nextroll[0];
}

sub bowling {
	my @line = @_;
	my $score = 0;

	my $VERBOSE = 0;

	for my $frame (0..$#line) {

		my @roll = @{$line[$frame]};
		my @nextroll = $frame == $#line ? @roll [$#roll-1..$#roll] : @{$line[$frame+1]};

		if (isStrike(@roll)) {
			if ($VERBOSE) { print "Strike\n"; }

			$score += 10; 
			$score += getStrikeBonus(@nextroll);
			$score += isStrike(@nextroll) && $frame < $#line-1 ? getStrikeBonus(@{$line[$frame+2]}) 
									   : getStrikeBonus(@nextroll [1..1]);
		}
		elsif (isSpare(@roll)) {
			if ($VERBOSE) { print "Spare\n"; }

			if ($frame == $#line) { $nextroll[0] = $nextroll[1]; }
			$score += 10 + getSpareBonus(@nextroll);
		}
		else {
			if ($VERBOSE) { print "Roll\n"; }

			$score += getFrameScore(@roll);
		}

		if ($VERBOSE) {
			print "Game: " . ($frame+1) . " - ";
			print "Score: " . $score . "\n";
			foreach my $roll (0..$#{$line[$frame]}) {
				print "Throw [$frame][$roll] = $line[$frame][$roll]\n";
			}
		}
	}

	return $score;
}
1;
__END__
