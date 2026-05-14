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

    private Select getSelect() {
        WebElement element = WaitManager.waitForVisible(getDriver(), locator);
        return new Select(element);
    }

    public void selectByVisibleText(String label) {
        getSelect().selectByVisibleText(label);
    }

    public void selectByValue(String value) {
        getSelect().selectByValue(value);
    }

    public String getSelectedOption() {
        return getSelect().getFirstSelectedOption().getText();
    }
}