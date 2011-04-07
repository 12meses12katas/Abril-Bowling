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
            TestRoll[] testRolls = {new TestRoll("XXXXXXXXXXXX",300)
                                    ,new TestRoll("9-9-9-9-9-9-9-9-9-9-",90)
                                    , new TestRoll("5/5/5/5/5/5/5/5/5/5/5",150)};
            

            
            foreach (TestRoll testRoll in testRolls)
            {
                Line line = new Line(testRoll.Rolls);
                Console.WriteLine(string.Format("Test: {0}",testRoll.Rolls ));
                Console.WriteLine(string.Format("Expected result: {0}", testRoll.Score));
                int calcScore = line.GetScore(testRoll.Rolls);
                Console.WriteLine(string.Format("Result: {0}", calcScore));
                Console.WriteLine(string.Format("Test Passed: {0}", testRoll.Test(calcScore)?"Yes":"No"));
                Console.WriteLine("-------------");
            }

            Console.WriteLine("Test Ended. Press Enter to continue...");
            Console.Read();


        }
    }
}
