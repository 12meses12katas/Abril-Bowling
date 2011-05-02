using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Kata
{
    public class Bowling
    {

        private class Throws : List<Throw>
        {
            private double turns = 0;
            private const int MAX_TURNS = 10;
      
            public new void Add(Throw aThrow)
            {
                 this.Insert(this.Count, aThrow);

                 aThrow.Throws = this;
                 aThrow.IsBonus = (turns >= MAX_TURNS);                 
                 
                turns += aThrow.TurnConsumed;

             }

            public Throw Next(Throw aThrow)
            {
                return this[this.IndexOf(aThrow) + 1];
            }

            public Throw Previous(Throw aThrow)
            {
                return this[this.IndexOf(aThrow) - 1];
            }

            public int CalculeScore()
            {
                int score = 0;
                foreach (Throw aThrow in this)
                {
                    score += aThrow.Score;
                }

                return score;
            }
        }

        private abstract class Throw
        {
            protected const int MAX_VALUE = 10;
           
            public Throws Throws { get; set; }

            public bool IsBonus { get; set; }
            
            public virtual int Value { get; set; }
            public abstract double TurnConsumed { get; }
            protected abstract int CalculeScore();
            
            public int Score
            {
                get
                {
                    if (IsBonus) return 0;
                    return CalculeScore();
                }
            }
            
            public Throw NextThrow()
            {
                return Throws.Next(this);
            }

            public Throw PreviousThrow()
            {
                return Throws.Previous(this);
            }
        }     
        
        private class SingleThrow : Throw
        {
            protected SingleThrow() { }
  
            public SingleThrow(string value)
            {
                Value = Convert.ToInt32(value);
            }

            protected override int CalculeScore() { 
                    return Value; 
            }

            public override double TurnConsumed
            {
                get { return 0.5; }
            }

        }

        private class MissThrow : SingleThrow
        {
            public MissThrow()
            {
                Value = 0;
            }
            protected override int CalculeScore()
            {
                return 0;
            }

        }
        
        private class SpareThrow : Throw
        {
           
            public SpareThrow()
            {

            }

            public override int  Value
            {
	                get 
	                {
                        return MAX_VALUE - PreviousThrow().Value;
	                }
	                set 
	                { 
		                throw new NotImplementedException();
	                }
            }
            
             protected override int CalculeScore() 
             { 
                return Value + NextThrow().Value; 
             }

            public override double TurnConsumed
            {
                get { return 0.5; }
            }


        }
        
        private class StrikeThrow : Throw
        {
            public StrikeThrow()
            {
                Value = MAX_VALUE;
            }

            protected override int CalculeScore() 
            { 
                return Value + NextThrow().Value + NextThrow().NextThrow().Value; 
            } 

            public override double TurnConsumed
            {
                get { return 1; }
            }

        }

        private Throws throws = new Throws();
        
        private const string SYMBOL_MISS = "-";
        private const string SYMBOL_SPARE = "/";
        private const string SYMBOL_STRIKE = "X";

        public int CalculeScore(string bowls)
        {
            foreach (var roll in bowls)
            {
                Throw aThow = CreateThrow(roll);
                throws.Add(aThow);
            }

            return throws.CalculeScore();
        }


        private Throw CreateThrow(char symbol)
        {
                string roll = symbol.ToString();

                Throw aThow = new SingleThrow(roll);
                            
                if (isStrike(roll))
                {
                    aThow = new StrikeThrow();
                }
                if (isSpare(roll))
                {
                    aThow = new SpareThrow();
                }
                if (isMiss(roll))
                {
                    aThow = new MissThrow();
                }
                            
                return aThow;
        }

        private bool isStrike(string aThrow)
        {
            return aThrow == SYMBOL_STRIKE;
        }

        private bool isSpare(string aThrow)
        {
            return aThrow == SYMBOL_SPARE;
        }

        private bool isMiss(string aThrow)
        {
            return aThrow == SYMBOL_MISS;
        } 
    }
}
