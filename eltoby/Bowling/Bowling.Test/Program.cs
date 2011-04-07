using System;
using System.Collections.Generic;
using System.Text;
using Bowling.Logic;
namespace Bowling.Test
{
    class Program
    {
        static void Main(string[] args)
        {
            TestRoll[] testRolls = {
                new TestRoll("XXXXXXXXXXXX",300)
                ,new TestRoll("9-9-9-9-9-9-9-9-9-9-",90)
                , new TestRoll("5/5/5/5/5/5/5/5/5/5/5",150)
            };


            BowlingCalculator calc = new BowlingCalculator();
            foreach (TestRoll testRoll in testRolls)
            {
                Line line = new Line(testRoll.Rolls);
                Console.WriteLine(string.Format("Test: {0}",testRoll.Rolls ));
                Console.WriteLine(string.Format("Expected result: {0}", testRoll.Score));
                int calcScore1 = line.GetScore(testRoll.Rolls);
                int calcScore2 = calc.GetScore(testRoll.Rolls);
                Console.WriteLine(string.Format("Result Method 1: {0}", calcScore1));
                Console.WriteLine(string.Format("Test 1 Passed: {0}", testRoll.Test(calcScore1) ? "Yes" : "No"));
                Console.WriteLine(string.Format("Result Method 2: {0}", calcScore2));
                Console.WriteLine(string.Format("Test 2 Passed: {0}", testRoll.Test(calcScore2)?"Yes":"No"));
                Console.WriteLine("-------------");
            }

            Console.WriteLine("Test Ended. Press Enter to continue...");
            Console.Read();


        }
    }
}
