package Model.Tiles;

import Model.EffectHolders.Equipment;
import Model.Virologist;
import Utils.EventType;

/**
 * The Shelter Tile gives any Virologist it's Equipment
 * which interacts with it. However, only a single instance is
 * stored, meaning it can only do so once.
 */
public class Shelter extends Tile {

    private Equipment equipment;

    /**
     * Constructor for the Shelter Tile
     *
     * @param equipment the Equipment stored by this tile.
     */
    public Shelter (Equipment equipment) {
        this.equipment = equipment;
    }

    /**
     * If it still has an Equipment, it gives it to the "v" Virologist which interacted with it.
     *
     * @param v The virologist that wants to interact with the tile.
     */
    @Override
    public void interact(Virologist v) {
        if (equipment != null) {
            v.addEquipment(equipment);
            equipment = null;
            notifyObservers(EventType.Any);
        }
    }
}
