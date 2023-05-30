package implementation.hietetlen.cucumber;

import Control.RoundManager;
import Model.Virologist;
import View.GameFrame;
import View.RoundManagerView;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.swing.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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
        for (var comp : components) {
            if (comp instanceof RoundManagerView) {
                for (int i = 0; i < 2; i++) {
                    ((JButton)comp.getComponentAt(700, 80)).doClick();
                }
            }
        }
    }

    @Then("it is the first players turn again")
    public void itSTheFirstPlayerSTurnAgain() {
        System.out.println("");
        assertNotEquals(virologist, RoundManager.getInstance().getCurrentVirologist());
    }
}
