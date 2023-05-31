package implementation.hietetlen.cucumber;

import Control.RoundManager;
import Model.EffectHolders.Agent;
import Model.EffectHolders.Equipment;
import Model.Effects.Glove;
import Model.Effects.MemoryLoss;
import Model.GeneticCode;
import Model.Tiles.Tile;
import Model.Virologist;
import View.GameFrame;
import View.RoundManagerView;
import implementation.testingutils.CreationUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.*;

public class VirologistAttack {

    Virologist virologistA;
    Virologist virologistB;
    Tile tile;
    Agent agent;
    int count;

    @Given("there are two virologists on a tile")
    public void thereAreTwoVirologistsOnATile() {
        tile = new Tile();
        virologistA = CreationUtils.CreateVirologistWithVirus(tile, null, true);
        virologistB = new Virologist(tile);
    }

    @When("one attack the other")
    public void oneAttackTheOther() {
        agent = virologistA.getAgents().get(0);
        count = virologistA.getAgents().size();
        virologistA.useAgent(virologistB, agent);
    }

    @Then("a virus gets applied to the attacked virologist")
    public void aVirusGetsAppliedToTheAttackedVirologist() {
        assertNotEquals(count, virologistA.getAgents().size());
    }
}
