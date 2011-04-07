using System;
using System.Collections.Generic;
using System.Text;

namespace Bowling.Test
{
    class TestRoll
    {
        string rolls;
        int score;

        public TestRoll(string rolls, int score)
        {
            this.rolls = rolls;
            this.score = score;
        }

        public string Rolls
        {
            get { return rolls; }
        }

        public int Score
        {
            get { return score; }
        }

        public bool Test(int calculatedScore)
        {
            return this.score == calculatedScore;
        }
    }
}
