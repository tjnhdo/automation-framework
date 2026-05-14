package framework.components;

import framework.driver.DriverManager;
import framework.sync.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HeaderComponent {
    private final By headerLocator;

    public HeaderComponent(By headerLocator) {
        this.headerLocator = headerLocator;
    }

    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public WebElement getHeader() {
        return WaitManager.waitForVisible(getDriver(), headerLocator);
    }

    public void clickLogo(By logoLocator) {
        WaitManager.waitFor(getDriver(), d -> {
            WaitManager.waitForClickable(d, logoLocator).click();
            return true;
        });
    }
}
