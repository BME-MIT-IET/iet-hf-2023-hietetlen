package Model;

/**
 * The Effect interface provides a way for altering the
 * behaviour of the Virologists.
 */
public interface Effect {
    /**
     * Applies the effect on the given virologist.
     *
     * @param v The virologist whom the effect is applied to.
     */
    void apply(EffectHolder effectHolder, Virologist v);

    /**
     * Removes the effect from the given virologist.
     *
     * @param v The virologist whom the effect is removed from.
     */
    void remove(Virologist v);
}