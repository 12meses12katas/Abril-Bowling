
/**
 * Created by IntelliJ IDEA.
 * User: ruben
 * Date: 30/04/11
 * Time: 11:18
 * To change this template use File | Settings | File Templates.
 */

class Frame {

    final static String STRIKE = 'x'
    final static String SPARE = '/'
    final static String FAIL = '-'
    final static int MAX_SCORE = 10

    String rollSecuence
    int score

    static def getFrames(String rollsSecuence){

        def frames = []
        int frameIndex = 0
        String currentFrame = ""
        rollsSecuence.each {
            if (it == '-'){
                it = 0
            }
            currentFrame += it
            if ((frameIndex == 1)||(it == STRIKE)){
                 Frame frame = new Frame( rollSecuence : currentFrame)
                 frames.add(frame)
                 frameIndex = 0
                  currentFrame = ""
            }else{
                frameIndex++
            }
        }
        if (frameIndex == 1){
             Frame frame = new Frame( rollSecuence : rollsSecuence[-1])
             frames.add(frame)
        }
        return frames

    }


  def pinsKnockDown(){

        def frameScore = 0
        if (rollSecuence.contains(SPARE)||(rollSecuence.contains(STRIKE)) ){
            frameScore = MAX_SCORE;
        }else{
             def rolls =  rollSecuence.replaceAll(FAIL,'0').toList()
            frameScore = rolls.sum {
            Integer.valueOf(it)
               }
        }
        this.score += frameScore

    }

    def extraScore(List framesRolls, int frameIndex){

        int extraScore = 0
        if (this.isSpare()) {
           extraScore = getSpareExtraScore(framesRolls,frameIndex)
        } else if (this.isStrike()) {
           extraScore = getStrikeExtraScore(framesRolls,frameIndex)
        }

       this.score += extraScore

    }

    def getSpareExtraScore(List framesRolls, int frameIndex){
          Frame nextFrame = framesRolls[frameIndex + 1]
            if (nextFrame.isStrike()){
                 return  MAX_SCORE
            }else{
                return Integer.valueOf(nextFrame.getFirsRoll())
            }
    }

    def getStrikeExtraScore(List framesRolls, int frameIndex){

        int extraScore
        Frame nextFrame = framesRolls[frameIndex + 1]
        int spareExtra = 0
        if (nextFrame.isSpare()) {
            extraScore = MAX_SCORE
        } else if (nextFrame.isStrike()) {
            Frame nextNextFrame = framesRolls[frameIndex + 2]
            int lastExtraScore = 0
            if (nextNextFrame.isStrike()){
                lastExtraScore = MAX_SCORE
            }else {
                lastExtraScore = Integer.valueOf(nextNextFrame.getFirsRoll())
            }
            extraScore = MAX_SCORE + lastExtraScore
        }else {
            extraScore = Integer.valueOf(nextFrame.getFirsRoll()) + Integer.valueOf(nextFrame.getSecondRoll())
        }
          return extraScore
    }

    def isSpare(){
        return rollSecuence.contains(SPARE)
    }

    def isStrike(){
        return rollSecuence.contains(STRIKE)
    }

    def getFirsRoll(){

         rollSecuence[0]

    }

    def getSecondRoll(){

         if (getFirsRoll() != STRIKE){
             return rollSecuence[1]
         }

    }

    def getScore(){
        return this.score
    }


}
