package framework.components;

import framework.sync.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SidebarComponent {
    private final WebDriver driver;
    private final By sidebarLocator;

    public SidebarComponent(WebDriver driver, By sidebarLocator) {
        this.driver = driver;
        this.sidebarLocator = sidebarLocator;
    }

    public WebElement getSidebar() {
        return WaitManager.waitForVisible(driver, sidebarLocator);
    }

    public void clickItem(By itemLocator) {
        WaitManager.waitForClickable(driver, itemLocator).click();
    }
}

