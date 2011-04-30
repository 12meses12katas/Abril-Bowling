describe("Bowling Kata", function() {
    var game;
    beforeEach(function() {
      game = new Game();
    });
    
    it("Should score Zero when no pins are knocked", function() {
      for (var i = 0; i < 20; i++) {
        game.roll(0); 
      }
      expect(game.score()).toBe(0);
    });

    it("should return 20 with 1 in all rols", function() {
      for (var i = 0; i < 20; i++) {
        game.roll(1);
      };
      expect(game.score()).toEqual(20);
    });
    
    it("should return 40 with 2 in all rolls", function() {
      for (var i = 0; i < 20; i++) {
        game.roll(2);
      };
      expect(game.score()).toEqual(40);
    });
});  

var Game = function() {
  var totalScore = 0;

  var roll = function(number) {
    totalScore += number;
  };

  var score = function() {
    return totalScore;
  };

  return {
    roll: roll,
    score: score
  };
};
