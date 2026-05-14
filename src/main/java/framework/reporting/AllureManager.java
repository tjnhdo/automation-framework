package framework.reporting;

import io.qameta.allure.Allure;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

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
}
