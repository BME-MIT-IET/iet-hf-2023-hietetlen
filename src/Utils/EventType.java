package Utils;

/**
 * The types of events to which objects in need of scheduling
 * can subscribe to.
 */
public enum EventType {
    RoundInit,
    MoveCompleted,
    ActionCompleted,
    GameWon,
    Any,
}
