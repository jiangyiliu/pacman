package edu.rice.comp504.model.item;

import lombok.Getter;
import lombok.Setter;

import java.awt.*;
@Getter
@Setter
public class LargeDot extends AItem {
    private int radius;


    /**
     * Constructor.
     *
     * @param loc   The location of the Dot on the canvas
     * @param color The Dot color
     */
    public LargeDot(Point loc, String color, int score, int radius) {
        super("LargeDot", loc, color, score);
        this.radius = radius;
    }
}
