using System;
using System.Collections.Generic;
using System.Text;

namespace Bowling.Logic
{
    public class Line
    {
        private List<Frame> frames;

        public Line(string rolls)
        {
            frames = new List<Frame>();
            StringBuilder stbRolls = new StringBuilder();
            bool inFrame = false;
            foreach (char roll in rolls)
            {
                if (frames.Count < 9)
                {
                    switch (roll)
                    {
                        case 'X':
                            inFrame = false;
                            stbRolls.Append(roll);
                            break;
                        default:
                            stbRolls.Append(roll);
                            inFrame = !inFrame;
                            break;
                    }

                    if (!inFrame)
                    {
                        frames.Add(new Frame(stbRolls.ToString()));
                        stbRolls = new StringBuilder();
                    }
                }
                else
                    stbRolls.Append(roll);

            }
            frames.Add(new LastFrame(stbRolls.ToString()));
        }

        public int GetScore(string rolls)
        {
            int score = 0;

            for (int i = 0; i < frames.Count ; i++)
            {
                int frameScore = frames[i].FrameSubScore();
                if (frames[i].Strike() && (i < frames.Count - 1))
                {
                    frameScore += frames[i + 1].Roll1Score();
                    switch (frames[i + 1].RollsCount())
                    {
                        case 1:
                            if (i < frames.Count - 2)
                                frameScore += frames[i + 2].Roll1Score();
                            break;
                        default:
                            frameScore += frames[i + 1].Roll2Score();
                            break;
                    }
                }
                if (frames[i].Spare() && (i < frames.Count - 1))
                    frameScore += frames[i + 1].Roll1Score();

                score += frameScore;
            }
            return score;
        }

    }
}
