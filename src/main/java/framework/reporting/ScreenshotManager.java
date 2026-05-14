package framework.reporting;

import framework.utils.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotManager {
    private static final DateTimeFormatter TIMESTAMP = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    private ScreenshotManager() {
    }

    public static Path capture(WebDriver driver, String testName) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String fileName = String.format("%s_%s.png", testName.replaceAll("[^a-zA-Z0-9_-]", "_"), LocalDateTime.now().format(TIMESTAMP));
        Path destination = FileUtils.createReportPath("screenshots", fileName);
        try {
            FileUtils.copyFile(screenshot.toPath(), destination);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to save screenshot: " + destination, e);
        }
        return destination;
    }
}

