/*
 * Copyright 2014 Andrej Badinka
 */

package sk.badand.text;

import sk.badand.math.OddsDecider;
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
            bucket.append(charsLow);
        }
        if (includeUpper) {
            bucket.append(charsUpp);
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
        return generateString(false, true, false, false, false, 1, 1) + generateString(true, false, true, false, false, 3, maxCount - 1);
    }
    
    public String generateWord(int maxCount) {
        return generateString(true, false, true, false, false, 2, maxCount);
    }
    
    public String generateText(int wordCount) {
        StringBuilder text = new StringBuilder(generateDesignation(10));
        for (int i = 0; i < wordCount; i++) {
            if (OddsDecider.decideSuccess(0.1)) {
                text.append(". ").append(generateDesignation(10));
            } else {
                text.append(" ").append(generateWord(10));
            }
        }
        return text.append(".").toString();
    }
    

    public static void main(String[] args) {
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator();
        System.out.println("word: " + randomStringGenerator.generateWord(10));
        System.out.println("word: " + randomStringGenerator.generateWord(10));
        System.out.println("word: " + randomStringGenerator.generateWord(10));
        
        System.out.println("text: " + randomStringGenerator.generateText(20));
        System.out.println("text: " + randomStringGenerator.generateText(20));
    }
}
