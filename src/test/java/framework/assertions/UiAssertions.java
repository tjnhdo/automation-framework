package framework.assertions;

import org.testng.Assert;

public final class UiAssertions {
    private UiAssertions() {
    }

    public static void assertElementVisible(boolean condition, String message) {
        Assert.assertTrue(condition, message);
    }

    public static void assertTextEquals(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }

    public static void assertContains(String actual, String expectedSubstring, String message) {
        Assert.assertTrue(actual != null && actual.contains(expectedSubstring), message);
    }
}

