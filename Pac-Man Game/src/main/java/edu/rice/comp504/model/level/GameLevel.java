package edu.rice.comp504.model.level;

import edu.rice.comp504.model.item.*;
import edu.rice.comp504.model.wall.Wall;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.HashMap;
import java.util.List;

/**
 * The game level object that contains level information.
 */
@Getter
@Setter
public class GameLevel {
    private int levelCount;
    private List<Wall> walls;
    private List<Fruit> fruits;
    private HashMap<Point, Boolean> fruitLocations;
    private List<Dot> dots;
    private List<LargeDot> largeDots;

    /**
     * Instatiates ALevel abstract class.
     * @param walls wall list.
     * @param fruits fruit list.
     * @param dots dot list.
     * @param largeDots big dot list.
     */
    public GameLevel(int levelCount, List<Wall> walls, List<Fruit> fruits, List<Dot> dots, List<LargeDot> largeDots,
                     HashMap<Point, Boolean> fruitLocations) {
        this.levelCount = levelCount;
        this.walls = walls;
        this.fruits = fruits;
        this.dots = dots;
        this.largeDots = largeDots;
        this.fruitLocations = fruitLocations;
    }

    /**
     * Remove a fruit.
     * @param fruit The fruit object.
     */
    public void removeFruit(Fruit fruit) {
        this.fruits.remove(fruit);
    }

    /**
     * Remove a dots.
     */
    public void removeDot(Dot dot) {
        this.dots.remove(dot);
    }

    /**
     * Remove a big dots.
     */
    public void removeBigDot(LargeDot largeDot) {
        this.largeDots.remove(largeDot);
    }

}
