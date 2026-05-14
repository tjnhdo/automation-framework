package framework.driver;

import org.openqa.selenium.WebDriver;
import framework.config.BrowserConfig;

public final class DriverManager {
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();
        if (driver == null) {
            throw new IllegalStateException("WebDriver has not been initialized for this thread.");
        }
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
    }

    public static void initDriver(BrowserConfig config) {
        if (config.isRemote()) {
            setDriver(RemoteDriverFactory.createDriver(config));
        } else {
            setDriver(LocalDriverFactory.createDriver(config));
        }
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}
