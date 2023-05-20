package View.EquipmentViews;

import Model.EffectHolders.Equipment;
import View.EquipmentView;

public class CapeView extends EquipmentView {

    public CapeView(Equipment equipment) {
        super(equipment);
        remove(useButton);
    }

    @Override
    protected String getEquipmentName() {
        return "Cape";
    }

    @Override
    protected void use() {

    }
}
