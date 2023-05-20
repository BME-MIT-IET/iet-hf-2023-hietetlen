package Model.Effects;

import Model.EffectHolder;
import Model.Virologist;
import Model.Effect;

/**
 * The Bag can be used by a Virologist to increase
 * their substance inventory capacity;
 */
public class Bag implements Effect {

    private final int deltaCapacity;

    /**
     * Construtor. Creates a new instance of a bag.
     * @param deltaC An integer which decides by how much the virologist's inventory capacity increases.
     */
    public Bag (int deltaC) {
        this.deltaCapacity = deltaC;
    }

    /**
     * Applies the Bag's effect on the "v" Virologist.
     * @param effectHolder - not used
     * @param v The virologist whom the effect is applied to.
     */
    public void apply(EffectHolder effectHolder, Virologist v) {
        v.getSubstanceContainer().changeCapacity(deltaCapacity);
    }

    /**
     * Removes the Bag's effect of the "v" Virologist.
     * @param v The virologist whom the effect is removed from.
     */
    public void remove(Virologist v) {
        v.getSubstanceContainer().changeCapacity(-deltaCapacity);
    }

}
