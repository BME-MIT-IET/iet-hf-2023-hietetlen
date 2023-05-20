package Model.Effects;

import Model.*;
import Model.EffectHolders.Agent;
import Utils.Signal;

/**
 * The Vaccine class provides a general immunity against any
 * Effect which can apply to the Virologists.
 */
public class Vaccine extends Behaviour implements Effect {
    private final GeneticCode geneticCode;

    /**
     * Creates a new instance of a vaccine effect.
     *
     * @param geneticCode The genetic code that the Vaccine was created from.
     */
    public Vaccine(GeneticCode geneticCode) {
        this.geneticCode = geneticCode;
    }

    /**
     * If the agents GeneticCode being applied to the Virologist matches the GeneticCode
     * that the vaccine is effective against, it blocks the agent from being applied.
     *
     * @param from The virologist who uses the agent.
     * @param to The virologist whom the agent will be used on.
     * @param a The agent that will be used.
     * @return Whether the vaccine could prevent the usage of the agent
     */
    @Override
    public Signal addAgent(Virologist from, Virologist to, Agent a) {
        return (!a.isVaccine() && a.getGeneticCode() == geneticCode) ? Signal.Failed : Signal.Passed;
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
