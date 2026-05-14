package framework.config;

import java.io.InputStream;
import java.util.Properties;
import framework.enums.BrowserType;

/**
 * Single Source of Truth cho toàn bộ cấu hình của Framework.
 * Đọc cấu hình từ application.properties và cho phép ghi đè bằng System.getProperty (từ CI/Maven).
 */
public class ConfigManager {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigManager.class.getClassLoader().getResourceAsStream("config/application.properties")) {
            if (input != null) {
                properties.load(input);
            }
        } catch (Exception e) {
            throw new RuntimeException("Không thể đọc file cấu hình application.properties", e);
        }
    }

    public static String getProperty(String key, String defaultValue) {
        // System property (Maven/CI) luôn có độ ưu tiên cao nhất
        String sysProp = System.getProperty(key);
        if (sysProp != null && !sysProp.trim().isEmpty()) {
            return sysProp;
        }
        return properties.getProperty(key, defaultValue);
    }

    // --- Các hàm tiện ích lấy cấu hình cụ thể ---
    public static String getBrowser() { return getProperty("browser", "CHROME"); }
    public static boolean isHeadless() { return Boolean.parseBoolean(getProperty("headless", "false")); }
    public static boolean isRemote() { return Boolean.parseBoolean(getProperty("remote", "false")); }
    public static String getRemoteUrl() { return getProperty("remoteUrl", "http://localhost:4444/wd/hub"); }
    
    public static String getBaseUrl() { return getProperty("env.url", "https://example.com"); }
    public static String getBaseApiUrl() { return getProperty("env.api.url", "https://api.example.com/v1"); }
    public static int getWaitTimeout() { return Integer.parseInt(getProperty("wait.timeout", "15")); }

    public static BrowserConfig getBrowserConfig() {
        BrowserConfig config = new BrowserConfig();
        try {
            config.setBrowser(BrowserType.valueOf(getBrowser().toUpperCase()));
        } catch (IllegalArgumentException e) {
            config.setBrowser(BrowserType.CHROME); // Mặc định là CHROME nếu truyền sai tên
        }
        config.setHeadless(isHeadless());
        config.setRemote(isRemote());
        config.setRemoteUrl(getRemoteUrl());
        return config;
    }
}