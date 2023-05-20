package Model.Tiles;

import Model.GeneticCode;
import Model.Virologist;

/**
 * The Lab Tile teaches it's contained GeneticCode to
 * any Virologist which interacts with it.
 */
public class Lab extends Tile {
    protected GeneticCode geneticCode;

    /**
     * Constructor of the Lab Tile
     *
     * @param geneticCode The GeneticCode taught by the Lab Tile
     */
    public Lab(GeneticCode geneticCode) {
        this.geneticCode = geneticCode;
    }

    /**
     * Teaches the "v" Virologist the GeneticCode stored on this tile, by
     * calling the Virologists CollectGeneticCode method.
     *
     * @param v The virologist that wants to interact with the tile.
     */
    @Override
    public void interact(Virologist v) {
        v.collectGeneticCode(geneticCode);
    }
}