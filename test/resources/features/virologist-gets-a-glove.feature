Feature: Virologist get a new glove

  @GetGlove
  Scenario: Create a new glove item and add it to a virologist
    Given there is a virologist on the board
    When he receives a new glove item
    Then his number of items increases by one