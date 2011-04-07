
class Score {
    private List reverseRolls
    private countPins = { roll, index ->
        if(roll.isStrike()){
            roll.pins = 10
        }
        if(roll.isNumberRoll()){
            roll.pins = roll.roll.toInteger()
        }
    }
    private countPinsSpare = { roll, index ->
        if(roll.isSpare()){
            roll.pins = 10 - reverseRolls[index + 1].pins //a spare will always have a previous roll
        }
    }
    private scoreOneToNinePinRolls = { roll, index ->
        if(roll.isNumberRoll()){
            if(!isRollWithoutScore(reverseRolls, index)){
                roll.score = roll.pins
            }
        }
    }
    private scoreSpare = { roll, index ->
        if(roll.isSpare()){
            roll.score = roll.pins + reverseRolls[index - 1].pins
        }
    }
    private scoreStrike = { roll, index ->
        if(roll.isStrike()){
            if(index >= 2){
                roll.score = roll.pins
                roll.score += reverseRolls[index - 1].pins
                roll.score += reverseRolls[index - 2].pins
            }
        }
    }
    
    int compute(String game){
        int numberOfRolls = game.length()
        def rolls = []
        game.toCharArray().eachWithIndex { it, i ->
            rolls << new Roll(roll:it.toString())
        }
        
        reverseRolls = rolls.reverse()
        reverseRolls.eachWithIndex countPins
        reverseRolls.eachWithIndex countPinsSpare
        reverseRolls.eachWithIndex scoreOneToNinePinRolls
        reverseRolls.eachWithIndex scoreSpare
        reverseRolls.eachWithIndex scoreStrike
        
        computeTotalScore(reverseRolls)
    }
    
    private boolean isRollWithoutScore(List rolls, int index){
        boolean result = false
        if(isExtraRoll(rolls, index)){
            result = result ?: lastScorableRollWasStrike(rolls, index)
            result = result ?: lastScorableRollWasSpare(rolls, index)
        }
        result
    }
    
    private boolean isExtraRoll(List rolls, int index){
        List strikes = []
        strikes << rolls.findAll { it.isStrike() }
        List flattenStrikes = strikes.flatten()
        
        def rollCount = (rolls.size() + flattenStrikes.size())
        
        (rollCount > 20) && (index < 2)
    }
    
    private boolean lastScorableRollWasStrike(List rolls, int index){
        int rollsSize = rolls.size()
        def currentRollIsJustNextToStrike = index == 1 && (index + 1) <= rollsSize && rolls[index + 1].isStrike()
        def currentRollIsAfterNextToStrike = index == 0 && (index + 2) <= rollsSize && rolls[index + 2].isStrike()
        currentRollIsJustNextToStrike || currentRollIsAfterNextToStrike
    }
    
    private boolean lastScorableRollWasSpare(List rolls, int index){
        int rollsSize = rolls.size()
        (index + 1) <= rollsSize && index == 0 && rolls[index + 1].isSpare()
    }
    
    private computeTotalScore(List rolls) {
        int sum = 0
        rolls.each { sum += it.score }
        sum
    }
}
