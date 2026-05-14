package framework.sync;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public final class WaitManager {
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(Integer.parseInt(System.getProperty("WAIT_TIMEOUT", "15")));
    private static final Duration POLLING_INTERVAL = Duration.ofMillis(300);

    private WaitManager() {
    }

    public static WebElement waitForVisible(WebDriver driver, By locator) {
        return createWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(WebDriver driver, By locator) {
        return createWait(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean waitForInvisibility(WebDriver driver, By locator) {
        return createWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static <T> T waitFor(WebDriver driver, Function<? super WebDriver, T> condition) {
        return createWait(driver).until(condition);
    }

    private static Wait<WebDriver> createWait(WebDriver driver) {
        return new FluentWait<>(driver)
                .withTimeout(DEFAULT_TIMEOUT)
                .pollingEvery(POLLING_INTERVAL)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(org.openqa.selenium.NoSuchElementException.class);
    }
}
