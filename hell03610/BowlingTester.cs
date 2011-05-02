using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NUnit.Framework;


namespace Kata
{
    [TestFixture]
    public class BowlingTester
    {

        [Test]
        public void All_fails_should_be_0()
        {
            Bowling bowling = new Bowling();

            int score = bowling.CalculeScore("--------------------");
            
            Assert.AreEqual(0, score);
        }

        [Test]
        public void Single_scores_should_be_single_added()
        {
            Bowling bowling = new Bowling();

            int score = bowling.CalculeScore("-1-2----------------");

            Assert.AreEqual(3, score);
        }

        [Test]
        public void Single_semi_strike()
        {
            Bowling bowling = new Bowling();

            int score = bowling.CalculeScore("-12/----------------");

            Assert.AreEqual(11, score);
        }

        [Test]
        public void Semi_strike_with_bowls_after()
        {
            Bowling bowling = new Bowling();

            int score = bowling.CalculeScore("--2/1---------------");

            Assert.AreEqual(12, score);
        }

        [Test]
        public void Single_strike()
        {
            Bowling bowling = new Bowling();

            int score = bowling.CalculeScore("-1X----------------");

            Assert.AreEqual(11, score);
        }

        [Test]
        public void Strike_with_bowls_after()
        {
            Bowling bowling = new Bowling();

            int score = bowling.CalculeScore("X12----------------");

            Assert.AreEqual(16, score);
        }

        [Test]
        public void All_strikes_12_rolls()
        {
            Bowling bowling = new Bowling();

            int score = bowling.CalculeScore("XXXXXXXXXXXX");

            Assert.AreEqual(300, score);
        }

        [Test]
        public void All_semi_strikes_21_rolls()
        {
            Bowling bowling = new Bowling();

            int score = bowling.CalculeScore("5/5/5/5/5/5/5/5/5/5/5");

            Assert.AreEqual(150, score);
        }

        [Test]
        public void All_single_and_miss_20_rolls()
        {
            Bowling bowling = new Bowling();

            int score = bowling.CalculeScore("9-9-9-9-9-9-9-9-9-9-");

            Assert.AreEqual(90, score);
        }
    }
}
