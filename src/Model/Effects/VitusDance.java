package Model.Effects;

import Control.*;
import Model.Behaviour;
import Model.Effect;
import Model.EffectHolder;
import Model.Tiles.Tile;
import Utils.Signal;
import Model.Virologist;

import java.util.List;
import java.util.Random;

/**
 * The VitusDance prevents the effected Virologist from moving
 * on their own. Instead, it chooses the next Tile to step on
 * randomly.
 */
public class VitusDance extends Behaviour implements Effect {

    protected final Random rnd = new Random();
    protected Tile nextTile;

    /**
     * Moves the Virologist to a random neighbouring tile.
     *
     * @param v The virologist whose round just started.
     * @return The roundInit can continue
     */
    public Signal roundInit(Virologist v) {
        List<Tile> neighbours = v.getCurrentTile().getNeighbours();
        nextTile = neighbours.get(rnd.nextInt(neighbours.size()));
        if (v.getCurrentTile() != nextTile) {
            v.move(nextTile);
        }
        RoundManager.getInstance().completeMove();
        return Signal.Passed;
    }

    /**
     * If the "t" Tile doesn't match the one, it selected in RoundInit, it prevents
     * the virologist from moving.
     *
     * @param v The virologist who tries to move.
     * @param t The tile that the virologist wants to step on.
     * @return Whether the Virologist can move to the next Tile
     */
    public Signal move(Virologist v, Tile t) {
        return (nextTile == t || nextTile == null) ? Signal.Passed : Signal.Failed;
    }

    /**
     * Applies itself to the "v" Virologist, by adding itself to the Virologists
     * Behaviour list.
     *
     * @param v The virologist whom the effect is applied to.
     */
    public void apply(EffectHolder effectHolder, Virologist v) {
        v.addBehaviour(this);
    }

    /**
     * Removes its effect form the "v" Virologist, by removing itself from the Virologists
     * Behaviour list.
     *
     * @param v The virologist whom the effect is removed from.
     */
    public void remove(Virologist v) {
        v.removeBehaviour(this);
    }

}
