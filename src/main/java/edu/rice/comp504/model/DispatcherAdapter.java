package edu.rice.comp504.model;

import edu.rice.comp504.model.character.Ghost;
import edu.rice.comp504.model.cmd.IPacmanCmd;
import edu.rice.comp504.model.item.*;
import edu.rice.comp504.model.wall.Wall;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.TreeMap;

/**
 * The dispatch adapter provides the interface between the controller and model.
 */
public class DispatcherAdapter {

    private GameContext context;
    private PropertyChangeSupport pcs;
    private TreeMap<Point,Wall> horizontalWall;
    private TreeMap<Point,Wall> verticalWall;
    public static final int updatePeriod = 60;
    private int timeElapsed = 0;
    private int endTime;

    /**
     * Constructor.
     */
    public DispatcherAdapter() {
        context = new GameContext(1, 4, 3, 0);
        pcs = new PropertyChangeSupport(this);

    }

    /**
     * Initialize the game.
     * @return PacmanContext record information about the game
     */
    public GameContext init() {
        horizontalWall.clear();
        verticalWall.clear();
        context.init();

        return context;
    }

    /**
     * Pacman eat a dot.
     * @param dot The eaten dot.
     */
    public void eatDot(Dot dot) {

    }

    /**
     * Pacman eat a big dot.
     * @param dot The eaten big dot.
     */
    public void eatBigDot(LargeDot dot) {

    }

    /**
     * Pacman eat a fruit.
     * @param fruit The eaten fruit.
     */
    public void eatFruit(Fruit fruit) {

    }

    /**
     * Pacman eat a ghost.
     * @param ghost The eaten ghost.
     */
    public void eatGhost(Ghost ghost) {

    }

    /**
     * Remove the items that are not in effect time.
     * @param item The out-of-time item.
     */
    public void removeTimeOutItem(AItem item) {

    }

    /**
     * Call the update method on all the character observers to update their position in the pacman world.
     */
    public GameContext updateCanvas(String keyCode) {

        return null;
    }

    /**
     * Extra action for characters.
     */
    private void charactersAction() {
    }

    /**
     * Reset the ghost strategy based on ghost's color.
     * @param ghost The ghost object.
     * @param switchCmd Switch Command.
     * @return The Switch Command.
     */
    private IPacmanCmd resetGhostStrategy(Ghost ghost, IPacmanCmd switchCmd) {

        return null;
    }

    /**
     * Remove all PropertyChangeListener.
     */
    public void removeAll() {
        for (PropertyChangeListener pcl : pcs.getPropertyChangeListeners()) {
            pcs.removePropertyChangeListener(pcl);
        }
    }

    /**
     * Set game settings.
     * @param gameLevel The game level.
     * @param numGhosts Number of ghosts.
     * @param numLives Number of lives.
     */
    public void setGameParameters(int gameLevel, int numGhosts, int numLives) {

    }

    /**
     * Get game context.
     * @return The game context.
     */
    public GameContext getContext() {
        return context;
    }
}
