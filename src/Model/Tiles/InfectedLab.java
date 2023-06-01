package Model.Tiles;

import Model.EffectHolders.Agent;
import Model.Effects.BearDance;
import Model.GeneticCode;
import Model.SubstanceContainer;
import Model.Virologist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The InfectedLab is an altered version of the Lab,
 * which has a chance of infecting any Virologists stepping on it
 * with the BearVirus.
 */
public class InfectedLab extends Lab {
    float infectionLevel;
    private GeneticCode bearDance;
    Agent infection;
    Random rnd;
    List<Virologist> infected = new ArrayList<>();

    /**
     * Constructor of the InfectedLab Tile
     *
     * @param geneticCode The GeneticCode taught by the InfectedLab Tile
     * @param lvl The chance of getting infected when entering the Tile
     */
    public InfectedLab(GeneticCode geneticCode, float lvl) {
        super(geneticCode);
        rnd = new Random();
        infectionLevel = lvl;
        if (bearDance == null) bearDance = new GeneticCode(() -> new BearDance(bearDance), new SubstanceContainer(20, 20), 2);
        infection = bearDance.makeVirus(new SubstanceContainer(20, 20));
    }

    /**
     * Randomly infects the Virologist with BearVirus.
     *
     * @param v The virologist being added.
     */
    @Override
    public void addVirologist(Virologist v) {
        super.addVirologist(v);
        if (rnd.nextFloat() < infectionLevel && !infected.contains(v)) {
            v.addAgent(v, infection);
            infected.add(v);
        }
    }
}
