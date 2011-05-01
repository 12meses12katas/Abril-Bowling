describe("Bowling Kata", function() {
    var game;
    beforeEach(function() {
      game = new Game();
    });
    
    it("Should score Zero when no pins are knocked", function() {
      rollPins(20, 0);
      expect(game.getScore()).toBe(0);
    });

    it("should return 20 with 1 in all rols", function() {
      rollPins(20, 1);
      expect(game.getScore()).toEqual(20);
    });
    
    it("should return 40 with 2 in all rolls", function() {
      rollPins(20, 2);
      expect(game.getScore()).toEqual(40);
    });

    it("should return 20 after one spare made of 3 fives", function() {
      spare();
      game.roll(5);
      rollPins(17, 0);
      expect(game.getScore()).toEqual(20);
    });
    
    it("should return 24 after a strike and a 3 and a 4", function(){
      strike();
      game.roll(3);
      game.roll(4);
      rollPins(16, 0);
      expect(game.getScore()).toEqual(24);
    });

    it("should return 300 for a perfect game", function() { 
      for (var i = 0; i < 21; i+=1) {
        game.roll(10);
      }
      expect(game.getScore()).toEqual(300);
    });
    
    function rollPins(numberOfRolls, numberOfPins){
      for (var i = 0; i < numberOfRolls; i+=1) {
        game.roll(numberOfPins);
      };
    };

    function spare(){
      game.roll(5);
      game.roll(5);
    };

    function strike(){
      game.roll(10);
    };
});  

var Game = function() {
  var totalScore = 0;
  var rolls = [];
  var currentRoll = 0;

  var roll = function(number) {
    rolls[currentRoll] = number;
    currentRoll += 1;
  };

  var getScore = function() {
    var i = 0;
    for (var frame = 0; frame < 10; frame+=1) {
      if ( isStrike(i) ) {
        totalScore += 10;
        if (Number(rolls[i+1]))
          totalScore += Number(rolls[i+1]);
        if (Number(rolls[i+2]))
          totalScore += Number(rolls[i+2]);
        i += 1;
      }
      else if ( isSpare(i) ) {
        totalScore += (10 + Number(rolls[i+2]));
        i += 2;
      }
      else {
        totalScore += (Number(rolls[i]) + Number(rolls[i+1]));
        i += 2;
      }
    };
    return totalScore;
  };

  var isSpare = function(index) {
    return (Number(rolls[index]) + Number(rolls[index+1]) === 10);
  };

  var isStrike = function(index) {
    return (Number(rolls[index]) === 10);
  };
  return {
    roll: roll,
    getScore: getScore
  };
};
