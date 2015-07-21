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
public abstract class Randomizer {
    private static final Random random = new Random(Calendar.getInstance().getTime().getTime());

    /**
     * Returns the next pseudorandom, uniformly distributed double value between 0.0(inclusive) and 1.0(exclusive) from this random number
     * generator's sequence.
     * @return 
     */
    public static double nextDouble() {
        return random.nextDouble();
    }
    
    /**
     * Returns a pseudorandom, uniformly distributed int value between 0 (inclusive) and the specified value (exclusive)
     * @param upperBound
     * @return 
     */
    public static int nextRandomInt(int upperBound){
        return random.nextInt(upperBound);
    }    
}
