package framework.sync;

import framework.config.ConfigManager;
import framework.driver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class WaitManager {

    private static WebDriverWait getWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(ConfigManager.getWaitTimeout()));
    }

    /**
     * Chờ đợi linh hoạt dựa trên một function/điều kiện tuỳ chỉnh.
     * Tương thích với các khối lambda từ BasePage.
     */
    public static <T> T waitFor(WebDriver driver, Function<WebDriver, T> condition) {
        return getWait(driver).until(condition);
    }

    /**
     * Chờ cho đến khi element hiển thị và có thể click.
     */
    public static WebElement waitForClickable(WebDriver driver, By locator) {
        return getWait(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Chờ cho đến khi element xuất hiện trên DOM và hiển thị (visible).
     */
    public static WebElement waitForVisible(WebDriver driver, By locator) {
        return getWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Chờ cho đến khi element biến mất khỏi DOM hoặc bị ẩn đi (invisible).
     */
    public static Boolean waitForInvisibility(WebDriver driver, By locator) {
        return getWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Đảm bảo trình duyệt đã load xong toàn bộ DOM và JavaScript.
     * Rất hữu ích cho các trang web kiểu cũ hoặc sau khi redirect.
     */
    public static void waitForPageLoad() {
        WebDriver driver = DriverManager.getDriver();
        ExpectedCondition<Boolean> pageLoadCondition = d -> 
                ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete");
        
        getWait(driver).until(pageLoadCondition);
    }
}