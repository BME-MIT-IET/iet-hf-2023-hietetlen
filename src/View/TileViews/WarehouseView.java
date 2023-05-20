package View.TileViews;

import Model.Tiles.Tile;
import View.TileView;

public class WarehouseView extends TileView {

    public WarehouseView(Tile tile) {
        super(tile);
    }

    @Override
    protected String getTileName () {
        return "Warehouse";
    }

    @Override
    protected String getTileImage () {
        return "warehouse.png";
    }
}
