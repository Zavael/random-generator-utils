/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.badand.text;

import sk.badand.math.Randomizer;

/**
 *
 * @author abadinka <andrej.badinka@interway.sk>
 */
public class RandomStringGenerator {

    String charsLow = "abcdefghijklmnopqrstuvwxyz";
    String charsUpp = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String charsSpecialLow = "áéíóúľščťžňý";
    String charsSpecialUpp = "ÁÉÍÓÚĽŠČŤŽŇÝ";
    String charsNumbers = "0123456789";

    private String generateString(
            boolean includeLower,
            boolean includeUpper,
            boolean includeSpecialLow,
            boolean includeSpecialUpp,
            boolean includeNumbers,
            int minCount, int maxCount) {

        if (maxCount <= minCount) {
            maxCount = minCount + 1;
        }

        StringBuilder sb = new StringBuilder();
        StringBuilder bucket = new StringBuilder();

        if (includeLower) {
            bucket.append(charsLow);
        }
        if (includeUpper) {
            bucket.append(charsUpp);
        }
        if (includeNumbers) {
            bucket.append(charsNumbers);
        }
        if (includeSpecialLow) {
            bucket.append(charsSpecialLow);
        }
        if (includeSpecialUpp) {
            bucket.append(charsSpecialUpp);
        }

        int lenght = Randomizer.nextRandomInt(maxCount - minCount) + minCount;
        char[] chars = bucket.toString().toCharArray();

        for (int i = 0; i < lenght; i++) {
            sb.append(chars[Randomizer.nextRandomInt(chars.length)]);
        }
        return sb.toString();
    }

    public String generateDesignation(int maxCount) {
        return generateString(false, true, false, false, false, 1, 1) + generateString(true, false, true, false, false, 3, maxCount);
    }
    
    public String generateText(int wordCount) {
        return "To be done";//TODO finish
    }
    

    public static void main(String[] args) {
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator();
        System.out.println("5:" + randomStringGenerator.generateDesignation(5));
        System.out.println("15:" + randomStringGenerator.generateDesignation(15));
        System.out.println("25:" + randomStringGenerator.generateDesignation(25));

        System.out.println("3-10 full:" + randomStringGenerator.generateString(true, true, true, true, true, 3, 10));
        System.out.println("7-1 full:" + randomStringGenerator.generateString(true, true, true, true, true, 7, 1));
    }
}
