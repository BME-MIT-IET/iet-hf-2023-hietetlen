Feature: Disabled virologist tries pick up item

  Scenario: Disabled virologist tries pick up item from the tile
    Given A Tile with an item on it and a disabled virologist
    Then Press the interact button by the virologist
    Then The inventory's size increase by one