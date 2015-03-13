/*
 * Copyright 2014 Andrej Badinka
 */

package sk.badand.math;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author abadinka
 */
public class EmpiricDecider {
    
    public static <T> T getSuccessfulOption(Map<T, Double> optionsWithOdds, double chance){        
        double totalOdds = 0;
        LinkedHashMap<T, Double> optionRanges = new LinkedHashMap<>(optionsWithOdds.size());
        for (T option : optionsWithOdds.keySet()) {
            totalOdds += optionsWithOdds.get(option);
            optionRanges.put(option, totalOdds);
        }
        double realChance = chance * totalOdds;
        for (T option : optionRanges.keySet()) {
            if (realChance < optionRanges.get(option)) {
                return option;
            }
        }
        
        throw new IllegalArgumentException("Argument 'chance' is out of universe 1.0");
    }
    
    public static <T> T getSuccessfulOption(Map<T, Double> optionsWithOdds){
        return getSuccessfulOption(optionsWithOdds, Randomizer.nextDouble());
    }
}
