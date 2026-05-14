# 03. Developer Guide (Hướng dẫn Lập trình)

## 🇺🇸 English

### 1. How to Create a New Page Object
**Rule:** Inherit from `BasePage`. Store `By` locators as `private final`. Do not pass `WebDriver` to the constructor.

```java
public class LoginPage extends BasePage {
    private final By emailInput = By.id("email");
    private final By loginBtn = By.cssSelector("button[type='submit']");

    public LoginPage open() {
        open(EnvironmentConfig.getBaseUrl() + "/login");
        return this;
    }

    public void login(String email) {
        type(emailInput, email);
        click(loginBtn);
    }
}
```

### 2. How to Create a New Test
**Rule:** Inherit from `BaseUiTest`. Keep it clean. Assertions go here.

```java
public class LoginTest extends BaseUiTest {
    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage().open();
        loginPage.login("test@example.com");
        
        // Assertion inside the test, not the page object
        UiAssertions.assertTextEquals(DashboardPage.getTitle(), "Dashboard", "User should be redirected to Dashboard");
    }
}
```

### 3. How to Write a Data-Driven Test (DDT)
**Rule:** Use TestNG `@DataProvider` combined with `JsonDataReader` to map JSON files to POJO models. Do not use hardcoded arrays or loops inside the test method.

```java
public class LoginDdtTest extends BaseUiTest {
    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() {
        // Automatically reads JSON and maps to LoginData POJO
        return JsonDataReader.readData("loginData.json", LoginData.class);
    }

    @Test(dataProvider = "invalidLoginData")
    // The test only receives a single, clean POJO parameter
    public void testInvalidLogin(LoginData data) {
        LoginPage loginPage = new LoginPage().open();
        loginPage.login(data.getEmail(), data.getPassword());
        UiAssertions.assertTextEquals(loginPage.getErrorMessage(), data.getExpectedErrorMessage(), "Error message should match");
    }
}
```

---

## 🇻🇳 Tiếng Việt

### 1. Cách tạo một Page Object mới
**Quy tắc:** Kế thừa từ `BasePage`. Khai báo `By` locators ở dạng `private final`. Tuyệt đối không truyền `WebDriver` vào constructor.

```java
public class LoginPage extends BasePage {
    private final By emailInput = By.id("email");
    private final By loginBtn = By.cssSelector("button[type='submit']");

    public LoginPage open() {
        open(EnvironmentConfig.getBaseUrl() + "/login");
        return this;
    }

    public void login(String email) {
        type(emailInput, email); // Gọi hàm an toàn từ BasePage
        click(loginBtn);         // Gọi hàm an toàn từ BasePage
    }
}
```

### 2. Cách tạo một Test Case mới
**Quy tắc:** Kế thừa từ `BaseUiTest`. Giữ code ngắn gọn. Đặt các hàm Assert tại đây.

```java
public class LoginTest extends BaseUiTest {
    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage().open();
        loginPage.login("test@example.com");
        
        // Phép kiểm chứng phải nằm ở Test Class
        UiAssertions.assertTextEquals(DashboardPage.getTitle(), "Dashboard", "User should be redirected to Dashboard");
    }
}
```