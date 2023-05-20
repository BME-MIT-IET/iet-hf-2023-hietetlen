package Model.Effects;

import Control.*;
import Model.Behaviour;
import Model.Effect;
import Model.EffectHolder;
import Utils.Signal;
import Model.Virologist;

/**
 * While stunning is active the effected Virologist is prevented
 * from moving or taking any actions. However, it lets other Virologists
 * rob the stunned one.
 */
public class Stunning extends Behaviour implements Effect {

    /**
     * Prevents the virologist from doing anything, by telling the RoundManager
     * that it has already finished it's round.
     *
     * @param v The virologist whose round just started.
     * @return The roundInit must be interrupted
     */
    public Signal roundInit(Virologist v) {
        RoundManager.getInstance().completeMove();
        RoundManager.getInstance().completeAction();
        return Signal.Interrupted;
    }

    /**
     * Lets the Virologist get robbed by returning Signal.Passed.
     *
     * @param who The virologist who is getting robbed.
     * @param by The virologist who is robbing virologist "{@code who}".
     * @param isEquipment Decides whether virologist "{@code by}" is stealing equipment (true) or substance (false)
     *                    from virologist "{@code who}".
     * @return The Virologist can get robbed
     */
    public Signal getRobbed(Virologist who, Virologist by, boolean isEquipment) {
        return Signal.Passed;
    }

    /**
     * Applies itself to the "v" Virologist, by adding itself to the Virologists
     * Behaviour list.
     *
     * @param v The virologist whom the effect is applied to.
     */
    public void apply(EffectHolder effectHolder, Virologist v) {
        v.addBehaviour(this);
        if (RoundManager.getInstance().started() && RoundManager.getInstance().getCurrentVirologist() == v){
            roundInit(v);
        }
    }

    /**
     * Removes its effect form the "v" Virologist, by removing itself from the Virologists
     * Behaviour list.
     *
     * @param v The virologist whom the effect is removed from.
     */
    public void remove(Virologist v) {
        v.removeBehaviour(this);
    }

}
