package edu.rice.comp504.model.factory;

import edu.rice.comp504.model.strategy.IUpdateStrategy;

/**
 * Strategy factory helps to create a strategy.
 */
public class StrategyFactory {

    public static StrategyFactory singleton;

    /**
     * Only makes 1 eat strategy factory.
     * @return The eat strategy factory.
     */
    public static StrategyFactory makeStrategyFactory() {
        if (singleton == null ) {
            singleton = new StrategyFactory();
        }
        return singleton;
    }

    /**
     * Make a strategy based on input type.
     * @param type The type of the strategy.
     * @return The corresponding strategy.
     */
    public IUpdateStrategy makeStrategy(String type) {
        IUpdateStrategy strategy = null;
        switch (type) {
            default:
                break;
        }
        return strategy;
    }


}
