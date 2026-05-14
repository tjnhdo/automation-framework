package framework.components;

import framework.sync.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationDialog {
    private final WebDriver driver;
    private final By dialogLocator;
    private final By confirmButton;
    private final By cancelButton;

    public ConfirmationDialog(WebDriver driver, By dialogLocator, By confirmButton, By cancelButton) {
        this.driver = driver;
        this.dialogLocator = dialogLocator;
        this.confirmButton = confirmButton;
        this.cancelButton = cancelButton;
    }

    public void confirm() {
        WaitManager.waitForClickable(driver, confirmButton).click();
        WaitManager.waitForInvisibility(driver, dialogLocator);
    }

    public void cancel() {
        WaitManager.waitForClickable(driver, cancelButton).click();
        WaitManager.waitForInvisibility(driver, dialogLocator);
    }

    public boolean isDisplayed() {
        return WaitManager.waitForVisible(driver, dialogLocator).isDisplayed();
    }
}

