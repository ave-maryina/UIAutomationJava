import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationPageTest extends BaseTest {
    @Test
    public void emptyFieldsRegistration() {
        regisPage
                .openRegistrationPage().enterFirstName("").enterLastName("").enterDateOfBirth("");
        actions.click().perform();
        regisPage
                .enterEmail("").enterPassword("").enterConfPass("").clickSubmit();
        actions.pause(3000).build().perform();
        Assert.assertTrue(regisPage.getErrorMessage().contains("Required"), "The error message does not match what was expected.");
    }

    @Test
    public void firstNameMinLengthRegistration() {
        regisPage
                .openRegistrationPage().enterFirstName("Na").enterLastName("LastName").enterDateOfBirth("03/19/1996");
        actions.click().perform();
        regisPage
                .enterEmail(RandomEmailGenerator.generateEmail()).enterPassword("12345qwert").enterConfPass("12345qwert").clickSubmit();
        actions.pause(3000).build().perform();
        Assert.assertFalse(driver.getCurrentUrl().contains("/registration"), "Failed registration.");
    }

    @Test
    public void firstNameOneCharRegisNegative() {
        regisPage
                .openRegistrationPage().enterFirstName("S").enterLastName("LastName").enterDateOfBirth("03/19/1996");
        actions.click().perform();
        regisPage
                .enterEmail(RandomEmailGenerator.generateEmail()).enterPassword("12345qwert").enterConfPass("12345qwert").clickSubmit();
        actions.pause(3000).perform();
        Assert.assertTrue(authPage.getErrorMessage().equals("Minimum 2 characters"), "Failed registration.");
    }

    @Test
    public void notValidDateFormat() {
        regisPage
                .openRegistrationPage()
                .enterDateOfBirth("30/04/1990");
        actions.click().pause(3000).build().perform();
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
        actions.click().pause(3000).build().perform();
        Assert.assertTrue(authPage.getErrorMessage().equals("Passwords must match"), "The error message does not match what was expected.");
    }

    @Test
    public void singInLinkLeadsToSpecifiedPage() {
        regisPage
                .openRegistrationPage()
                .clickSignIn();
        actions.pause(3000).perform();
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"), "The link does not lead to the login page.");
    }
}
