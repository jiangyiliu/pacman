package edu.rice.comp504.model.strategy;


import edu.rice.comp504.model.character.ARole;

/**
 * The pacman strategy causes an pacman to move.
 */
public class PacmanStrategy implements IUpdateStrategy {
    private static IUpdateStrategy singleton;

    /**
     * Constructor.
     */
    private PacmanStrategy() {

    }

    /**
     * Make a straight strategy.  There is only one (singleton).
     * @return A straight strategy
     */
    public static IUpdateStrategy makeStrategy() {
        if (singleton == null) {
            singleton = new PacmanStrategy();
        }

        return singleton;
    }

    /**
     * Get the strategy name.
     * @return strategy name
     */
    @Override
    public String getName() {
        return "pacman";
    }

    /**
     * Update the character state in the character world.
     * @param context The character to update
     */
    @Override
    public void updateState(ARole context) {
    }

    /**
     * Update the paintobj state in the paintobj world.
     * @param pacman The pacman information, if needed
     * @param context The paintobj to update
     */
    @Override
    public void updateState(ARole pacman, ARole context) {
        updateState(context);
    }
}


