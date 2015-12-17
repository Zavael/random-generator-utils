/*
 * Copyright 2014 Andrej Badinka
 */
package sk.badand.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sk.badand.math.EmpiricDecider;
import sk.badand.math.OddsDecider;
import sk.badand.math.Randomizer;

/**
 *
 * @author abadinka <andrej.badinka@interway.sk>
 */
public class StrGenerator {

    private final Randomizer randomizer = new Randomizer();

    String vowelsLow = "aeiouy";
    String vowelsSpecialLow = "áéíóúýä";
    String consonantsLow = "bcdfghjklmnpqrstvwxz";
    String consonantsSpecialLow = "čďľĺŕňšťž";
    String vowelsUpp = "AEIOUY";
    String vowelsSpecialUpp = "ÁÉÍÓÚÝÄ";
    String consonantsUpp = "BCDFGHJKLMNPQRSTVWXZ";
    String consonantsSpecialUpp = "ČĎĽĹŔŇŠŤŽ";
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

        int lenght = randomizer.nextRandomInt(maxCount - minCount) + minCount;

        Map<String, Double> buckets = new HashMap<>();
        if (includeLower) {
            buckets.put(vowelsLow, 3.0);
            buckets.put(consonantsLow, 4.0);
            buckets.put(vowelsLow, 3.0);
            buckets.put(consonantsLow, 4.0);
            buckets.put(vowelsLow, 3.0);
            buckets.put(consonantsLow, 4.0);
        }
        if (includeUpper) {
            buckets.put(vowelsUpp, 3.0);
            buckets.put(consonantsUpp, 5.0);
            buckets.put(vowelsUpp, 3.0);
            buckets.put(consonantsUpp, 5.0);
        }
        if (includeSpecialLow) {
            buckets.put(vowelsSpecialLow, 1.0);
            buckets.put(consonantsSpecialLow, 1.0);
            buckets.put(vowelsSpecialLow, 1.0);
            buckets.put(consonantsSpecialLow, 1.0);
        }
        if (includeSpecialUpp) {
            buckets.put(vowelsSpecialUpp, 1.0);
            buckets.put(consonantsSpecialUpp, 1.0);
            buckets.put(vowelsSpecialUpp, 1.0);
            buckets.put(consonantsSpecialUpp, 1.0);
        }
        if (includeNumbers) {
            buckets.put(charsNumbers, 2.0);
        }

        EmpiricDecider<String> empiricDecider = new EmpiricDecider<>(buckets);
        char newChar;
        char lastChar = ' ';
        for (int i = 0; i < lenght; i++) {
            String successfulOption = empiricDecider.getSuccessfulOption();
            char[] chars = successfulOption.toCharArray();
            newChar = chars[randomizer.nextRandomInt(chars.length)];
            if (newChar != lastChar) {
                sb.append(newChar);
                lastChar = newChar;
            }
        }
        return sb.toString();
    }

    public String generateDesignation(int maxCount) {
        return generateString(false, true, false, false, false, 1, 1) + generateString(true, false, true, false, false, 1, maxCount-1);
    }

    public String generateWord(int maxCount) {
        return generateString(true, false, true, false, false, 2, maxCount);
    }

    public String generateParagraph(int wordCount) {
        StringBuilder text = new StringBuilder(generateDesignation(10));
        OddsDecider oddsDecider = new OddsDecider();
        for (int i = 0; i < wordCount; i++) {
            if (oddsDecider.decideSuccess(0.1)) {
                text.append(". ").append(generateDesignation(10));
            } else if (oddsDecider.decideSuccess(0.2)) {
                text.append(", ").append(generateWord(10));
            } else {
                text.append(" ").append(generateWord(10));
            }
        }
        return text.append(".").toString();
    }

    public static void main(String[] args) {
        StrGenerator strGenerator = new StrGenerator();
        System.out.println("word1: " + strGenerator.generateWord(10));
        System.out.println("word2: " + strGenerator.generateWord(10));
        System.out.println("word3: " + strGenerator.generateWord(10));

        System.out.println("designation1: " + strGenerator.generateDesignation(10));
        System.out.println("designation2: " + strGenerator.generateDesignation(10));
        System.out.println("designation3: " + strGenerator.generateDesignation(10));

        System.out.println("paragraph1: " + strGenerator.generateParagraph(20));
        System.out.println("paragraph2: " + strGenerator.generateParagraph(20));
        System.out.println("paragraph3: " + strGenerator.generateParagraph(20));
    }
}
