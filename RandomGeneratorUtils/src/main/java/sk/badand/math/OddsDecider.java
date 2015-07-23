/*
 * Copyright 2014 Andrej Badinka
 */

package sk.badand.math;

/**
 *
 * @author abadinka
 */
public class OddsDecider {
    
    /**
     * Desides if the given chance succeeded.
     * For example call OddsDecider.decideSuccess(0.4) has a 40% chance to return true and 60% chance to return false
     * @param chance from 0.0 to 1.0
     * @return 
     */
    public static boolean decideSuccess(double chance){
        if (chance < 0.0 || chance > 1.0) {
            throw new IllegalArgumentException("Argument 'chance' out of universe 0.0 - 1.0");
        }
        return Randomizer.nextDouble() < chance;
    }
}
