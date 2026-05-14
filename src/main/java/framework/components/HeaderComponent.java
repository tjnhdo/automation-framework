package framework.components;

import framework.sync.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderComponent {
    private final WebDriver driver;
    private final By headerLocator;

    public HeaderComponent(WebDriver driver, By headerLocator) {
        this.driver = driver;
        this.headerLocator = headerLocator;
    }

    public WebElement getHeader() {
        return WaitManager.waitForVisible(driver, headerLocator);
    }

    public void clickLogo(By logoLocator) {
        WaitManager.waitForClickable(driver, logoLocator).click();
    }
}
