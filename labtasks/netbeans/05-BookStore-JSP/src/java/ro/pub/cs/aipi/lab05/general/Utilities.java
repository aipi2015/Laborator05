package ro.pub.cs.aipi.lab05.general;

import java.util.Random;
import java.util.StringTokenizer;

public class Utilities {

    final private static int NUMBER_OF_CHARACTERS = 25;
    final private static Random random = new Random(Constants.SEED);

    private static char generateCharacter() {
        return (char) ('A' + random.nextInt(NUMBER_OF_CHARACTERS));
    }

    private static String generateString(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(generateCharacter());
        }
        return result.toString();
    }

    public static String generateIdentificationNumber(int alphaLength, int numericLength) {
        int base = (int) Math.pow(10, numericLength - 1);
        return generateString(alphaLength) + (random.nextInt(9 * base) + base);
    }

    public static String removeSpaces(String text) {
        StringBuilder result = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(text, " ");
        while (stringTokenizer.hasMoreTokens()) {
            result.append(stringTokenizer.nextToken());
        }
        return result.toString();
    }

}
