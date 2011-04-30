describe("Bowling Kata", function() {
    it("Should score Zero when no pins are knocked down in the potential 21 rolls", function() {
      for (var i = 0; i < 20; i++) {
        game.roll(0); 
      }
      expect(game.score()).toBe(0);
    });
});  

var game = function() {
  var roll = function(number) {
    return 0;
  };

  var score = function() {
    return 0;
  };

  return {
    roll: roll,
    score: score
  };
}();
