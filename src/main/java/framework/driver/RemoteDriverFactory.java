package framework.driver;

import framework.config.BrowserConfig;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public final class RemoteDriverFactory {
    private RemoteDriverFactory() {
    }

    public static WebDriver createDriver(BrowserConfig config) {
        try {
            MutableCapabilities capabilities = BrowserOptionsFactory.createOptions(config);
            return new RemoteWebDriver(new URL(config.getRemoteUrl()), capabilities);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Remote URL is invalid: " + config.getRemoteUrl(), e);
        }
    }
}
