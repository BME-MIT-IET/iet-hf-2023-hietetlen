package Model.Effects;

import Model.EffectHolder;
import Utils.Signal;
import Model.Virologist;
import Model.EffectHolders.Agent;
import Model.Behaviour;
import Model.Effect;

/**
 * The Glove enables it's wearer to return any
 * Agent that another Virologist tried to apply to them.
 */
public class Glove extends Behaviour implements Effect {

    private Agent prevAgent = null;
    private int counter = 0;
    private EffectHolder eh;

    /**
     * It throws the "a" Agent back to the "from" Virologist, if
     * it didn't already throw the "a" Agent back once.
     *
     * @param to The virologist whom the agent will be used on.
     * @param from The virologist who uses the agent.
     * @param a The agent that will be used.
     * @return Whether the Agent can be applied to the Virologist
     */
    public Signal addAgent(Virologist to, Virologist from, Agent a) {
        if (prevAgent == null || prevAgent != a) {
            prevAgent = a;
            from.addAgent(to, a);
            counter++;
            if (counter == 3) {
                eh.deactivate();
            }
            return Signal.Failed;
        }
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
        eh = effectHolder;
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
