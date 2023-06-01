Feature: Virologist with full inventory

  Scenario: A virologist tries to pick up an item while it's inventory is full
    Given A virologist with full inventory and a tile with an axe on it
    Then The virologist press interact with the tile
    But The virologist inventory does not contain an axe
