package net.nyaclient.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringUtils {
    // https://www.geeksforgeeks.org/java/java-program-to-capitalize-the-first-letter-of-each-word-in-a-string/
    public static String capitalizeText(String input) {
        return Arrays.stream(input.split("\\s"))
                .map(word -> Character.toTitleCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
    }
}
