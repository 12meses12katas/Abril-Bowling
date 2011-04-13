package net.corbacho.AbrilBowling;

public class Bowling {

    private final int numberOfFrames = 10;
    private int currentFrame;

    public Bowling() {
        super();
        this.currentFrame = 0;
    }


    public int getTryValue(char myChar) {

        Character myCharacter = myChar;
        int value = 0;
        if (myCharacter.equals('X') || myCharacter.equals('/')) {
            value = 10;

        } else if (myCharacter.equals('-')) {
            value = 0;

        } else {
            value = Integer.parseInt(myCharacter.toString());

        }

        return value;
    }

    public int getFrameValue(String frame) {
        int value = this.getTryValue(frame.charAt(0));

        if (frame.charAt(0) == 'X') {
            int auxRoll1 = this.getTryValue(frame.charAt(1));
            int auxRoll2 = this.getTryValue(frame.charAt(2));
                
            if (auxRoll1 <10 && auxRoll2 ==10){
                value +=10;
            } else {
                value += auxRoll1 + auxRoll2;                
            }
            
        } else if (frame.charAt(1) == '/') {
            value = 10 +this.getTryValue(frame.charAt(2));

        } else {
            value += this.getTryValue(frame.charAt(1));
        }

        return value;
    }

    public int getScore(String rolls) {
        int score = 0;
        int index = 0;
        while (this.currentFrame < this.numberOfFrames) {
            currentFrame++;
            score += this.getFrameValue(rolls.substring(index));
            if (rolls.charAt(index) == 'X')
                index ++;
            else
                index += 2;
        }
        return score;
    }


}
