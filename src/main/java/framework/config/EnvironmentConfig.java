package framework.config;

public final class EnvironmentConfig {
    
    private EnvironmentConfig() {
    }

    public static String getBaseUrl() {
        return System.getProperty("BASE_URL", "https://example.com");
    }
}