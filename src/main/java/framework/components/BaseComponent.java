package framework.components;

import framework.driver.DriverManager;
import framework.sync.WaitManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

public abstract class BaseComponent {
    protected final By parentLocator;

    public BaseComponent(By parentLocator) {
        this.parentLocator = parentLocator;
    }

    /**
     * Kỹ thuật Chained Locator: Tìm element con BÊN TRONG component cha.
     * LƯU Ý: Vẫn tuân thủ Dynamic Resolution, không lưu WebElement ra biến global.
     */
    protected WebElement getChildElement(By childLocator) {
        WebDriver driver = DriverManager.getDriver();
        // 1. Chờ và phân giải lại element cha để tránh lỗi con xuất hiện chậm hơn cha
        WebElement parent = WaitManager.waitForVisible(driver, parentLocator);
        // 2. Tìm element con bên trong cha
        return parent.findElement(childLocator);
    }
    
    /**
     * Kỹ thuật Chained Locator: Tìm danh sách elements con BÊN TRONG component cha.
     * Dùng cho các trường hợp như tìm tất cả các hàng (rows) của một bảng.
     */
    protected List<WebElement> getChildElements(By childLocator) {
        WebDriver driver = DriverManager.getDriver();
        WebElement parent = WaitManager.waitForVisible(driver, parentLocator);
        return parent.findElements(childLocator);
    }

    // Các thao tác click, type trong component sẽ gọi getChildElement(locator).click()
    // Thực tế nên bọc qua WaitManager để ổn định hơn.
}