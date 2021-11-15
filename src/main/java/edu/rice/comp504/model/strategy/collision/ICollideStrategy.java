package edu.rice.comp504.model.strategy.collision;

import edu.rice.comp504.model.APaintObj;
import edu.rice.comp504.model.DispatcherAdapter;
import edu.rice.comp504.model.GameContext;
import edu.rice.comp504.model.character.ARole;

import java.beans.PropertyChangeSupport;

/**
 * An interface for ARole strategies that determine the ARole collision behavior with others.
 */
public interface ICollideStrategy {
    /**
     * The name of the strategy.
     * @return strategy name
     */
    String getName();

    /**
     * Update the state of the ARole.
     * @param other The collide object.
     */
    void collideState(APaintObj other);
}
