package Model.Effects;

import Model.EffectHolder;
import Control.LevelManager;
import Model.Virologist;
import Model.Effect;
import Model.GeneticCode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * MemoryLoss temporarily removes all already learned GeneticCodes
 * from the effected Virologist, then reapplies them.
 */
public class MemoryLoss implements Effect {

    private final List<GeneticCode> geneticCodes = new ArrayList<>();

    /**
     * Applies the effect on the "v" Virologist.
     * @param effectHolder - not used
     * @param v The virologist whom the effect is applied to.
     */
    public void apply(EffectHolder effectHolder, Virologist v) {
        List<GeneticCode> currentGCs = v.getGeneticCodes();
        geneticCodes.addAll(currentGCs);
        currentGCs.clear();
    }

    /**
     * Removes the effect of the "v" Virologist.
     * @param v The virologist whom the effect is removed from.
     */
    public void remove(Virologist v) {
        List<GeneticCode> currentGCs = v.getGeneticCodes();
        currentGCs.addAll(geneticCodes.stream().filter(x -> !currentGCs.contains(x)).collect(Collectors.toList()));
        geneticCodes.clear();
        LevelManager.getInstance().checkGeneticCodes(v.getGeneticCodes());
    }
}
