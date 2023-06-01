package implementation.testingutils;

import Model.Effect;
import Model.Effects.MemoryLoss;
import Model.GeneticCode;
import Model.SubstanceContainer;
import Model.Tiles.Tile;
import Model.Virologist;

import java.util.function.Supplier;

public class CreationUtils {

    public static GeneticCode CreateGeneticCode(Supplier<Effect> effectSupplier) {
        return new GeneticCode(effectSupplier, new SubstanceContainer(0,0), 10);
    }

    public static Virologist CreateVirologistWithVirus(Tile tile, Supplier<Effect> effectSupplier, boolean isVirus) {
        if (tile == null) tile = new Tile();
        if (effectSupplier == null) effectSupplier = () -> new MemoryLoss();
        var virologist = new Virologist(tile);
        var geneticCode = new GeneticCode(effectSupplier, new SubstanceContainer(0,0), 10);
        virologist.collectGeneticCode(geneticCode);
        virologist.makeAgent(isVirus, geneticCode);
        return virologist;
    }
}
