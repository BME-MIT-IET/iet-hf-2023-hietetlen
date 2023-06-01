package implementation.hietetlen.cucumber;

import Model.Effect;
import Model.EffectHolders.Agent;
import Model.EffectHolders.Equipment;
import Model.Effects.Axe;
import Model.Effects.Stunning;
import Model.GeneticCode;
import Model.SubstanceContainer;
import Model.Tiles.Shelter;
import Model.Tiles.Tile;
import Model.Virologist;
import io.cucumber.java.an.E;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DisabledVirologist {
    Virologist virologist;
    Tile tile;
    int size = 0;
    @Given("A Tile with an item on it and a disabled virologist")
    public void a_tile_with_an_item_on_it_and_a_disabled_virologist() {
        Axe axe = new Axe();
        Equipment equipment = new Equipment(axe);
        tile = new Shelter(equipment);
        virologist = new Virologist(tile);
        GeneticCode gc =  new GeneticCode(() -> new Stunning(), new SubstanceContainer(20, 20), 2);
        Agent agent = new Agent(gc, new Stunning(),false);
        virologist.addAgent(virologist,agent);
        size = virologist.getEquipments().size();
    }
    @Then("The virologist press interact button")
    public void the_virologist_press_interact_button() {
        virologist.tileInteract();
    }
    @Then("The inventory's size increase by one")
    public void the_inventory_s_size_increase_by_one() {
        assertNotEquals(size,virologist.getEquipments().size());
    }
}
