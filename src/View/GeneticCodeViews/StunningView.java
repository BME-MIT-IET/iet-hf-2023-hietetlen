package View.GeneticCodeViews;

import Model.GeneticCode;
import View.GeneticCodeView;

public class StunningView extends GeneticCodeView {

    public StunningView(GeneticCode gc) {
        super(gc);
    }

    @Override
    protected String getGCName() {
        return "Stunning";
    }
}
