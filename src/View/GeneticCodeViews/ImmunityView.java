package View.GeneticCodeViews;

import Model.EffectHolders.Agent;
import Model.GeneticCode;
import Utils.EventType;
import Utils.Subject;
import View.AgentView;
import View.GeneticCodeView;

public class ImmunityView extends GeneticCodeView {

    public ImmunityView(GeneticCode gc) {
        super(gc);
    }

    @Override
    protected String getGCName() {
        return "Immunity";
    }
}
