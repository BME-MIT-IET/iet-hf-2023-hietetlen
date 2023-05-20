package View.TileViews;

import Model.Tiles.Tile;
import View.TileView;

public class InfectedLabView extends TileView {

    public InfectedLabView(Tile tile) {
        super(tile);
    }

    @Override
    protected String getTileName () {
        return "Infected Laboratory";
    }

    @Override
    protected String getTileImage () {
        return "infected_lab.png";
    }
}
