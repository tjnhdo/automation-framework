package framework.components;

import framework.driver.DriverManager;
import framework.sync.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationDialog {
    private final By dialogLocator;
    private final By confirmButton;
    private final By cancelButton;

    public ConfirmationDialog(By dialogLocator, By confirmButton, By cancelButton) {
        this.dialogLocator = dialogLocator;
        this.confirmButton = confirmButton;
        this.cancelButton = cancelButton;
    }

    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public void confirm() {
        WaitManager.waitFor(getDriver(), d -> {
            WaitManager.waitForClickable(d, confirmButton).click();
            return true;
        });
        WaitManager.waitForInvisibility(getDriver(), dialogLocator);
    }

    public void cancel() {
        WaitManager.waitFor(getDriver(), d -> {
            WaitManager.waitForClickable(d, cancelButton).click();
            return true;
        });
        WaitManager.waitForInvisibility(getDriver(), dialogLocator);
    }

    public boolean isDisplayed() {
        return WaitManager.waitForVisible(getDriver(), dialogLocator).isDisplayed();
    }
}
