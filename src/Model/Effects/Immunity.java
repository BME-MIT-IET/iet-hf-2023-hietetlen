package Model.Effects;

import Model.EffectHolder;
import Utils.Signal;
import Model.Virologist;
import Model.EffectHolders.Agent;
import Model.Behaviour;
import Model.Effect;

import java.util.Random;

/**
 * Immunity is an Effect and Behaviour which prevents any
 * Agent from being applied to the immune Virologist.
 */
public class Immunity extends Behaviour implements Effect {
    private final Random rnd;
    protected float effectiveness;

    /**
     * Constructor. Creates a new instance of an Immunity.
     *
     * @param effectiveness value of the effectiveness.
     */
    public Immunity(int effectiveness) {
        rnd = new Random();
        this.effectiveness = effectiveness;
    }

    /**
     * Prevents the "a" Agent from being applied to the Virologist.
     * Based on the "effectiveness" attributes value, it might not be able to prevent it.
     *
     * @param to The virologist whom the agent will be used on.
     * @param from The virologist who uses the agent.
     * @param a The agent that will be used.
     * @return Signal.Passed if the infestation was successful, Signal.Failed if not successful
     */
    public Signal addAgent(Virologist to, Virologist from, Agent a) {
        return rnd.nextFloat() < effectiveness ? Signal.Failed : Signal.Passed;
    }

    /**
     * Applies itself to the "v" Virologist, by adding itself to the Virologists
     * Behaviour list.
     *
     * @param v The virologist whom the effect is applied to.
     */
    public void apply(EffectHolder effectHolder, Virologist v) {
        v.addBehaviour(this);
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
