package Model.Tiles;

import Model.GeneticCode;
import Model.Virologist;
import Utils.DefaultSubject;
import Utils.EventType;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the default Tile, the instances and it's subclasses' instances of
 * which the game's level is build of.
 */
public class Tile extends DefaultSubject<Tile> {

    protected final List<Tile> neighbours = new ArrayList<>();
    protected final List<Virologist> virologists = new ArrayList<>();

    /**
     * Adds the "n" tile as it's new neighbour.
     *
     * @param n The neighbour to add
     */
    public void setNeighbours(Tile n) {
        this.neighbours.add(n);
        n.neighbours.add(this);
        notifyObservers(EventType.Any);
    }

    /**
     *
     * @return The list of neighbouring tiles.
     */
    public List<Tile> getNeighbours() {
        return neighbours;
    }

    /**
     *
     * @return The list of Virologists currently on the tile.
     */
    public List<Virologist> getVirologists() {
        return virologists;
    }

    /**
     * Performs an interaction with a virologist that is standing on the tile.
     *
     * @param v The virologist that wants to interact with the tile.
     */
    public void interact(Virologist v) {
    }

    /**
     * Destroys the Tile, making it unusable.
     */
    public void destroy() {
    }

    /**
     * Adds a virologist to the tile's storage that represents the virologists standing on the tile.
     *
     * @param v The virologist being added.
     */
    public void addVirologist(Virologist v) {
        if (v == null) throw new IllegalArgumentException("The parameter 'v' cannot be null.");
        virologists.add(v);
        notifyObservers(EventType.Any);
    }

    /**
     * Removes a virologist from the tile's storage that represents the virologists standing on the tile.
     *
     * @param v The virologist being removed.
     */
    public void removeVirologist(Virologist v) {
        if (v == null) throw new IllegalArgumentException("The parameter 'v' cannot be null.");
        virologists.remove(v);
        notifyObservers(EventType.Any);
    }

}
