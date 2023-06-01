package implementation.hietetlen.cucumber;

import Control.LevelManager;
import View.GameFrame;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FivePlayerGame {

    private GameFrame gameFrame;

    @io.cucumber.java.en.When("one of the players presses the add player button")
    public void oneOfThePlayersPressesTheAddPlayerButton() {
        gameFrame = new GameFrame();
        gameFrame.getJMenuBar().getMenu(0).getItem(1).doClick();
    }

    @io.cucumber.java.en.And("restarts the game")
    public void restartsTheGame() {
        gameFrame.getJMenuBar().getMenu(0).getItem(0).doClick();
    }

    @io.cucumber.java.en.Then("five virologists are placed on the board")
    public void fiveVirologistsArePlacedOnTheBoard() {
        assertEquals(5, LevelManager.getInstance().getVirologists().size());
    }
}
