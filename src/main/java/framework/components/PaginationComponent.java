package framework.components;

import framework.driver.DriverManager;
import framework.sync.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaginationComponent {
    private final By paginationContainer;
    private final By nextButton;
    private final By previousButton;

    public PaginationComponent(By paginationContainer, By nextButton, By previousButton) {
        this.paginationContainer = paginationContainer;
        this.nextButton = nextButton;
        this.previousButton = previousButton;
    }

    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public void goNext() {
        WaitManager.waitForClickable(getDriver(), nextButton).click();
    }

    public void goPrevious() {
        WaitManager.waitForClickable(getDriver(), previousButton).click();
    }

    public boolean isDisplayed() {
        return WaitManager.waitForVisible(getDriver(), paginationContainer).isDisplayed();
    }
}
