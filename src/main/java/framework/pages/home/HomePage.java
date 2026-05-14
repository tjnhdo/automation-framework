package framework.pages.home;

import framework.base.BasePage;
import framework.config.EnvironmentConfig;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    private final By moreInfoLocator = By.cssSelector("a[href='https://www.iana.org/domains/example']");
    private final By headingLocator = By.cssSelector("h1");

    public HomePage open() {
        open(EnvironmentConfig.getBaseUrl());
        return this;
    }

    public String getHeading() {
        return getText(headingLocator);
    }

    public void clickMoreInformation() {
        click(moreInfoLocator);
    }
}
