package tests.POJOTest;

import tests.base.BaseUiTest;
import framework.models.LoginData;
import framework.pages.login.LoginPage;
import framework.utils.JsonDataReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginDdtTest extends BaseUiTest {

    @DataProvider(name = "invalidLoginData")
    public Object[][] getInvalidLoginData() {
        // Tự động đọc file JSON từ thư mục testdata và map vào một mảng object LoginData
        return JsonDataReader.readData("loginData.json", LoginData.class);
    }

    @Test(dataProvider = "invalidLoginData", description = "Data-Driven Test cho chức năng Login với dữ liệu sai")
    public void testInvalidLogin(LoginData data) {
        // 1. Mở trang và thực hiện hành động Login
        LoginPage loginPage = new LoginPage().open();
        loginPage.login(data.getEmail(), data.getPassword());
        
        // 2. Kiểm chứng kết quả ở Test Class, map với expectedErrorMessage từ file JSON
        Assert.assertEquals(loginPage.getErrorMessage(), data.getExpectedErrorMessage(), "Error message should match the expected value from JSON data");
    }
}