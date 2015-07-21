/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sk.badand.math;

/**
 *
 * @author abadinka
 */
public class OddsDecider {
    
    public static boolean decideSuccess(double chance){
        if (chance < 0.0 || chance > 1.0) {
            throw new IllegalArgumentException("Argument 'chance' out of universe 0.0 - 1.0");
        }
        return Randomizer.nextDouble() < chance;
    }
}
