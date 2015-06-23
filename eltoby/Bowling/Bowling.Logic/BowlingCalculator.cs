using System;
using System.Collections.Generic;
using System.Text;

namespace Bowling.Logic
{
    public class BowlingCalculator
    {

        private int getRollValue(char roll, char previous)
        {
            if (roll == '/')
                return 10 - getRollValue(previous);
            else
                return getRollValue(roll);
        }

        private int getRollValue(char roll)
        {
            switch (roll)
            {
                case 'X':
                    return 10;
                case '-':
                    return 0;
                default:
                    return int.Parse(roll.ToString());
            }
        }

        public int GetScore(string rolls)
        {
            char previousRoll = '\0';
            char previousRollTwo = '\0';

            int frameCount = 1;
            bool inframe = false;
            int totalScore = 0;
            
            foreach (char roll in rolls)
            {
                int multiplier = 1;
                if (frameCount < 12 && previousRollTwo == 'X')
                    multiplier++;

                if (frameCount < 11 && (previousRoll == 'X' || previousRoll == '/'))
                    multiplier++;
                
                switch (roll)
                {
                    case 'X':
                        inframe = false;
                        break;
                    default:
                        inframe = !inframe;
                        break;
                }

                if (!inframe)
                    frameCount++;

                int rollValue = getRollValue(roll, previousRoll);
                previousRollTwo = previousRoll;
                previousRoll = roll;

                totalScore += (rollValue  * multiplier);


            }
            return totalScore;
        }

    }
}
