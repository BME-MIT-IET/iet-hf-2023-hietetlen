package Model;

import Model.EffectHolders.Agent;
import Model.EffectHolders.Equipment;
import Model.Tiles.Tile;

/**
 * This class describes the types of actions with which the player can
 * control the Virologists.
 */
public interface InteractionSet {
    /**
     * Performs an interaction with a tile if possible
     */
    void tileInteract();

    /**
     * Applies an agent to a virologist if possible.
     *
     * @param v The virologist whom the agent is used on
     * @param a The agent that will be used on the given virologist.
     */
    void useAgent(Virologist v, Agent a);

    /**
     * Moves a virologist to a new tile if possible.
     *
     * @param t The tile where the virologist will be moved to.
     */
    void move(Tile t);

    /**
     * A virologist attempts to steal something from another virologist.
     *
     * @param v The virologist who will get robbed.
     * @param isEquipment Decides whether the stolen object is an equipment (true) or substance (false).
     */
    void steal(Virologist v, boolean isEquipment);

    /**
     * Creates a new agent from a given genetic code if possible.
     *
     * @param isVirus Decides whether the new agent is a virus (true) or a vaccine (false).
     * @param gc The genetic code that the agent is created from.
     */
    void makeAgent(boolean isVirus, GeneticCode gc);

    /**
     * Called when trying to use the "eq" equipment.
     *
     * @param eq The Equipment to use
     * @param to The Virologist to use the Equipment on
     */
    void useEquipment(Equipment eq, Virologist to);

    /**
     * Removes a given equipment from a virologist.
     *
     * @param eq The equipment that will be removed.
     */
    void removeEquipment(Equipment eq);
}
