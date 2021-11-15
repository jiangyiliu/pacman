package edu.rice.comp504.model.item;

import edu.rice.comp504.model.APaintObj;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
@Getter
@Setter
public class AItem extends APaintObj implements PropertyChangeListener {
    private String color;
    private int score;
    private transient boolean isEaten;

    /**
     * Constructor.
     * @param name The name of the AItem
     * @param loc  The location of the AItem on the canvas
     * @param color The AItem color
     */
    public AItem(String name, Point loc, String color, int score) {
        super(name, loc);
        this.color = color;
        this.score = score;
        this.isEaten = false;
    }

    /**
     * Item respond to property change event.
     * @param evt changed event.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
