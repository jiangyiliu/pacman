package edu.rice.comp504.model.character;


import edu.rice.comp504.model.cmd.IPacmanCmd;
import edu.rice.comp504.model.strategy.collision.ICollideStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.beans.PropertyChangeEvent;

/**
 * A ghost in the game.
 */
@Getter
@Setter
public  class Ghost extends ARole {
    private boolean isFlashing;
    private boolean isDead;
    private int flashingTimer;
    private int score;
    private Point overlapItem;
    private int prevDir;
    private Point startLoc;
    private boolean wasFlashing;

    /**
     * Constructor.
     *
     * @param loc             The location of the ARole on the canvas
     * @param vel             The ARole velocity
     * @param color           The ARole color
     * @param updateStrategy  The object updateStrategy
     * @param collideStrategy The object collideStrategy
     */
    public Ghost(Point loc, Point vel, String color, IUpdateStrategy updateStrategy, ICollideStrategy collideStrategy,
                 int size, boolean isFlashing, boolean isDead, int flashingTimer, Point overlapItem) {
        super("ghost", loc, vel, color, updateStrategy, collideStrategy, 1, size);
        this.isFlashing = isFlashing;
        this.isDead = isDead;
        this.flashingTimer = flashingTimer;
        this.overlapItem = overlapItem;
        this.score = 200;
        this.prevDir = this.direction;
        this.startLoc = loc;
    }

    /**
     * Update state of the Ghost when the property change event occurs by executing the command.
     */
    @Override
    public void propertyChange(PropertyChangeEvent e) {}
}
