package framework.assertions;

import org.testng.Assert;

public final class CustomAssertions {
    private CustomAssertions() {
    }

    public static void assertNotEmpty(String actual, String message) {
        Assert.assertTrue(actual != null && !actual.isEmpty(), message);
    }

    public static void assertPositive(int value, String message) {
        Assert.assertTrue(value > 0, message);
    }
}

