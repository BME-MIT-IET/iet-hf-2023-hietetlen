package Control;

import Model.EffectHolders.Agent;
import Model.EffectHolders.Equipment;
import Model.GeneticCode;
import Model.InteractionSet;
import Model.SubstanceContainer;
import Utils.DefaultSubject;
import Utils.EventType;
import Model.Virologist;
import Model.Tiles.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * The RoundManager singleton class is responsible for handling the gameplay.
 * It keeps count of the active player, handles method calls to them,
 * starts and finishes the rounds. The active Virologist can be controlled
 * through calling the corresponding methods of the RoundManager.
 */
public class RoundManager extends DefaultSubject<RoundManager> implements InteractionSet {

    private List<Virologist> virologists = new ArrayList<>();
    private final List<Runnable> runnables = new ArrayList<>();

    private int currentVirologistIndex = 0;
    private boolean moveCompleted = false;
    private boolean actionCompleted = false;
    private boolean gameStarted = false;

    private static RoundManager instance = new RoundManager();
    private RoundManager() { }

    /**
     * Returns the singleton instance of the class.
     *
     * @return The singleton instance
     */
    public static RoundManager getInstance() {
        return instance;
    }

    /**
     * Returns whether the game has already started.
     *
     * @return Whether the game has already started
     */
    public boolean started() {
        return gameStarted;
    }

    /**
     * This method is called by the active Virologist when
     * it finishes moving. This prevents the
     * Player from moving the virologist more than once a round.
     */
    public void completeMove() {
        moveCompleted = true;
        notifyObservers(EventType.MoveCompleted);
    }

    /**
     * This method is called by the active Virologist when
     * it finishes taking an action. This prevents the
     * player from making the virologist take an action more than once a round.
     */
    public void completeAction() {
        actionCompleted = true;
        notifyObservers(EventType.ActionCompleted);
    }

    /**
     * This method should be called by the player when they wish to
     * finish the current round.
     */
    public void roundCompleted() {
        currentVirologistIndex++;
        if (currentVirologistIndex == virologists.size()) currentVirologistIndex = 0;
        notifyObservers(EventType.RoundInit);
        moveCompleted = false;
        actionCompleted = false;
        virologists.get(currentVirologistIndex).roundInit();
        for (Runnable r : runnables) {
            r.run();
        }
    }

    /**
     * Return the currently active Virologist.
     *
     * @return The currently active Virologist
     */
    public Virologist getCurrentVirologist() {
        return virologists.get(currentVirologistIndex);
    }

    public List<Virologist> getVirologists() {
        return virologists;
    }

    /**
     * Subscribes the "r" Runnable to the desired event.
     *
     * @param r The Runnable which will be invoked when the event happens.
     * @param event The event upon which the Runnable should be invoked.
     */
    public void subscribe(Runnable r, EventType event) {
        runnables.add(r);
    }

    /**
     * Unsubscribes the "r" Runnable from the desired event.
     *
     * @param r The Runnable which is being invoked when the event happens.
     * @param event The event upon which the Runnable is being invoked.
     */
    public void unsubscribe(Runnable r, EventType event) {
        runnables.remove(r);
    }

    /**
     * Starts the game.
     *
     * @param vs The currently active players
     */
    public void start(List<Virologist> vs) {
        currentVirologistIndex = 0;
        virologists = vs;
        gameStarted = true;
        actionCompleted = false;
        moveCompleted = false;
        virologists.get(currentVirologistIndex).roundInit();
    }

    /**
     * Calls the active Virologist's tileInteract method, if
     * it hasn't performed any actions in the current round before.
     */
    @Override
    public void tileInteract() {
        if (!actionCompleted) {
            virologists.get(currentVirologistIndex).tileInteract();
            return;
        }
    }

    /**
     * Calls the active Virologist's useAgent method, if
     * it hasn't performed any actions in the current round before.
     */
    @Override
    public void useAgent(Virologist v, Agent a) {
        if (!actionCompleted) {
            virologists.get(currentVirologistIndex).useAgent(v, a);
            return;
        }
    }

    /**
     * Calls the active Virologist's move method, if
     * it hasn't moved in the current round before.
     */
    @Override
    public void move(Tile t) {
        if (!moveCompleted) {
            virologists.get(currentVirologistIndex).move(t);
            return;
        }
    }

    /**
     * Calls the active Virologist's steal method, if
     * it hasn't performed any actions in the current round before.
     */
    @Override
    public void steal(Virologist v, boolean isEquipment) {
        if (!actionCompleted) {
            virologists.get(currentVirologistIndex).steal(v, isEquipment);
            return;
        }
    }

    /**
     * Calls the active Virologist's makeAgent method, if
     * it hasn't performed any actions in the current round before.
     */
    @Override
    public void makeAgent(boolean isVirus, GeneticCode gc) {
        if (!actionCompleted) {
            virologists.get(currentVirologistIndex).makeAgent(isVirus, gc);
            return;
        }
    }

    /**
     * Calls the active Virologist's useEquipment method, if
     * it hasn't performed any actions in the current round before.
     */
    @Override
    public void useEquipment(Equipment eq, Virologist to) {
        if (!actionCompleted) {
            virologists.get(currentVirologistIndex).useEquipment(eq, to);
            return;
        }
    }

    /**
     * Calls the active Virologist's removeEquipment method, if
     * it hasn't performed any actions in the current round before.
     */
    @Override
    public void removeEquipment(Equipment eq) {
        if (!actionCompleted) {
            virologists.get(currentVirologistIndex).removeEquipment(eq);
            return;
        }
    }

    /**
     * Finishes the game and prints out the winner to the console.
     */
    public void finishGame() {
        //TODO
        System.out.println("The number " + currentVirologistIndex + " won the game!");
        gameStarted = false;
        notifyObservers(EventType.GameWon);
    }

    //From down here, the program handles the console commands

    /**
     * Handles the command line commands which control the game.
     *
     * @param line The input command from the user
     */
}
