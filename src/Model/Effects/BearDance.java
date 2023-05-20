package Model.Effects;

import Model.EffectHolders.Agent;
import Model.GeneticCode;
import Model.SubstanceContainer;
import Control.RoundManager;
import Utils.Signal;
import Model.Virologist;

import java.util.ArrayList;
import java.util.List;

/**
 * The BearDance is a form of VitusDance which
 * makes the effected Virologist infect any
 * neighbouring Virologists.
 */
public class BearDance extends VitusDance {
    private final GeneticCode gc;
    private List<Virologist> infected = new ArrayList<>();

    /**
     * The constructor creates the GeneticCode used for infecting other Virologists.
     */
    public BearDance (GeneticCode gc) {
        this.gc = gc;
    }

    /**
     * Moves the Virologist to a random neighbouring tile.
     *
     * @param v The virologist whose round just started.
     * @return The roundInit must be interrupted
     */
    @Override
    public Signal roundInit(Virologist v) {
        //Move to next Tile
        super.roundInit(v);
        nextTile.destroy();
        //Infect Neighbours
        Agent infection = gc.makeVirus(new SubstanceContainer(20, 20));
        for (Virologist virologist : v.getCurrentTile().getVirologists()) {
            if (virologist != v && !infected.contains(virologist)) {
                virologist.addAgent(virologist, infection);
                infected.add(virologist);
            }
        }
        RoundManager.getInstance().completeAction();
        return Signal.Passed;
    }

    @Override
    public Signal addAgent(Virologist to, Virologist from, Agent a) {
        return (a.getGeneticCode() == gc) ? Signal.Failed : super.addAgent(to, from, a);
    }

    /**
     * The BearVirus cannot be cured.
     *
     * @param v The virologist whom the effect is removed from.
     */
    @Override
    public void remove(Virologist v) {
    }

}
