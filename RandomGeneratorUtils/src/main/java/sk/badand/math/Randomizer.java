/*
 * Copyright 2014 Andrej Badinka
 */

package sk.badand.math;

import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author abadinka
 */
public class Randomizer {
    private static final Random random = new Random(Calendar.getInstance().getTime().getTime());

    /**
     * Returns the next pseudorandom, uniformly distributed double value between 0.0(inclusive) and 1.0(exclusive) from this random number
     * generator's sequence.
     * @return 
     */
    public double nextDouble() {
        return random.nextDouble();
    }

    /**
     * Returns the next pseudorandom, uniformly distributed double value between 0.0(inclusive) and upperBound(exclusive) from this random number
     * generator's sequence.
     * @param upperBound
     * @return 
     */
    public double nextDouble(double upperBound) {
        return random.nextDouble() * upperBound;
    }

    /**
     * Returns the next pseudorandom, uniformly distributed double value between lowerBound(inclusive) and upperBound(exclusive) from this random number
     * generator's sequence.
     * @param lowerBound
     * @param upperBound
     * @return 
     */
    public double nextDouble(double lowerBound, double upperBound) {
        if (upperBound <= lowerBound) {
            throw new IllegalArgumentException("UpperBound param must be higher than lowerBound param.");
        }
        return random.nextDouble() * (upperBound - lowerBound) + lowerBound;
    }
    
    /**
     * Returns a pseudorandom, uniformly distributed int value between 0 (inclusive) and the specified value (exclusive)
     * @param upperBound
     * @return 
     */
    public int nextRandomInt(int upperBound){
        return random.nextInt(upperBound);
    }   
    
    /**
     * Returns a pseudorandom, uniformly distributed int value between lowerBound (inclusive) and the specified value (exclusive)
     * @param lowerBound
     * @param upperBound
     * @return 
     */
    public int nextRandomInt(int lowerBound, int upperBound){
        if (upperBound <= lowerBound) {
            throw new IllegalArgumentException("UpperBound param must be higher than lowerBound param.");
        }
        return random.nextInt(upperBound - lowerBound) + lowerBound;
    }    
}
