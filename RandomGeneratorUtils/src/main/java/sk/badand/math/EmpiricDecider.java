/*
 * Copyright 2014 Andrej Badinka
 */

package sk.badand.math;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author abadinka
 * @param <V>
 */
public class EmpiricDecider<V extends Object>{
    private final Randomizer randomizer = new Randomizer();
    
    private final Map<V, Double> optionsWithOdds;

    public EmpiricDecider(Map<V, Double> optionsWithOdds) {
        this.optionsWithOdds = optionsWithOdds;
    }
    
    public V getSuccessfulOption(double chance){        
        double totalOdds = 0;
        LinkedHashMap<V, Double> optionRanges = new LinkedHashMap<>(optionsWithOdds.size());
        for (V option : optionsWithOdds.keySet()) {
            totalOdds += optionsWithOdds.get(option);
            optionRanges.put(option, totalOdds);
        }
        double realChance = chance * totalOdds;
        for (V option : optionRanges.keySet()) {
            if (realChance < optionRanges.get(option)) {
                return option;
            }
        }
        
        throw new IllegalArgumentException("Argument 'chance' is out of universe 1.0");
    }
    
    public V getSuccessfulOption(){
        return getSuccessfulOption(randomizer.nextDouble());
    }
}
