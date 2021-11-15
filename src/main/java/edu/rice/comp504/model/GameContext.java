package edu.rice.comp504.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.rice.comp504.model.character.Ghost;
import edu.rice.comp504.model.character.Pacman;
import edu.rice.comp504.model.item.*;
import edu.rice.comp504.model.level.GameLevel;
import edu.rice.comp504.model.strategy.IUpdateStrategy;
import edu.rice.comp504.model.strategy.collision.ICollideStrategy;
import edu.rice.comp504.model.wall.Wall;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

/**
 * GameContext is the context of the pacman game. It stores all the game elements and game status.
 */
@Getter
@Setter
public class GameContext {
    private GameLevel gameLevel;
    private Pacman pacman;
    private List<Ghost> ghosts;

    // 1 for start, 0 for pause
    private int status;
    private int maxLives;
    private int currentScore;
    private int highestScore;
    private int eatenDots;


    private int level;
    private int numberOfGhosts;
    private int numberOfFruits;
    private int ghostScore = 200;
    private int nextFruitIndex = 0; // next fruit that is going to be activated
    private int fruitsActivated = 0; // the number of fruits that have been activated so far
    private final int fruitActiveTime = 10000; // fruit are active for 10000 ms (10s)
    private int effectLeftTime = 0;
    private int scoreFactor = 1;
    private final int ghostFlashingTime = 10000; // how long ghost keeps flashing
    private final int maxGhosts = 4;
    private transient List<int[]>portals;
    private transient Random random;
    /**
     * Constructor.
     * @param gameLevel Either level 1 or level 2
     * @param numberOfGhosts Number of ghosts
     * @param maxLives Number of lives of pacman
     * @param highestScore Current highest score
     */
    public GameContext(int gameLevel, int numberOfGhosts, int maxLives, int highestScore) {
        level = gameLevel;
        this.numberOfGhosts = numberOfGhosts;
        this.maxLives = maxLives;
        this.highestScore = highestScore;
        random = new Random();
    }

    /**
     * Initialize a new game with a level and settings.
     */
    public void init() {
        currentScore = 0;
        eatenDots = 0;
        ghostScore = 200;
        status = 1;
        portals = new ArrayList<>();
        fruitsActivated = 0;
        numberOfFruits = 0;
        nextFruitIndex = 0;

        initLevel();
        createGhostsFromJson();
        createPacmanFromJson();
    }

    /**
     * Initialized the level.
     */
    private void initLevel() {
        JsonObject wallObject = null;
        JsonObject dotObject = null;
        JsonObject bigDotObject = null;
        JsonObject fruitObject = null;
        JsonObject fruitLocationsObject = null;

        wallObject = readJsonFile("public/json/wall new.json");
        dotObject = readJsonFile("public/json/dots new.json");
        bigDotObject = readJsonFile("public/json/big dots.json");
        fruitObject = readJsonFile("public/json/fruits.json");
        fruitLocationsObject = readJsonFile("public/json/fruit locations.json");

        List<Wall> walls = parseWallObject(wallObject);
        List<Dot> dots = parseDotObject(dotObject);
        List<LargeDot> largeDots = parseBigDotObject(bigDotObject);
        List<Fruit> fruits = parseFruitObject(fruitObject);
        HashMap<Point, Boolean> locations = parseFruitLocationsObject(fruitLocationsObject);
        gameLevel = new GameLevel(level, walls, fruits, dots, largeDots, locations);
    }

    /**
     * Parse wall object from json format.
     */
    public List<Wall> parseWallObject(JsonObject wallObject) {
        //Parse wall object
        List<Wall> walls = new ArrayList<>();
        if (wallObject != null) {
            JsonArray jsonWalls = wallObject.get("wall").getAsJsonArray();
            for (JsonElement element: jsonWalls) {
                JsonArray wallStart = element.getAsJsonObject().get("start").getAsJsonArray();
                JsonArray wallEnd = element.getAsJsonObject().get("end").getAsJsonArray();
                String wallColor = element.getAsJsonObject().get("color").getAsString();
                Point start = new Point(wallStart.get(0).getAsInt(),wallStart.get(1).getAsInt());
                Point end = new Point(wallEnd.get(0).getAsInt(),wallEnd.get(1).getAsInt());
                if (start.x == end.x) {
                    int length = end.y - start.y;
                    Wall wall = new Wall(start,wallColor,length,end);
                    walls.add(wall);

                } else if (start.y == end.y) {
                    int length = end.y - start.y;
                    Wall wall = new Wall(start,wallColor,length,end);
                    walls.add(wall);

                }
            }
        }
        return walls;
    }

    /**
     * Parse dot objects from json format.
     */
    public List<Dot> parseDotObject(JsonObject dotObject) {
        //Parse dot object
        List<Dot> dots = new ArrayList<>();
        if (dotObject != null) {
            JsonArray jsonDots = dotObject.get("dots").getAsJsonArray();
            for (JsonElement element: jsonDots) {
                JsonArray pos = element.getAsJsonObject().get("pos").getAsJsonArray();
                int radius = element.getAsJsonObject().get("radius").getAsInt();
                Point loc = new Point(pos.get(0).getAsInt(),pos.get(1).getAsInt());
                Dot dot = new Dot(loc, "white", 10, radius);
                dots.add(dot);
            }
        }
        return dots;
    }

    /**
     * Parse big dot objects from json format.
     */
    public List<LargeDot> parseBigDotObject(JsonObject bigDotObject) {
        //Parse big dot objects
        List<LargeDot> largeDots = new ArrayList<>();
        if (bigDotObject != null) {
            JsonArray jsonBigDots = bigDotObject.get("bigDots").getAsJsonArray();
            for (JsonElement element: jsonBigDots) {
                JsonArray pos = element.getAsJsonObject().get("pos").getAsJsonArray();
                int radius = element.getAsJsonObject().get("radius").getAsInt();
                Point loc = new Point(pos.get(0).getAsInt(),pos.get(1).getAsInt());
                LargeDot dot = new LargeDot(loc, "white", 50, radius);
                largeDots.add(dot);
            }
        }
        return largeDots;
    }

    /**
     * Parse fruit objects from json format.
     */
    public List<Fruit> parseFruitObject(JsonObject fruitObject) {
        //Parse fruit object

        return null;
    }

    /**
     * Parse fruit locations objects from json format.
     */
    public HashMap<Point, Boolean> parseFruitLocationsObject(JsonObject fruitLocationsObject) {
        //Parse fruit locations object
        return null;
    }

    /**
     * Parse ghost objects from json format.
     */
    public void createGhostsFromJson() {
        JsonObject ghostsObject = null;

        ghostsObject = readJsonFile("public/json/ghosts.json");

        if (ghostsObject != null) {
            ghosts = new ArrayList<>();

            int id = 0;
            JsonArray ghostsJson = ghostsObject.get("ghosts").getAsJsonArray();
            for (JsonElement ele : ghostsJson) {
                JsonArray pos = ele.getAsJsonObject().get("loc").getAsJsonArray();
                JsonArray vel = ele.getAsJsonObject().get("vel").getAsJsonArray();
                String color = ele.getAsJsonObject().get("color").getAsString();
                String updateStrategyName = ele.getAsJsonObject().get("updateStrategy").getAsString();
                String collideStrategyName = ele.getAsJsonObject().get("collideStrategy").getAsString();
                int size = ele.getAsJsonObject().get("size").getAsInt();
                boolean isFlashing = ele.getAsJsonObject().get("isFlashing").getAsBoolean();
                boolean isDead = ele.getAsJsonObject().get("isDead").getAsBoolean();
                int flashingTimer = ele.getAsJsonObject().get("flashingTimer").getAsInt();
                int direction = ele.getAsJsonObject().get("direction").getAsInt();

                Ghost ghost = new Ghost(new Point(pos.get(0).getAsInt(), pos.get(1).getAsInt()), new Point(vel.get(0).getAsInt(), vel.get(1).getAsInt()),
                        color, getUpdateStrategy(updateStrategyName), getCollideStrategy(collideStrategyName),
                        size, isFlashing, isDead, flashingTimer, new Point(0, 0));

                ghosts.add(ghost);

                id++;
            }
        }
    }

    /**
     * Parse pacman objects from json format.
     */
    public void createPacmanFromJson() {
        JsonObject pacmanObject = null;

        pacmanObject = readJsonFile("public/json/pacman.json");


        if (pacmanObject != null) {
            JsonArray pacmanJson = pacmanObject.get("pacman").getAsJsonArray();
            for (JsonElement ele : pacmanJson) {
                JsonArray pos = ele.getAsJsonObject().get("loc").getAsJsonArray();
                JsonArray vel = ele.getAsJsonObject().get("vel").getAsJsonArray();
                String updateStrategyName = ele.getAsJsonObject().get("updateStrategy").getAsString();
                String collideStrategyName = ele.getAsJsonObject().get("collideStrategy").getAsString();
                int size = ele.getAsJsonObject().get("size").getAsInt();
                int direction = ele.getAsJsonObject().get("direction").getAsInt();
                pacman = new Pacman(new Point(pos.get(0).getAsInt(), pos.get(1).getAsInt()), new Point(vel.get(0).getAsInt(), vel.get(1).getAsInt()),
                        getUpdateStrategy(updateStrategyName), getCollideStrategy(collideStrategyName),
                        size, direction, maxLives);
            }
        }
    }

    /**
     * Get the update strategy.
     * @param name strategy name
     * @return update strategy.
     */
    private IUpdateStrategy getUpdateStrategy(String name) {
        return null;
    }

    /**
     * Get the collide strategy.
     * @param name strategy name
     * @return collide strategy.
     */
    private ICollideStrategy getCollideStrategy(String name) {
        return null;
    }

    /**
     * Read json files from url.
     */
    private JsonObject readJsonFile(String url) {
        JsonParser parser = new JsonParser();
        try {
            InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(url);
            JsonObject jsonObject = (JsonObject)parser.parse(new InputStreamReader(input, "UTF-8"));
            input.close();
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Remove a dot.
     * @param dot dot needs to be removed
     * @param isEaten if the dot is eaten
     */
    public void removeDot(Dot dot, boolean isEaten) {


    }

    /**
     * Remove a big dot.
     * @param dot big dot needs to be removed
     * @param isEaten if the big dot is eaten
     */
    public void removeBigDot(LargeDot dot, boolean isEaten) {
    }

    /**
     * Remove a fruit.
     * @param fruit fruit needs to be removed
     * @param isEaten if the fruit is eaten
     */
    public void removeFruit(Fruit fruit, boolean isEaten) {
    }

    /**
     * Active the next fruit to be display.
     */
    public void activateNextFruit() {
    }


    /**
     * Set the game settings.
     * @param gameLevel Game level.
     * @param numGhosts Number of ghosts.
     * @param numLives  Number of lives.
     */
    public void setGameParameters(int gameLevel, int numGhosts, int numLives) {
        level = gameLevel;
        numberOfGhosts = numGhosts;
        maxLives = numLives;
        init();
    }



    /**
     * Decrease the effect time.
     * @return If the effect time runs out.
     */
    public boolean decreaseEffectTime() {
        return false;
    }
}
