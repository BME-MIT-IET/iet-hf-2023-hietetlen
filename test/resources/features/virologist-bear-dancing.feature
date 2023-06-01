Feature: Virologist step on Infected lab and got infected by Vitus Dance virus

Scenario: The virologist tries interact with the infected lab tile and got infected
  Given A virologist without glove and an Infected Lab tile
  Then The virologist tries to read the genetic code and got infected with vitus dance and the game is over
