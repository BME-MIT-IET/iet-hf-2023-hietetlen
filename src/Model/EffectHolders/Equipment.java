package Model.EffectHolders;

import Model.EffectHolder;
import Model.Virologist;
import Model.Effect;

/**
 * The Equipment class adds a non-expiring new behaviour to the
 * Virologists. However, the Equipment can be removed by it's wearer
 * or stolen by other Virologists. It applies behaviour by applying
 * the Effect that it holds on the Virologist.
 */
public class Equipment implements EffectHolder {

    private final Effect effect;
    private Virologist owner;

    /**
     * Creates a new instance of an equipment.
     * @param effect The effect which is applied to the virologist who collects the equipment.
     */
    public Equipment(Effect effect) {
        this.effect = effect;
        owner = null;
    }

    /**
     * Sets the owner of the equipment to a new virologist.
     * @param newOwner The virologist who will be the new owner.
     */
    public void setOwner(Virologist newOwner) {
        if (owner != null) effect.remove(owner);
        owner = newOwner;
        if (owner != null) effect.apply(this, owner);
    }

    /**
     * Returns the effect which is applied.
     * @return The effect which is applied to the virologist who collects the equipment.
     */
    public Effect getEffect() {
        return effect;
    }

    /**
     * Deactivates the effect on the equipment's owner (Virologist).
     */
    public void deactivate() {
        effect.remove(owner);
        owner.removeEquipment(this);
    }

}
