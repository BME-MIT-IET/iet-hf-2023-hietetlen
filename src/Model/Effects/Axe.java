package Model.Effects;

import Model.EffectHolder;
import Utils.Signal;
import Model.Virologist;
import Model.Behaviour;
import Model.Effect;
import Model.EffectHolders.Equipment;

/**
 * This class can be used as an Equipment by the Virologists.
 * It allows it's current holder to kill a neighbouring Virologist.
 */
public class Axe extends Behaviour implements Effect {

    private EffectHolder eh;

    /**
     * Uses the equipment on an other Virologist, if the equipment is an Axe.
     *
     * @param eq the equipment which might be an Axe
     * @param to the Virologist whom the Axe will be used
     * @return a Signal value, Passed: usage was successful, Failed: usage was not successful
     */
    public Signal useEquipment(Equipment eq, Virologist to) {
        if (eq.getEffect().equals(this)) {
            to.addBehaviour(new Death());
            eh.deactivate();
            return Signal.Passed;
        }
        return Signal.Failed;
    }

    /**
     * Applies the Axe's effect on the "v" Virologist.
     *
     * @param effectHolder The value which will be the Axe's "eh" parameter.
     * @param v The virologist whom the effect is applied to.
     */
    @Override
    public void apply(EffectHolder effectHolder, Virologist v) {
        v.addBehaviour(this);
        eh = effectHolder;
    }

    /**
     * Removes the Axe's effect of the "v" Virologist.
     *
     * @param v The virologist whom the effect is removed from.
     */
    @Override
    public void remove(Virologist v) {
        v.removeBehaviour(this);
    }

}
