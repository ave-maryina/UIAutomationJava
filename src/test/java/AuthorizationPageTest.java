
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthorizationPageTest extends BaseTest {
    private static AuthorizationPage authPage;

    @Test
    public void walidDataAuthorization() {
        authPage
                .openAuthorizationPage()
                .login("test@example.com", "Qwerty123");
        actions.pause(3000).perform();
        Assert.assertFalse(driver.getCurrentUrl().contains("/login"), "Failed to login.");
    }

    @Test
    public void emptyFieldsAuthorization() {
        authPage
                .openAuthorizationPage()
                .login("", "");
        actions.pause(3000).perform();
        Assert.assertTrue(authPage.getErrorMessage().equals("Required"), "The error message does not match what was expected.");


    }

    @Test
    public void emailFormatWithoutAt() {
        authPage
                .openAuthorizationPage()
                .enterEmail(" www.ww");
        Assert.assertTrue(authPage.getErrorMessage().equals("Invalid email address"), "The error message does not match what was expected.");
    }
}
