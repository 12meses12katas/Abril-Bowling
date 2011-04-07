using System;
using System.Collections.Generic;
using System.Text;

namespace Bowling.Logic
{
    class Frame
    {
        protected char roll1;
        protected char roll2;

        public Frame(string rolls)
        {
            foreach (char roll in rolls)
                addRoll(roll);
        }

        protected  virtual  void addRoll(char roll)
        {
            if (roll1 == '\0')
                roll1 = roll;
            else
                roll2 = roll;
        }
        public virtual int RollsCount()
        {
            if (roll2 == '\0')
                return 1;
            else
                return 2;
        }

        public int Roll1Score()
        {
            return getRollScore(roll1);
        }

        public int Roll2Score()
        {
            return getRollScore(roll2);
        }
      
        protected int getRollScore(char roll)
        {
            switch (roll)
            {
                case 'X':
                case '/':
                    return 10;           
                case '-':
                    return 0;
                default:
                    return int.Parse(roll.ToString());
            }
        }

        public bool Strike()
        {
            return (roll1 == 'X');
        }

        public bool Spare()
        {
            return (roll2 == '/');
        }

        public virtual  int FrameSubScore()
        {
            if (Strike() || Spare())
                return 10;
            else
                return Roll1Score() + Roll2Score();
        }
    }
}
