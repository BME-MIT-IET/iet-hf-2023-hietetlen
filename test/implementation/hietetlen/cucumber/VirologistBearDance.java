package implementation.hietetlen.cucumber;

import static org.junit.jupiter.api.Assertions.*;

import Control.LevelManager;
import Control.RoundManager;
import Model.Behaviour;
import Model.EffectHolders.Agent;
import Model.Effects.BearDance;
import Model.Effects.VitusDance;
import Model.GeneticCode;
import Model.SubstanceContainer;
import Model.Tiles.InfectedLab;
import Model.Tiles.Tile;
import Model.Virologist;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import View.GameFrame;

public class VirologistBearDance {
    private Virologist virologist;
    private static GeneticCode vitusDance;
    Tile tile1;
    Tile tile2;
    RoundManager roundManager;
    LevelManager levelManager;
    @Given("A virologist without glove and an Infected Lab tile")
    public void a_virologist_without_glove_and_an_infected_lab_tile() {

        vitusDance = new GeneticCode(() -> new VitusDance(), new SubstanceContainer(20, 20), 2);

        tile1 = new Tile();
        tile2 = new InfectedLab(vitusDance, 100000);
        tile2.setNeighbours(tile1);
        tile1.setNeighbours(tile2);
        virologist = new Virologist(tile1);


    }

    @Then("The virologist tries to read the genetic code and got infected with vitus dance and the game is over")
    public void the_virologist_tries_to_read_the_genetic_code_and_got_infected_with_bear_dance_virus() {
        virologist.move(tile2);
        assertTrue(virologist.getCurrentTile() == tile2);
        virologist.tileInteract();
    }




}
