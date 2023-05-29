Feature: Initialize a New Game

@InitializeGame
Scenario: Initialize the game board with five virologists
  When one of the players presses start
  Then The tiles get created
  And the virologists are placed on the board