Feature: player create a game

  The player select a score card type. The machine give a score card
  
  Each score card calculate the total points in a different way.
  
  Scenario: player select "calculate after finish"
    Given a score card type named "caf"
    When I ask for the score card
    Then I should obtain a score card with type "caf"
    
  Scenario: player select "partial updates"
    Given a score card type named "live"
    When I ask for the score card
    Then I should obtain a score card with type "live"