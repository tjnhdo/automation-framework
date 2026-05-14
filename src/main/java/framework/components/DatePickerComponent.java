package framework.components;

import framework.driver.DriverManager;
import framework.sync.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DatePickerComponent {
    private final By dateInputLocator;
    private final By monthPickerLocator;
    private final String dayLocatorTemplate;

    public DatePickerComponent(By dateInputLocator, By monthPickerLocator, String dayLocatorTemplate) {
        this.dateInputLocator = dateInputLocator;
        this.monthPickerLocator = monthPickerLocator;
        this.dayLocatorTemplate = dayLocatorTemplate;
    }

    private WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    public void open() {
        WaitManager.waitFor(getDriver(), d -> {
            WaitManager.waitForClickable(d, dateInputLocator).click();
            return true;
        });
        WaitManager.waitForVisible(getDriver(), monthPickerLocator);
    }

    public void selectDay(String dayValue) {
        WaitManager.waitFor(getDriver(), d -> {
            String locator = String.format(dayLocatorTemplate, dayValue);
            WaitManager.waitForClickable(d, By.xpath(locator)).click();
            return true;
        });
    }
}
