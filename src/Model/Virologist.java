package Model;

import Control.LevelManager;
import Control.RoundManager;
import Model.EffectHolders.Agent;
import Model.EffectHolders.Equipment;
import Model.Tiles.Tile;
import Utils.DefaultSubject;
import Utils.EventType;
import Utils.Signal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

/**
 * The Virologist class serves as the player character in the game.
 * It contains it's own behaviours that implement the default control scheme and
 * interactions, however it also contains a list of foreign behaviours which can
 * alter, expand or replace it's default functions.
 */
public class Virologist extends DefaultSubject<Virologist> implements InteractionSet {

    //region Attributes
    //Containers
    private final SubstanceContainer substanceContainer;
    private final List<GeneticCode> geneticCodes;
    private final List<Equipment> equipments;
    private final List<Agent> agents;


    private final List<Behaviour> behaviours;
    //States
    private Tile currentTile;
    private int maxEq = 3; //Maximum concurrent equipment count
    //endregion

    /**
     * This constructor initializes the Virologist's components
     * and sets the starting tile.
     * @param tile The tile where the virologist spawns.
     */
    public Virologist(Tile tile) {
        substanceContainer = new SubstanceContainer(0,0,100);
        geneticCodes = new ArrayList<>();
        equipments = new ArrayList<>();
        agents = new ArrayList<>();
        behaviours = new ArrayList<>();
        currentTile = tile;
        tile.addVirologist(this);
    }

    //region Getters Setters

    /**
     * Returns the Virologist's SubstanceContainer.
     *
     * @return The Virologist's SubstanceContainer
     */
    public SubstanceContainer getSubstanceContainer() {
        return substanceContainer;
    }

    /**
     * Returns the Tile on which the Virologist currently stands.
     *
     * @return The Tile on which the Virologist currently stands
     */
    public Tile getCurrentTile() {
        return currentTile;
    }

    /**
     * Returns the list of GeneticCodes currently known by the Virologist.
     *
     * @return The list of GeneticCodes currently known by the Virologist
     */
    public List<GeneticCode> getGeneticCodes() {
        return geneticCodes;
    }

    /**
     * Returns the list of Equipments currently held by the Virologist.
     *
     * @return The list of Equipments currently held by the Virologist
     */
    public List<Equipment> getEquipments() {
        return equipments;
    }

    /**
     * Returns the list of Agents currently owned by the Virologist.
     *
     * @return The list of Agents currently owned by the Virologist
     */
    public List<Agent> getAgents() {
        return agents;
    }

    /**
     * Returns the maximum number of Equipments the Virologist can concurrently hold.
     *
     * @return The number of Equipments the Virologist can concurrently hold
     */
    public int getMaxEq() {
        return  maxEq;
    }

    /**
     * Sets the number of Equipments the Virologist can concurrently hold.
     *
     * @param maxEq The number of Equipments the Virologist can concurrently hold
     */
    public void setMaxEq(int maxEq) {
        if (maxEq > 0)
            this.maxEq = maxEq;
        notifyObservers(EventType.Any);
    }

    //endregion

    //region Controls

    /**
     * Interacts with the Tile that the Virologist currently stands on.
     */
    @Override
    public void tileInteract() {
        currentTile.interact(this);
        notifyObservers(EventType.Any);
        RoundManager.getInstance().completeAction();
    }

    /**
     * Uses the "a" Agent on the "v" Virologist. The Virologist passed as a parameter might be the same one
     * as the one using the Agent.
     *
     * @param v The virologist whom the agent is used on
     * @param a The agent that will be used on the given virologist.
     */
    @Override
    public void useAgent(Virologist v, Agent a) {
        if (!currentTile.getVirologists().contains(v) || !agents.contains(a))
            throw new RuntimeException("Selected virologist isn't a neighbour or the passed agent isn't held by the Virologist.");
        if (v.addAgent(this, a)) agents.remove(a);
        notifyObservers(EventType.Any);
        RoundManager.getInstance().completeAction();
    }

    /**
     * Moves the Virologist to the "t" Tile, unless one of its behaviours prevents it happening.
     *
     * @param t The tile where the virologist will be moved to.
     */
    @Override
    public void move(Tile t) {
        if (t == null) throw new IllegalArgumentException("Method paramenter cannot be null");
        if (executeBehaviours(Signal.Passed, (b) -> b.move(this, t)) == Signal.Passed && currentTile.getNeighbours().contains(t)) {
                currentTile.removeVirologist(this);
                currentTile = t;
                currentTile.addVirologist(this);
                notifyObservers(EventType.Any);
                RoundManager.getInstance().completeMove();
            
        }
    }

    /**
     * Steals substance or one equipment from another virologist, unless it's prevented.
     *
     * @param v The virologist who will get robbed.
     * @param isEquipment Determines whether the stolen object is an equipment (true) or substance (false).
     */
    @Override
    public void steal(Virologist v, boolean isEquipment) {
        if (currentTile.getVirologists().contains(v) || v == this) throw new IllegalArgumentException("The passed Virologist isn't a neighbour.");
        if (v.getRobbed(this, isEquipment)) {
            notifyObservers(EventType.Any);
            RoundManager.getInstance().completeAction();
        }
    }

    /**
     * Makes a new Agent based on a GeneticCode it already knows.
     * If the Virologist doesn't have enough substances, the process fails.
     * The Agent can either be a Virus or a Vaccine, it is determined by the isVirus
     * method parameter.
     *
     * @param isVirus Decides whether the new agent is a virus (true) or a vaccine (false).
     * @param gc The genetic code that the agent is created from.
     */
    @Override
    public void makeAgent(boolean isVirus, GeneticCode gc) {
        if (!geneticCodes.contains(gc)) throw new IllegalArgumentException("The Virologist doesn't know this GeneticCode.");

        Agent a = isVirus ? gc.makeVirus(substanceContainer) : gc.makeVaccine(substanceContainer);
        if (a != null) {
            agents.add(a);
            notifyObservers(EventType.Any);
            RoundManager.getInstance().completeAction();
        }
    }

    /**
     * Removes one of its current equipments.
     *
     * @param eq The equipment that will be removed.
     */
    @Override
    public void removeEquipment(Equipment eq) {
        if (!equipments.contains(eq)) throw new IllegalArgumentException("The virologist doesn't have this Equipment.");

        eq.setOwner(null);
        equipments.remove(eq);
        notifyObservers(EventType.Any);
        RoundManager.getInstance().completeAction();
    }

    /**
     * The virologist uses the equipment on another virologist.
     *
     * @param eq The equipment to use.
     * @param on The virologist the equipment will be used on.
     */
    @Override
    public void useEquipment(Equipment eq, Virologist on) {
        if (!equipments.contains(eq) || !currentTile.getVirologists().contains(on)) throw new IllegalArgumentException("The Virologist doesn't own this Equipment.");
        if (executeBehaviours(Signal.Failed, b -> b.useEquipment(eq, on)) == Signal.Passed) RoundManager.getInstance().completeAction();
    }

    //endregion

    //region Other Interactions

    /**
     * Calls the RoundInit() method on all the Virologist's behaviours.
     *
     * It allows them to initiate actions before the player controller.
     */
    public void roundInit() {
        executeBehaviours(Signal.Passed, b -> b.roundInit(this));
    }

    /**
     * The virologist collects a genetic code and stores it if it is not already stored.
     *
     * @param gc The genetic code to collect.
     */
    public void collectGeneticCode(GeneticCode gc) {
        if (!geneticCodes.contains(gc)) {
            geneticCodes.add(gc);
            notifyObservers(EventType.Any);
            LevelManager.getInstance().checkGeneticCodes(geneticCodes);
        }
    }

    /**
     * The virologist is getting robbed by another virologist if possible.
     *
     * @param v The virologist who is attempting to steal.
     * @param isEquipment Decides whether virologist "{@code v}" is stealing equipment (true) or substance (false)
     *                    from this virologist.
     * @return Whether the stealing was successful (true) or not (false).
     */
    public boolean getRobbed(Virologist v, boolean isEquipment){
        if (executeBehaviours(Signal.Failed, b -> b.getRobbed(this, v, isEquipment)) == Signal.Passed) {
            Random rnd = new Random();

            if (isEquipment) {
                Equipment rndEq = this.equipments.get(rnd.nextInt(equipments.size()));
                if (v.addEquipment(rndEq)) {
                    equipments.remove(rndEq);
                    notifyObservers(EventType.Any);
                    return true;
                }
            } else {
                int a = substanceContainer.getAminoCount(), n = substanceContainer.getNucleotidCount();
                if (a  > 0 || n  > 0) {
                    SubstanceContainer stolenSc = new SubstanceContainer(rnd.nextInt(a), rnd.nextInt(n));
                    substanceContainer.use(stolenSc);
                    notifyObservers(EventType.Any);
                    return v.gainSubstance(stolenSc);
                }
            }
        }
        return false;
    }

    /**
     * The virologist collects a given equipment and stores it.
     *
     * @param eq The equipment to be collected.
     */
    public boolean addEquipment(Equipment eq) {
        if (eq == null) throw new IllegalArgumentException("Passed Equipment is null.");
        if (equipments.size() < maxEq) {
            eq.setOwner(this);
            equipments.add(eq);
            notifyObservers(EventType.Any);
            return true;
        }
        return false;
    }

    /**
     * A given agent is applied to a given virologist.
     *
     * @param v The virologist whom the agent will be applied on.
     * @param a The agent that is being applied.
     * @return Whether the process was successful (true) or not (false).
     */
    public boolean addAgent(Virologist v, Agent a){
        if (v == null || a == null) throw new IllegalArgumentException("Parameters cannot be null");

        if (executeBehaviours(Signal.Passed, b -> b.addAgent(this, v, a)) == Signal.Passed) {
            a.applyEffect(this);
            return true;
        }
        return false;
    }

    /**
     * The virologist collects a given amount of substance and adds it to their storage.
     *
     * @param sc The amount of substance to be collected.
     */
    public boolean gainSubstance(SubstanceContainer sc) {
        notifyObservers(EventType.Any);
        return substanceContainer.gain(sc);
    }

    /**
     * Adds a given behaviour to the virologist's collection.
     * @param b The behaviour that is added.
     */
    public void addBehaviour(Behaviour b){
        if (b == null) throw new IllegalArgumentException("The passed Behaviour cannot be null.");
        behaviours.add(b);
    }



    /**
     * Removes a given behaviour from the virologist's collection.
     * @param b The behaviour that is removed.
     */
    public void removeBehaviour(Behaviour b) {
        behaviours.remove(b);
    }

    //endregion

    //region Private Methods

    private Signal executeBehaviours(final Signal sDefault, Function<Behaviour, Signal> operation) {
        Signal result = sDefault;
        for (Behaviour b : behaviours) {
            Signal sTemp = operation.apply(b);
            if (sTemp == Signal.Interrupted) return sTemp;
            if (sTemp != sDefault) result = sTemp;
        }
        return result;
    }

    //endregion

}