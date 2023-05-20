package Model;

/**
 * EffectHolders are the classes which serve as
 * containers for the different kinds of effects.
 * By implementing this interface, the container classes
 * enable the Effects to deactivate themselves when necessary.
 */
public interface EffectHolder {
    /**
     * When the contained Effect calls this method on it's holder,
     * it should disable the effect.
     */
    void deactivate();
}
