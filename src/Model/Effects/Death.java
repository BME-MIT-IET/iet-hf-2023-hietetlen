package Model.Effects;

import Utils.Signal;
import Model.Virologist;
import Model.EffectHolders.Agent;

/**
 * Death is the permanent version of being stunned.
 */
public class Death extends Stunning {
    /**
     * Prevents the usage on the dead Virologist.
     *
     * @param to The virologist whom the agent will be used on.
     * @param from The virologist who uses the agent.
     * @param a The agent that will be used.
     * @return Signal.Failed
     */
    public Signal addAgent(Virologist to, Virologist from, Agent a) {
        return Signal.Interrupted;
    }
}
