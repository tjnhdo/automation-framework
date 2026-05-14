package framework.base;

import framework.driver.DriverManager;
import framework.sync.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.qameta.allure.Step;

public abstract class BasePage {
    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    @Step("Click on element: {locator}")
    protected void click(By locator) {
        WaitManager.waitFor(getDriver(), d -> {
            WaitManager.waitForClickable(d, locator).click();
            return true;
        });
    }

    @Step("Type text '{text}' into element: {locator}")
    protected void type(By locator, String text) {
        WaitManager.waitFor(getDriver(), d -> {
            WebElement element = WaitManager.waitForVisible(d, locator);
            element.clear();
            element.sendKeys(text);
            return true;
        });
    }

    @Step("Get text from element: {locator}")
    protected String getText(By locator) {
        return WaitManager.waitFor(getDriver(), d -> {
            return WaitManager.waitForVisible(d, locator).getText();
        });
    }

    @Step("Get page title")
    protected String getPageTitle() {
        return getDriver().getTitle();
    }

    @Step("Open URL: {url}")
    protected void open(String url) {
        getDriver().get(url);
    }
}
