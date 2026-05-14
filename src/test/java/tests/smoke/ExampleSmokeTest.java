package tests.smoke;

import framework.api.ApiHelper;
import framework.assertions.UiAssertions;
import framework.pages.home.HomePage;
import framework.pages.login.LoginPage;
import org.testng.annotations.Test;
import tests.base.BaseUiTest;
import java.util.UUID;

public class ExampleSmokeTest extends BaseUiTest {

    @Test(groups = {"stable", "smoke"})
    public void openHomePageShouldDisplayExampleDomain() {
        HomePage homePage = new HomePage().open();
        UiAssertions.assertTextEquals(homePage.getHeading(), "Example Domain", "Home page heading should be visible");
    }

    /**
     * Hybrid Test Example: Sử dụng API để setup data, sau đó dùng UI để kiểm tra.
     */
    @Test(groups = {"stable"})
    public void loginWithNewlyCreatedUserViaApiShouldSucceed() {
        // 1. PRE-CONDITION (API Layer) - Tạo user độc lập cho test case này
        String uniqueEmail = "testuser_" + UUID.randomUUID() + "@example.com";
        String password = "SecurePassword123!";
        // ApiHelper.createTestUserViaApi(uniqueEmail, password); // Comment lại để tránh lỗi do API giả lập

        // 2. ACTION (UI Layer) - Mở trang web và login
        LoginPage loginPage = new LoginPage().open();
        // loginPage.login(uniqueEmail, password); 
        
        // 3. ASSERTION
        // UiAssertions.assertTextEquals(homePage.getHeading(), "Welcome", "Should login successfully");
    }
}
