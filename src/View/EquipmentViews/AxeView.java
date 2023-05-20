package View.EquipmentViews;

import Control.LevelManager;
import Control.RoundManager;
import Model.EffectHolders.Equipment;
import View.EquipmentView;
import View.TileView;

public class AxeView extends EquipmentView {

    public AxeView(Equipment equipment) {
        super(equipment);
    }

    @Override
    protected String getEquipmentName() {
        return "Axe";
    }

    @Override
    protected void use() {
        TileView tv = LevelManager.getInstance().getTileView(RoundManager.getInstance().getCurrentVirologist().getCurrentTile());
        RoundManager.getInstance().useEquipment(equipment, tv.getSelectedVirologist());
    }
}
