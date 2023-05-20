package Model.Tiles;

import Model.SubstanceContainer;
import Model.Virologist;
import Utils.EventType;

/**
 * The Warehouse Tile gives a set amount of substances to
 * any Virologist which interacts with it.
 */
public class Warehouse extends Tile {

    private final SubstanceContainer sc = new SubstanceContainer(20,20,20);
    private boolean destroyed = false;

    /**
     * Gives the "v" Virologist a set amount of substances.
     *
     * @param v The virologist that wants to interact with the tile.
     */
    @Override
    public void interact(Virologist v) {
        if (!destroyed) {
            v.gainSubstance(sc);
            notifyObservers(EventType.Any);
        }
    }

    /**
     * By setting the destroyed attribute false, it disables the Tile's ability to give away substances.
     */
    @Override
    public void destroy() {
        if (!destroyed) notifyObservers(EventType.Any);
        destroyed = true;
    }

}
