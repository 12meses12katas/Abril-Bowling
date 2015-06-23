using System;
using System.Collections.Generic;
using System.Text;

namespace Bowling.Logic
{
    class LastFrame : Frame
    {

        public LastFrame(string rolls):base(rolls)
        {
        }
        protected char roll3;

        public int Roll3Score()
        {
            return getRollScore(roll3);
        }

        protected  override void addRoll(char roll)
        {
            if (roll1 == '\0' || roll2 == '\0')
                base.addRoll(roll);
            else
                roll3 = roll;
        }

        public override int RollsCount()
        {
            if (roll3 == '\0')
                return base.RollsCount();
            else
                return 3;
        }

        public override int FrameSubScore()
        {
            if (Strike())
                return 10 + Roll2Score() + Roll3Score();
            else
                if (Spare())
                    return 10 + Roll3Score();
                else
                    return base.FrameSubScore();
        }
    }
}
