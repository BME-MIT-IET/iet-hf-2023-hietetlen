package View.EquipmentViews;

import Model.EffectHolders.Equipment;
import View.EquipmentView;

public class GloveView extends EquipmentView {

    public GloveView(Equipment equipment) {
        super(equipment);
        remove(useButton);
    }

    @Override
    protected String getEquipmentName() {
        return "Glove";
    }

    @Override
    protected void use() {

    }
}
