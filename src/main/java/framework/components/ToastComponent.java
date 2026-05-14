package framework.components;

import framework.sync.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ToastComponent {
    private final WebDriver driver;
    private final By toastLocator;

    public ToastComponent(WebDriver driver, By toastLocator) {
        this.driver = driver;
        this.toastLocator = toastLocator;
    }

    public String getMessage() {
        return WaitManager.waitForVisible(driver, toastLocator).getText();
    }

    public boolean isDisplayed() {
        return WaitManager.waitForVisible(driver, toastLocator).isDisplayed();
    }
}

