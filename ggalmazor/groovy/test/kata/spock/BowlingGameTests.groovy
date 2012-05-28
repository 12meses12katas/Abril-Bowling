package kata.spock

import spock.lang.Specification
import kata.BowlingGame

class BowlingGameTest extends Specification {
	def bg = new BowlingGame()

	def "gutter game scores zero"() {
		when:
		(1..20).each { bg.roll 0 }

		then:
		bg.score() == 0
	}

	def "all rolls throw only one pin"() {
		when:
		(1..20).each { bg.roll 1 }

		then:
		bg.score() == 20
	}

	def "a spare roll adds the next roll's pins as extra score"() {
		when:
		(1..3).each { bg.roll 5 }
		(1..17).each { bg.roll 0 }

		then:
		bg.score() == 20
	}

	def "a strike roll adds the next two rolls' pins as extra score"() {
		when:
		bg.roll 10
		(1..2).each { bg.roll 3 }
		(1..16).each { bg.roll 0 }

		then:
		bg.score() == 22
	}

	def "all strikes score 300"() {
		when:
		(1..12).each { bg.roll 10 }

		then:
		bg.score() == 300
	}
}