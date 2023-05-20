package Model;

import Utils.Signal;
import Model.EffectHolders.Agent;
import Model.EffectHolders.Equipment;
import Model.Tiles.Tile;

/**
 * Overriding this class's methods, enables the alteration of
 * the Virologists behaviour.
 */
public abstract class Behaviour {
    /**
     *
     * @param to The virologist whom the agent will be used on.
     * @param from The virologist who uses the agent.
     * @param a The agent that will be used.
     * @return A signal enumerable, that shows if the method was successful (Passed), Interrupted or Failed.
     */
    public Signal addAgent(Virologist to, Virologist from, Agent a) {
        return Signal.Passed;
    }

    /**
     * Checks states and performs actions that are relevant at the start of a virologist's round
     * and cannot be controlled by them.
     * @param v The virologist whose round just started.
     * @return A signal, passed on default. See also {@link #addAgent(Virologist, Virologist, Agent)}
     */
    public Signal roundInit(Virologist v) {
        return Signal.Passed;
    }

    /**
     * Moves a virologist from their current tile to another one if possible.
     * @param v The virologist who tries to move.
     * @param t The tile that the virologist wants to step on.
     * @return A signal, Passed on default. See also {@link #addAgent(Virologist, Virologist, Agent)}
     */
    public Signal move(Virologist v, Tile t) {
        return Signal.Passed;
    }

    /**
     * A virologist is getting robbed by another virologist if possible.
     * @param who The virologist who is getting robbed.
     * @param by The virologist who is robbing virologist "{@code who}".
     * @param isEquipment Decides whether virologist "{@code by}" is stealing equipment (true) or substance (false)
     *                    from virologist "{@code who}".
     * @return A signal, Failed on default. See also {@link #addAgent(Virologist, Virologist, Agent)}
     */
    public Signal getRobbed(Virologist who, Virologist by, boolean isEquipment) {
        return Signal.Failed;
    }

    /**
     * A virologist uses an equipment if possible.
     * @param eq - not used
     * @param to - not used
     * @return Signal.Failed
     */
    public Signal useEquipment(Equipment eq, Virologist to) { return Signal.Failed; }
}
