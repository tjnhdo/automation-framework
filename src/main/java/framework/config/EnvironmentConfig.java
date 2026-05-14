package framework.config;

/**
 * @deprecated Hãy sử dụng {@link ConfigManager} thay thế để đảm bảo Single Source of Truth.
 */
@Deprecated
public class EnvironmentConfig {
    
    public static String getBaseUrl() {
        return ConfigManager.getBaseUrl();
    }

    public static String getBaseApiUrl() {
        return ConfigManager.getBaseApiUrl();
    }

    public static boolean isHeadless() {
        return ConfigManager.isHeadless();
    }
    
    public static int getWaitTimeout() {
        return ConfigManager.getWaitTimeout();
    }
}