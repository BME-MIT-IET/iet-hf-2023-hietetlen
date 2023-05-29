Feature: Initialize a New Game

@InitializeGame
Scenario: Initialize the game board and the characters
  Given the game board does not exist yet
  When the one of the players presses start
  Then The tiles get created
  And the virologists are placed on the board