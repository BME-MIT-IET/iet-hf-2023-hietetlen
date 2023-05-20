package View.GeneticCodeViews;

import Model.GeneticCode;
import View.GeneticCodeView;

public class VitusDanceView extends GeneticCodeView {

    public VitusDanceView(GeneticCode gc) {
        super(gc);
    }

    @Override
    protected String getGCName() {
        return "Vitus dance";
    }
}
