package framework.components;

import framework.sync.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DatePickerComponent {
    private final WebDriver driver;
    private final By dateInputLocator;
    private final By monthPickerLocator;
    private final String dayLocatorTemplate;

    public DatePickerComponent(WebDriver driver, By dateInputLocator, By monthPickerLocator, String dayLocatorTemplate) {
        this.driver = driver;
        this.dateInputLocator = dateInputLocator;
        this.monthPickerLocator = monthPickerLocator;
        this.dayLocatorTemplate = dayLocatorTemplate;
    }

    public void open() {
        WaitManager.waitForClickable(driver, dateInputLocator).click();
        WaitManager.waitForVisible(driver, monthPickerLocator);
    }

    public void selectDay(String dayValue) {
        String locator = String.format(dayLocatorTemplate, dayValue);
        WaitManager.waitForClickable(driver, By.xpath(locator)).click();
    }
}

