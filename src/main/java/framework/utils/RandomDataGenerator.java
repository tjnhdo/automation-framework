package framework.utils;

import java.security.SecureRandom;
import java.util.Locale;

public final class RandomDataGenerator {
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    private RandomDataGenerator() {
    }

    public static String randomString(int length) {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            builder.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return builder.toString();
    }

    public static String randomEmail() {
        return String.format(Locale.ENGLISH, "%s@example.com", randomString(8));
    }
}

