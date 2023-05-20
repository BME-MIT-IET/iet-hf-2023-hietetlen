package View.GeneticCodeViews;

import Model.GeneticCode;
import View.GeneticCodeView;

public class MemoryLossView extends GeneticCodeView {

    public MemoryLossView(GeneticCode gc) {
        super(gc);
    }

    @Override
    protected String getGCName() {
        return "Memory loss";
    }
}
