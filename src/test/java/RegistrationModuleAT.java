import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationModuleAT extends BaseTest {

    @Test
    public void emptyFieldsRegistration() {
        regisPage
                .registration("", "", "", "", "", "");
        actions.pause(3000).perform();
        Assert.assertTrue(regisPage.getErrorMessage().contains("Required"), "The error message does not match what was expected.");
    }

    @Test
    public void firstNameMinLengthRegistration() {
        regisPage
                .registration("Na", "LastName", "03/19/1996", "testemail123@example.com", "12345qwert", "Method…");
        actions.pause(3000).perform();
        Assert.assertFalse(driver.getCurrentUrl().contains("/login"), "Failed registration.");
    }

    @Test
    public void firstNameOneCharRegisNegative() {
        regisPage
                .openRegistrationPage()
                .registration("S", "LastName", "03/19/1996", "test123email123@example.com", "12345qwert", "12345qwert");
        actions.pause(3000).perform();
        Assert.assertFalse(driver.getCurrentUrl().contains("/registration"), "Failed registration.");
    }

    @Test
    public void notValidDateFormat() {
        regisPage
                .openRegistrationPage()
                .enterDateOfBirth("30/04/1990");
        actions.pause(3000).perform();
        Assert.assertTrue(authPage.getErrorMessage().equals("Not valid date format"), "The error message does not match what was expected.");
    }

    @Test
    public void alreadyRegisteredEmail() {
        regisPage
                .openRegistrationPage()
                .enterEmail("sample@gmail.com");

        actions.pause(3000).perform();
        Assert.assertTrue(authPage.getErrorMessage().equals("Email is already registered"), "The error message does not match what was expected.");
    }

    @Test
    public void confirmPasswordDoesNotMuchPassword() {
        regisPage.openRegistrationPage()
                .enterPassword("12345678")
                .enterConfPass("123456789");
        actions.pause(3000).perform();
        Assert.assertTrue(authPage.getErrorMessage().equals("Passwords must match"), "The error message does not match what was expected.");
    }

    @Test
    public void SingInLinkLeadsToSpecifiedPage() {
        regisPage
                .openRegistrationPage()
                .clickSignIn();
        actions.pause(3000).perform();
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"), "The link does not lead to the login page.");
    }
}
