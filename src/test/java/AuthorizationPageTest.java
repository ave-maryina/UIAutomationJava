import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Authorization page authentication")
public class AuthorizationPageTest extends BaseTest {

    @Test
    @Description("Checking the authorization of a registered user with valid data")
    @Severity(SeverityLevel.BLOCKER)
    @Story("Authorization by email and password")
    public void walidDataAuthorization() {
        BaseTest.authPage
                .openAuthorizationPage()
                .login("test@example.com", "Qwerty123");

        BaseTest.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@alt='Edit']")));

        Assert.assertFalse(BaseTest.driver.getCurrentUrl().contains("/login"), "Failed to login.");
    }

    @Test
    @Description("Checking authorization with empty email and password fields")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Authorization with empty fields")
    public void emptyFieldsAuthorization() {
        BaseTest.authPage
                .openAuthorizationPage()
                .login("", "");

        Assert.assertTrue(BaseTest.authPage.getErrorMessage().equals("Required"), "The error message does not match what was expected.");
    }

    @Test
    @Description("Checking email input without @")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Entering email without @")
    public void emailFormatWithoutAt() {
        BaseTest.authPage
                .openAuthorizationPage()
                .enterEmail("www.ww");

        BaseTest.actions.click().perform();

        Assert.assertTrue(BaseTest.authPage.getErrorMessage().equals("Invalid email address"), "The error message does not match what was expected.");
    }
}
