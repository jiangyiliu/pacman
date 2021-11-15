package edu.rice.comp504.model.item;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
@Getter
@Setter
public class Dot extends AItem {
    private int radius;


    /**
     * Constructor.
     *
     * @param loc   The location of the Dot on the canvas
     * @param color The Dot color
     */
    public Dot(Point loc, String color, int score, int radius) {
        super("dot", loc, color, score);
        this.radius = radius;
    }
}
