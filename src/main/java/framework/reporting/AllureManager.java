package framework.reporting;

import framework.config.EnvironmentConfig;
import io.qameta.allure.Allure;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;

public final class AllureManager {
    private AllureManager() {
    }

    public static void attachScreenshot(Path screenshotPath) {
        try (FileInputStream stream = new FileInputStream(screenshotPath.toFile())) {
            Allure.addAttachment("Screenshot", "image/png", stream, ".png");
        } catch (IOException e) {
            throw new IllegalStateException("Unable to attach screenshot: " + screenshotPath, e);
        }
    }

    public static void attachText(String name, String content) {
        Allure.addAttachment(name, "text/plain", content);
    }

    public static void attachHtml(String name, String content) {
        Allure.addAttachment(name, "text/html", content);
    }

    public static synchronized void generateEnvironmentProperties() {
        try {
            Properties props = new Properties();
            props.setProperty("Base_URL", EnvironmentConfig.getBaseUrl());
            props.setProperty("OS_Name", System.getProperty("os.name"));
            props.setProperty("OS_Version", System.getProperty("os.version"));
            props.setProperty("Java_Version", System.getProperty("java.version"));

            File allureResultsDir = new File("allure-results");
            if (!allureResultsDir.exists()) {
                allureResultsDir.mkdirs();
            }

            File envFile = new File(allureResultsDir, "environment.properties");
            try (FileOutputStream fos = new FileOutputStream(envFile)) {
                props.store(fos, "Allure Environment Information");
            }
        } catch (Exception e) {
            System.err.println("Failed to generate Allure environment properties: " + e.getMessage());
        }
    }
}
