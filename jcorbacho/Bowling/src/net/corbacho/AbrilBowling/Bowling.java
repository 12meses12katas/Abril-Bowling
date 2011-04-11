package net.corbacho.AbrilBowling;

public class Bowling {

    private int currentFrame;
    
    public Bowling() {
        super();
        this.currentFrame=0;
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

        if (frame.charAt(0) != 'X' && frame.charAt(0) != '/' && 
                frame.length() > 2 && frame.charAt(1) == '/') {  

            // If starts with number or "-" and then there is spare (having more strikes)
            value = this.getTryValue(frame.charAt(1)) + this.getTryValue(frame.charAt(2));

        }else if (frame.charAt(0) != 'X' && frame.charAt(0) != '/' && 
                frame.length() > 1 && frame.charAt(1) == '/') {  

            // If starts with number or "-" and then there is spare (no having more strikes)
            value = this.getTryValue(frame.charAt(1));
            
        } else if (frame.charAt(0) != 'X' && frame.charAt(0) != '/' &&
                frame.length() > 1) {

            // If starts with number or "-" and then there is not spare
            value += this.getTryValue(frame.charAt(1));
            
        }else if (frame.charAt(0) == 'X' && frame.length() == 2){
            
            // If we have strike and there is one frame more
            value += this.getTryValue(frame.charAt(1));
        
        }else if (frame.charAt(0) == 'X' && frame.length() > 2){
            
            // If we have strike and there is at least two frame more
            int aux1= this.getTryValue(frame.charAt(1));
            int aux2 = this.getTryValue(frame.charAt(2));
            value += aux2;
            
            if (aux1 == 10 || aux2 != 10 ){
                value += aux1;
            }
        }
     

        return value;
    }

    public int getScore(String myString) {

        int score = 0;
        int index = 0;
        boolean finishedGame = false;
        while (currentFrame < 10 && !finishedGame) {

            score += this.getFrameValue(myString.substring(index));

            if (myString.charAt(index) != 'X' && myString.charAt(index) != '/') {
                index = index + 2;

            } else {
                index = index + 1;
            }

            currentFrame++;
            
            if (index >= myString.length())
                finishedGame = true;
        }

        return score;
    }

}
