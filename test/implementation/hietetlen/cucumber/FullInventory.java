package implementation.hietetlen.cucumber;


import Model.EffectHolders.Equipment;
import Model.Effects.Axe;
import Model.Effects.Glove;
import Model.Tiles.Shelter;
import Model.Tiles.Tile;
import Model.Virologist;
import io.cucumber.java.en.But;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.Assert.assertNotEquals;

public class FullInventory {
    Virologist virologist;
    Tile tile;
    Axe axe;
    Equipment equipment;
    @Given("A virologist with full inventory and a tile with an axe on it")
    public void a_virologist_with_full_inventory_and_a_tile_with_an_axe_on_it() {
        axe = new Axe();
        equipment = new Equipment(axe);
        tile = new Shelter(equipment);
        virologist = new Virologist(tile);
        for (int i = 0;i<virologist.getMaxEq();i++){
            Glove glove = new Glove();
            virologist.addEquipment(new Equipment((glove)));
        }
    }
    @Then("The virologist press interact with the tile")
    public void the_virologist_press_interact_button(){
        virologist.tileInteract();
    }

    @Then("The virologist inventory does not contain an axe")
    public void the_virologist_inventory_does_not_contain_an_axe() {
        assertNotEquals(true,virologist.getEquipments().contains(equipment));
    }
}
