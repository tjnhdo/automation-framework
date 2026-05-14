package tests.base;

import framework.config.BrowserConfig;
import framework.config.ConfigManager;
import framework.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseUiTest {
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverManager.initDriver(ConfigManager.getBrowserConfig());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
