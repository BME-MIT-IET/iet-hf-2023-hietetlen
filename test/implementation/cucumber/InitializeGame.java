package implementation.cucumber;

import static org.junit.jupiter.api.Assertions.*;

import Control.LevelManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import View.GameFrame;

public class InitializeGame {

    private GameFrame gameFrame;

    @When("one of the players presses start")
    public void oneOfThePlayersPressesStart() {
        gameFrame = new GameFrame();
        gameFrame.getJMenuBar().getMenu(0).getItem(0).doClick();
    }

    @Then("The tiles get created")
    public void theTilesGetCreated() {
        assertTrue(LevelManager.getInstance().getTiles().size() >= 5);
    }

    @And("the virologists are placed on the board")
    public void theVirologistsArePlacedOnTheBoard() {
        assertEquals(LevelManager.getInstance().getVirologists().size(), 5);
    }
}
