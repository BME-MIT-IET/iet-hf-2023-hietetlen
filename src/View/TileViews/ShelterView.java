package View.TileViews;

import Model.Tiles.Tile;
import View.TileView;

public class ShelterView extends TileView {

    public ShelterView(Tile tile) {
        super(tile);
    }

    @Override
    protected String getTileName () {
        return "Shelter";
    }

    @Override
    protected String getTileImage () {
        return "shelter.png";
    }
}
