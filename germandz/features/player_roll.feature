Feature: player fill the score card to obtain the total points

  The player roll n-balls and fill the score card. The machine tell the resulting points
  
  Each roll count in represented in the score card as the number of hits, or a '/' for the spares or a 'X' for the strikes.
  
  Scenario Outline: score card complete
    Given a score card with <notation>
    When I ask the total score
    Then I should see <score>
  
  Scenarios: No rolls
      |  notation                  | score |
      |  ""                        |   0   |
                                           
  Scenarios: No spares or strikes          
      |  notation                  | score |
      |  "43"                      |   7   |
      |  "--------------------"    |   0   |
      |  "91919191919191919191"    | 100   |
      |  "9-9-9-9-9-9-9-9-9-9-"    |  90   |
                                           
  Scenarios: with spares                   
      |  notation                  | score |
      |  "5/5/5/5/5/5/5/5/5/5/5"   | 150   |
      |  "9/9-8--/72-481-63-8-"    |  92   |
                                           
  Scenarios: with strikes                   
      |  notation                  | score |
      |  "XXXXXXXXXXXX"            | 300   |
      |  "729-9-9-9/9/XX8-71"      | 137   |
      |  "-69/X63719-5/X7/7-"      | 135   |
      |  "1/-99-6-157-8/7-9-XX-"   | 100   |
      |  "357-3/-333X9/8/629/6"    | 112   |
      |  "8/X71718-9-XX9/X7-"      | 157   |
      |  "9/8/X7/53348/-78/-/8"    | 133   |
      |  "XX6-XX-97-7-9/9/7"       | 146   |
      |  "9/XX7/639/X9/9/9-"       | 179   |
      |  "349-3--95/X3--1X-7"      |  89   |
