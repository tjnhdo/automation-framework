package framework.driver;

import framework.config.BrowserConfig;
import framework.enums.BrowserType;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class BrowserOptionsFactory {
    private BrowserOptionsFactory() {
    }

    public static MutableCapabilities createOptions(BrowserConfig config) {
        if (config.getBrowser() == BrowserType.FIREFOX) {
            return createFirefoxOptions(config);
        }
        return createChromeOptions(config);
    }

    public static ChromeOptions createChromeOptions(BrowserConfig config) {
        ChromeOptions options = new ChromeOptions();
        if (config.isHeadless()) {
            options.addArguments("--headless=new");
        }
        options.addArguments("--disable-gpu", "--window-size=1920,1080", "--disable-extensions");
        return options;
    }

    public static FirefoxOptions createFirefoxOptions(BrowserConfig config) {
        FirefoxOptions options = new FirefoxOptions();
        if (config.isHeadless()) {
            options.addArguments("-headless");
        }
        options.addPreference("dom.webdriver.enabled", false);
        options.addPreference("media.navigator.streams.fake", true);
        return options;
    }
}
