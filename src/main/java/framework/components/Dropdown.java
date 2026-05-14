package framework.components;

import framework.driver.DriverManager;
import framework.sync.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Dropdown {
    private final By locator;

    public Dropdown(By locator) {
        this.locator = locator;
    }

    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public void selectByVisibleText(String label) {
        WaitManager.waitFor(getDriver(), d -> {
            new Select(WaitManager.waitForVisible(d, locator)).selectByVisibleText(label);
            return true;
        });
    }

    public void selectByValue(String value) {
        WaitManager.waitFor(getDriver(), d -> {
            new Select(WaitManager.waitForVisible(d, locator)).selectByValue(value);
            return true;
        });
    }

    public String getSelectedOption() {
        return WaitManager.waitFor(getDriver(), d -> {
            return new Select(WaitManager.waitForVisible(d, locator)).getFirstSelectedOption().getText();
        });
    }
}