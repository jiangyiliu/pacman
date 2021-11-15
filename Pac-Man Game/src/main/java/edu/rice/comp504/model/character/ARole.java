package edu.rice.comp504.model.character;

import edu.rice.comp504.model.APaintObj;
import edu.rice.comp504.model.DispatcherAdapter;
import edu.rice.comp504.model.GameContext;
import edu.rice.comp504.model.strategy.collision.ICollideStrategy;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.wall.Wall;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.beans.PropertyChangeListener;
import java.util.*;

/**
 * A ghost that the view can draw on the user's canvas.
 */
@Getter
@Setter
public abstract class ARole extends APaintObj implements PropertyChangeListener {
    public Point vel;
    public IUpdateStrategy updateStrategy;
    public ICollideStrategy collideStrategy;
    public String color;
    // 1 is left, 2 is up, 3 is right, and 4 is down
    public int direction;
    public int preDir;
    public final Point originalLoc;
    public int size;


    /**
     * Constructor.
     * @param name The name of the ARole
     * @param loc  The location of the ARole on the canvas
     * @param vel  The ARole velocity
     * @param color The ARole color
     * @param updateStrategy  The object updateStrategy
     * @param collideStrategy The object collideStrategy
     * @param direction The character direction
     */
    public ARole(String name, Point loc, Point vel, String color, IUpdateStrategy updateStrategy,
                      ICollideStrategy collideStrategy, int direction, int size) {
        super(name, loc);
        this.vel = vel;
        this.updateStrategy = updateStrategy;
        this.collideStrategy = collideStrategy;
        this.color = color;
        this.direction = direction;
        this.originalLoc = new Point(loc.x, loc.y);
        this.size = size;
    }

    /**
     * Detects collision between a ARole and a wall in the ARole world.  Change direction if ARole collides with a wall.
     * @return if it collide with a wall within a step.
     */
    public boolean detectCollisionWithWalls(int direction, TreeMap<Point,Wall> horizontal, TreeMap<Point,Wall> vertical) {
        return true;
    }

}

