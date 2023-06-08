Feature: Virologist pick up same item
  Scenario: The virologist tries to pick up an item of a type of which he already has
    Given A virologist with an axe on it's inventory and a tile with the same type of item on it
    Then The virologist try to pick up the item
    Then The virologist has two of the same type item