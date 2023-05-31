package implementation.hietetlen.cucumber;

import Control.RoundManager;
import Model.Virologist;
import View.GameFrame;
import View.RoundManagerView;
import implementation.testingutils.SwingUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

public class EveryPlayerTurn {

    private GameFrame gameFrame;
    private Virologist virologist;

    @Given("the game has started")
    public void theGameHasStarted() {
        gameFrame = new GameFrame();
        virologist = RoundManager.getInstance().getCurrentVirologist();
    }

    @When("every player finishes their round")
    public void everyPlayerFinishesTheirRound() {
        var components = gameFrame.getComponents();
        JButton button = (JButton)SwingUtils.getChildComponentByName(gameFrame,"roundCompleteButton");
        button.doClick();
        for (int i = 0; i < 3; i++) {
            assertNotEquals(virologist, RoundManager.getInstance().getCurrentVirologist());
            button.doClick();
        }
    }

    @Then("it is the first players turn again")
    public void itSTheFirstPlayerSTurnAgain() {
        System.out.println("");
        assertEquals(virologist, RoundManager.getInstance().getCurrentVirologist());
    }
}
