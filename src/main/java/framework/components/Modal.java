package framework.components;

import framework.driver.DriverManager;
import framework.sync.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Modal {
    private final By modalLocator;

    public Modal(By modalLocator) {
        this.modalLocator = modalLocator;
    }

    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public boolean isDisplayed() {
        return WaitManager.waitForVisible(getDriver(), modalLocator).isDisplayed();
    }

    public void close(By closeButtonLocator) {
        WaitManager.waitFor(getDriver(), d -> {
            WaitManager.waitForClickable(d, closeButtonLocator).click();
            return true;
        });
        WaitManager.waitForInvisibility(getDriver(), modalLocator);
    }
}