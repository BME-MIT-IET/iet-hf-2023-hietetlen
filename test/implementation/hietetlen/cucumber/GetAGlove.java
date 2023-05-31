package implementation.hietetlen.cucumber;

import Control.RoundManager;
import Model.EffectHolders.Equipment;
import Model.Effects.Glove;
import Model.Tiles.Tile;
import Model.Virologist;
import View.GameFrame;
import View.RoundManagerView;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class GetAGlove {

    Virologist virologist;
    Tile tile;
    int count;

    @Given("there is a virologist on the board")
    public void thereIsAVirologistOnTheBoard() {
        tile = new Tile();
        virologist = new Virologist(tile);
        count = virologist.getEquipments().size();
    }

    @When("he receives a new glove item")
    public void heReceivesANewGloveItem() {
        var glove = new Equipment(new Glove());
        virologist.addEquipment(glove);
    }

    @Then("his number of items increases by one")
    public void hisNumberOfItemsIncreasesByOne() {
        assertEquals(count + 1, virologist.getEquipments().size());
    }
}
