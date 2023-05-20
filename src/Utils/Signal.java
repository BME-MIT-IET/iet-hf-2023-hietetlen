package Utils;

/**
 * This enum contains the potential states which
 * a Virologists active Behaviours can return with.
 * Passed means that execution of the called action can
 * proceed, Failed means that the default implementation of
 * the invoked action can't be executed, but the other Behaviours
 * can still be called, Interrupted means that not even the yet
 * to be called Behaviours can be executed and the action must
 * be aborted.
 */
public enum Signal {
    Passed, Failed, Interrupted
}
