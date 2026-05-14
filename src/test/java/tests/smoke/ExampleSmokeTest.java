package tests.smoke;

import framework.assertions.UiAssertions;
import framework.pages.home.HomePage;
import org.testng.annotations.Test;
import tests.base.BaseUiTest;

public class ExampleSmokeTest extends BaseUiTest {

    @Test
    public void openHomePageShouldDisplayExampleDomain() {
        HomePage homePage = new HomePage().open();
        UiAssertions.assertTextEquals(homePage.getHeading(), "Example Domain", "Home page heading should be visible");
    }
}
