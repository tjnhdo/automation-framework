package framework.config;

import framework.enums.BrowserType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigManager {
    private static final BrowserConfig CONFIG = new BrowserConfig();
    private static final String PROPERTIES_PATH = "/config/application.properties";

    static {
        loadDefaults();
        applySystemOverrides();
    }

    private ConfigManager() {
    }

    public static BrowserConfig getBrowserConfig() {
        return CONFIG;
    }

    private static void loadDefaults() {
        try (InputStream stream = ConfigManager.class.getResourceAsStream(PROPERTIES_PATH)) {
            if (stream != null) {
                Properties properties = new Properties();
                properties.load(stream);
                CONFIG.setBrowser(BrowserType.valueOf(properties.getProperty("browser", CONFIG.getBrowser().name()).toUpperCase()));
                CONFIG.setHeadless(Boolean.parseBoolean(properties.getProperty("headless", String.valueOf(CONFIG.isHeadless()))));
                CONFIG.setRemote(Boolean.parseBoolean(properties.getProperty("remote", String.valueOf(CONFIG.isRemote()))));
                CONFIG.setRemoteUrl(properties.getProperty("remoteUrl", CONFIG.getRemoteUrl()));
            }
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load application properties", e);
        }
    }

    private static void applySystemOverrides() {
        String browser = System.getProperty("browser");
        if (browser != null && !browser.isBlank()) {
            CONFIG.setBrowser(BrowserType.valueOf(browser.toUpperCase()));
        }

        String headless = System.getProperty("headless");
        if (headless != null) {
            CONFIG.setHeadless(Boolean.parseBoolean(headless));
        }

        String remote = System.getProperty("remote");
        if (remote != null) {
            CONFIG.setRemote(Boolean.parseBoolean(remote));
        }

        String remoteUrl = System.getProperty("remoteUrl");
        if (remoteUrl != null && !remoteUrl.isBlank()) {
            CONFIG.setRemoteUrl(remoteUrl);
        }
    }
}
