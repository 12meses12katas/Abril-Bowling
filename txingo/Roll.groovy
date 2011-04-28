
class Roll {
    String roll
    int pins
    int score
    
    public boolean isStrike(){
        "X".equals(roll)
    }
    
    public boolean isNumberRoll(){
        !isStrike() && !isSpare() && !isZeroPin()
    }
    
    public boolean isZeroPin(){
        "-".equals(roll)
    }
    
    public boolean isSpare(){
        "/".equals(roll)
    }
}
