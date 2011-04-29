Feature: player create a game

  The player select a score card type. The machine give a score card
  
  Each score card calculate the total points in a different way.
  
  Scenario: player start the game
    When I ask for the score card
    Then I should obtain a score card
