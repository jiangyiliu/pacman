package edu.rice.comp504.model.item;

import edu.rice.comp504.model.DispatcherAdapter;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
@Getter
@Setter
public abstract class Fruit extends AItem {
    private int remainTime;
    private int fruitType;
    private boolean isActive;

    /**
     * Constructor.
     *
     * @param loc   The location of the Fruit on the canvas
     */
    public Fruit(Point loc, int score, int size, int fruitType, int remainTime) {
        super("fruit", loc,"black", score);
        this.remainTime = remainTime;
        this.fruitType = fruitType;
        this.isActive = false;
    }

    /**
     * Decrease the fruit active time.
     * @return If the time run out.
     */
    public boolean decreaseRemainTime() {
        return true;
    }
}
