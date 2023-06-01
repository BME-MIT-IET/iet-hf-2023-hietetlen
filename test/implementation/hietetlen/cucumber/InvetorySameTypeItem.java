package implementation.hietetlen.cucumber;

import Model.EffectHolders.Equipment;
import Model.Effects.Axe;
import Model.Tiles.Shelter;
import Model.Tiles.Tile;
import Model.Virologist;
import io.cucumber.java.an.E;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertEquals;

public class InvetorySameTypeItem {
    Virologist virologist;
    Tile tile;

    @Given("A virologist with an axe on it's inventory and a tile with the same type of item on it")
    public void a_virologist_with_an_axe_on_it_s_inventory_and_a_tile_with_the_same_type_of_item_on_it() {
        Axe axe1 = new Axe();
        Axe axe2 = new Axe();
        Equipment eq1 = new Equipment(axe1);
        Equipment eq2 = new Equipment(axe2);
        tile = new Shelter(eq1);
        virologist = new Virologist(tile);
        virologist.addEquipment(eq2);
    }

    @Then("The virologist try to pick up the item")
    public void the_virologist_try_to_pick_up_the_item() {
        virologist.tileInteract();
    }

    @Then("The virologist has two of the same type item")
    public void the_virologist_has_two_of_the_same_type_item() {
        assertEquals(true, virologist.getEquipments().size() == 2);
    }
}
