package edu.rice.comp504.model.character;

import edu.rice.comp504.model.DispatcherAdapter;
import edu.rice.comp504.model.cmd.IPacmanCmd;
import edu.rice.comp504.model.item.AItem;
import edu.rice.comp504.model.strategy.collision.ICollideStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.wall.Wall;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.util.TreeMap;

/**
 * The pacman in the game.
 */
@Getter
@Setter
public class Pacman extends ARole {
    private int leftLives;
    private int nextDirection;
    private boolean isDead;
    private int resetTime;
    /**
     * Constructor.
     *
     * @param loc             The location of the ARole on the canvas
     * @param vel             The ARole velocity
     * @param updateStrategy  The object updateStrategy
     * @param collideStrategy The object collideStrategy
     */
    public Pacman(Point loc, Point vel, IUpdateStrategy updateStrategy,
                  ICollideStrategy collideStrategy, int size, int direction, int leftLives) {
        super("pacman", loc, vel, null, updateStrategy, collideStrategy, direction, size);
        this.leftLives = leftLives;
        this.nextDirection = 0;
    }

    /**
     * Detects collision between Pacman and a ghost in the world.
     */
    public boolean detectCollisionWithGhost(Ghost ghost) { return true; }

    /**
     * Detects collision between Pacman and a item in the world.
     */
    public boolean detectCollisionWithItem(AItem item) { return true; }

    /**
     * Reset the pacman.
     */
    public void reset() { }

    /**
     * Update state of the Pacman when the property change event occurs by executing the command.
     */
    @Override
    public void propertyChange(PropertyChangeEvent e) {

    }
}
