package Model;

import Model.Effects.Vaccine;
import Model.EffectHolders.Agent;
import Utils.DefaultSubject;
import Utils.EventType;

import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * The GeneticCode is class which handles the creation of
 * Agents. Upon instantiation, it receives an Effect supplier
 * which determines the type of Agent that the given instance of
 * the class will produce.
 *
 * For each type of Agent, only a single instance of this class should exist,
 * because the instances of the GeneticCodes can be used to determine whether
 * any of the Virologists has collected all of them.
 */
public class GeneticCode extends DefaultSubject<GeneticCode> {

    private final java.util.List<Agent> createdAgents = new ArrayList<>();
    private final Supplier<Effect> effectSupplier;
    private final SubstanceContainer cost;
    private final int duration;

    /**
     * Creates a new instance of a genetic code.
     *
     * @param effectSupplier Creates new instances of the GeneticCode's Effect
     * @param cost The cost (nucleotid, amino acid) of the crafting of the agent that can be made from this genetic code
     * @param duration The duration of the effect of the agent that can be made from this genetic code
     */
    public GeneticCode(Supplier<Effect> effectSupplier, SubstanceContainer cost, int duration) {
        this.effectSupplier = effectSupplier;
        this.cost = cost;
        this.duration = duration;
    }

    /**
     * Creates a new virus from this genetic code. If the crafting was successful, the cost of the agent will be subtracted from it.
     * See also {@link #makeVaccine(SubstanceContainer)}.
     *
     * @param sc The reference of the substance container of a virologist.
     * @return The new virus agent (or null if the virologist did not have enough substance).
     */
    public Agent makeVirus(SubstanceContainer sc) {
        if (sc.use(cost)) {
            Agent a = new Agent(this, effectSupplier.get(), false);
            createdAgents.add(a);
            notifyObservers(EventType.Any);
            return a;
        }
        return null;
    }

    /**
     * Creates a new vaccine from this genetic code. If the crafting was successful, the cost of the agent will be subtracted from it.
     * See also {@link #makeVirus(SubstanceContainer)}.
     *
     * @param sc The reference of the substance container of a virologist.
     * @return The new vaccine agent (or null if the virologist did not have enough substance.
     */
    public Agent makeVaccine(SubstanceContainer sc) {
        if (sc.use(cost)) {
            Agent a = new Agent(this, new Vaccine(this),true);
            createdAgents.add(a);
            notifyObservers(EventType.Any);
            return a;
        }
        return null;
    }

    /**
     * @return The duration of the craftable agent's effect.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @return The cost of the craftable agent.
     */
    public SubstanceContainer getCost() {
        return cost;
    }

    public Agent getLast() { return createdAgents.get(createdAgents.size() - 1); }
}
