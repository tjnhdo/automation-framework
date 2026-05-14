package framework.listeners;

import framework.driver.DriverManager;
import framework.reporting.AllureManager;
import framework.reporting.ScreenshotManager;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import framework.utils.FileUtils;
import java.nio.file.Files;
import org.openqa.selenium.logging.LogType;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        try {
            String testName = result.getName();
            var driver = DriverManager.getDriver();
            var screenshotPath = ScreenshotManager.capture(driver, testName);
            AllureManager.attachScreenshot(screenshotPath);

            // Capture URL
            String url = driver.getCurrentUrl();
            System.err.println(">>> Test failed at URL: " + url);
            AllureManager.attachText("Failed URL", url);

            // Capture Page Source (DOM)
            String pageSource = driver.getPageSource();
            var domPath = FileUtils.createReportPath("dom", testName + "_source.html");
            Files.writeString(domPath, pageSource);
            System.err.println(">>> Saved DOM snapshot to: " + domPath);
            AllureManager.attachHtml("DOM Snapshot", pageSource);
            
            // Capture Browser Console Logs
            var logEntries = driver.manage().logs().get(LogType.BROWSER).getAll();
            if (!logEntries.isEmpty()) {
                StringBuilder logs = new StringBuilder();
                System.err.println(">>> Browser Console Logs:");
                for (var entry : logEntries) {
                    String logLine = "[" + entry.getLevel() + "] " + entry.getMessage();
                    System.err.println(logLine);
                    logs.append(logLine).append("\n");
                }
                AllureManager.attachText("Browser Console Logs", logs.toString());
            }
        } catch (Exception e) {
            System.err.println("Failed to capture diagnostics on test failure: " + e.getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        AllureManager.generateEnvironmentProperties();
    }
}
