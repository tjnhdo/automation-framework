package framework.components;

import framework.driver.DriverManager;
import framework.sync.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SidebarComponent {
    private final By sidebarLocator;

    public SidebarComponent(By sidebarLocator) {
        this.sidebarLocator = sidebarLocator;
    }

    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public WebElement getSidebar() {
        return WaitManager.waitForVisible(getDriver(), sidebarLocator);
    }

    public void clickItem(By itemLocator) {
        WaitManager.waitFor(getDriver(), d -> {
            WaitManager.waitForClickable(d, itemLocator).click();
            return true;
        });
    }
}
