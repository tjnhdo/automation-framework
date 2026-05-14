package framework.pages.login;

import framework.base.BasePage;
import framework.config.ConfigManager;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    // 1. Khai báo các locator (Ưu tiên id và cssSelector theo chuẩn dự án)
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By loginBtn = By.cssSelector("button[type='submit']");
    private final By errorMessage = By.cssSelector(".error-message");

    // 2. Hàm mở trang Login
    public LoginPage open() {
        open(ConfigManager.getBaseUrl() + "/login");
        return this;
    }

    // 3. Hàm thực hiện hành động login
    public void login(String email, String password) {
        // Gọi các hàm tương tác an toàn từ BasePage (tự động có wait ngầm định)
        type(emailInput, email);
        type(passwordInput, password);
        click(loginBtn);
    }

    // 4. Hàm lấy thông báo lỗi để đối chiếu bên Test Class
    public String getErrorMessage() {
        return getText(errorMessage);
    }
}
