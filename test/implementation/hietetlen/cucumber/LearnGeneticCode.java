package implementation.hietetlen.cucumber;

import Control.RoundManager;
import Model.EffectHolders.Agent;
import Model.EffectHolders.Equipment;
import Model.Effects.Glove;
import Model.Effects.MemoryLoss;
import Model.GeneticCode;
import Model.Tiles.Lab;
import Model.Tiles.Tile;
import Model.Virologist;
import View.GameFrame;
import View.RoundManagerView;
import implementation.testingutils.CreationUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;

public class LearnGeneticCode {

    Virologist virologist;
    Lab lab;
    Agent agent;
    int count;
    @Given("there is a virologist on a lab field")
    public void thereIsAVirologistOnALabField() {
        var gc = CreationUtils.CreateGeneticCode(() -> new MemoryLoss());
        lab = new Lab(gc);
        virologist = new Virologist(lab);
    }

    @When("when he learns the genetic code")
    public void whenHeLearnsTheGeneticCode() {
        count = virologist.getGeneticCodes().size();
        virologist.tileInteract();
    }

    @Then("it will be added to his inventory")
    public void itWillBeAddedToHisInventory() {
        assertEquals(count + 1, virologist.getGeneticCodes().size());
    }
}
