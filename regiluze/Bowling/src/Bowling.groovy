
/**
 * Created by IntelliJ IDEA.
 * User: ruben
 * Date: 30/04/11
 * Time: 10:26
 * To change this template use File | Settings | File Templates.
 */
class Bowling {

    int FRAME_NUM = 10


    def score(String rollsSecuence){

        int totalScore = 0
        List framesRolls = Frame.getFrames(rollsSecuence)
        int frameIndex = 0
        framesRolls.eachWithIndex { frame,i ->
             if (i<FRAME_NUM){
               frame.pinsKnockDown()
               frame.extraScore(framesRolls,i)
               totalScore += frame.getScore()
            }
        }
        return totalScore

    }

}
