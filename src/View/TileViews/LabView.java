package View.TileViews;

import Model.Tiles.Tile;
import View.TileView;

public class LabView extends TileView {

    public LabView(Tile tile) {
        super(tile);
    }

    @Override
    protected String getTileName () {
        return "Laboratory";
    }

    @Override
    protected String getTileImage () {
        return "lab.png";
    }
}
