package framework.elements;

import framework.driver.DriverManager;
import framework.sync.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Toast {
    private final By locator;

    public Toast(By locator) {
        this.locator = locator;
    }

    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public String getMessage() {
        return WaitManager.waitForVisible(getDriver(), locator).getText();
    }

    public boolean isDisplayed() {
        return WaitManager.waitForVisible(getDriver(), locator).isDisplayed();
    }
}
