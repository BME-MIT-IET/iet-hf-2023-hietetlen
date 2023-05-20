package Model.EffectHolders;

import Control.*;
import Model.Effect;
import Model.EffectHolder;
import Model.GeneticCode;
import Utils.EventType;
import Model.Virologist;

/**
 * The Agent class holds, starts then removes Effects from
 * the Virologist. A single instance only holds a single type
 * of Effect.
 */
public class Agent implements EffectHolder {

    private Virologist virologist;
    private final GeneticCode geneticCode;
    private final Effect effect;
    private final boolean isVaccine;
    private int roundCounter;

    /**
     * Creates a new instance of an agent.
     * @param geneticCode The genetic code from which the agent can be created.
     * @param effect The effect that the agent applies on a virologist when used on them.
     * @param isVaccine A boolean which decides whether the agent will be a vaccine (true) or virus (false).
     */
    public Agent(GeneticCode geneticCode, Effect effect, boolean isVaccine) {
        this.effect = effect;
        this.roundCounter = geneticCode.getDuration();
        this.geneticCode = geneticCode;
        this.isVaccine = isVaccine;
    }

    /**
     * Applies the agent's effect on a virologist.
     * @param v The virologist whom the effect will be applied on.
     */
    public void applyEffect(Virologist v) {
        RoundManager.getInstance().subscribe(this::timeElapsed, EventType.RoundInit);
        effect.apply(this, v);
        virologist = v;
    }

    /**
     * Returns the genetic code.
     * @return The genetic code from which the agent can be created.
     */
    public GeneticCode getGeneticCode() {
        return geneticCode;
    }

    /**
     * Returns whether the agent is a vaccine or not.
     * @return Whether the agent is a vaccine (true) or a virus (false).
     */
    public boolean isVaccine() {
        return isVaccine;
    }

    /**
     * Decreases the Agent's roundCounter, and removes the effect of the Agent if the value equals 0.
     */
    private void timeElapsed() {
        roundCounter--;
        if (roundCounter == 0) {
            effect.remove(virologist);
            RoundManager.getInstance().unsubscribe(this::timeElapsed, EventType.RoundInit);
        }
    }

    /**
     * Deactivates the Agent's effect on the virologist.
     */
    @Override
    public void deactivate() {
        effect.remove(virologist);
    }
}
