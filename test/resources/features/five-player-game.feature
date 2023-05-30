Feature: Initialize a New Game

  @FivePlayers
  Scenario: Initialize the game board with five virologists
    When one of the players presses the add player button
    And restarts the game
    Then five virologists are placed on the board