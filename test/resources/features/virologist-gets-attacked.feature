Feature: A virologist attacks an other virologist

  @AttackVirologist
  Scenario: A virologist attack an other virologist with a virus
    Given there are two virologists on a tile
    When one attack the other
    Then a virus gets applied to the attacked virologist