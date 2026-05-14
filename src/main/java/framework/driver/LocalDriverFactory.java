package framework.driver;

import framework.config.BrowserConfig;
import framework.enums.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class LocalDriverFactory {
    private LocalDriverFactory() {
    }

    public static WebDriver createDriver(BrowserConfig config) {
        if (config.getBrowser() == BrowserType.FIREFOX) {
            return new FirefoxDriver(BrowserOptionsFactory.createFirefoxOptions(config));
        }
        return new ChromeDriver(BrowserOptionsFactory.createChromeOptions(config));
    }
}
