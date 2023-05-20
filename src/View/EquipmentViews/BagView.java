package View.EquipmentViews;

import Model.EffectHolders.Equipment;
import View.EquipmentView;

public class BagView extends EquipmentView {

    public BagView(Equipment equipment) {
        super(equipment);
        remove(useButton);
    }

    @Override
    protected String getEquipmentName() {
        return "Bag";
    }

    @Override
    protected void use() {

    }
}
