Feature: Every player plays for one round

  @FullRound
  Scenario: A single round of the game is played
    Given the game has started
    When every player finishes their round
    Then it is the first players turn again