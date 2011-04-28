Feature: player fill the score card to obtain the total points

  The player roll n-balls and fill the score card. The machine tell the resulting points
  
  Each roll count in represented in the score card as the number of hits, or a '/' for the spares or a 'X' for the strikes.
  
  Scenario Outline: just a number
    Given a score card with <notation>
    When I ask the total points
    Then I should see <points>
    Examples:
      |  notation        | points  |
      |  "4"             | 4       |
      |  "6"             | 6       |
