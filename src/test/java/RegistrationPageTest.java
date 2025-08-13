import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import jdk.jfr.Description;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.randomEmailGenerator.RandomEmailGenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Epic("Registration page authentication")
public class RegistrationPageTest extends BaseTest {
    @Test
    @Description("Checking failed registration with all fields empty")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Registration with empty fields")
    public void emptyFieldsRegistration() {
        regisPage
                .openRegistrationPage().enterFirstName("").enterLastName("").enterDateOfBirth("");

        actions.click().perform();

        regisPage
                .enterEmail("").enterPassword("").enterConfPass("").clickSubmit();

        Assert.assertTrue(regisPage.getErrorMessage().contains("Required"), "The error message does not match what was expected.");
    }

    @Test
    @Description("Checking successful registration with all valid data, where First name consists of the minimum allowed length of 2 characters")
    @Severity(SeverityLevel.NORMAL)
    @Story("Registration with minimum length First name 2 characters")
    public void firstNameMinLengthRegistration() {
        regisPage
                .openRegistrationPage().enterFirstName("Na").enterLastName("LastName").enterDateOfBirth("03/19/1996");

        actions.click().perform();

        regisPage
                .enterEmail(RandomEmailGenerator.generateEmail()).enterPassword("12345qwert").enterConfPass("12345qwert").clickSubmit();

        wait.until(ExpectedConditions.urlContains("/login"));

        Assert.assertFalse(driver.getCurrentUrl().contains("/registration"), "Failed registration.");
    }

    @Test
    @Description("Checking that a field 'Date of birth' returns a valid date entered in dd/MM/yyyy format")
    @Severity(SeverityLevel.NORMAL)
    @Story("Valid date input to the 'Date of birth' field")
    public void dateOfBirthInputTest() {
        String dateOfBirth = LocalDate.now()
                .minusYears(20)
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        regisPage
                .openRegistrationPage()
                .enterDateOfBirth(dateOfBirth);

        actions.click().perform();

        Assert.assertEquals(regisPage.getDateOfBirthValue(), dateOfBirth,
                "The date of birth input value does not match what was typed.");
    }

    @Test
    @Description("Checking registration with already registered email")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Registration with already registered email")
    public void alreadyRegisteredEmail() {
        regisPage
                .openRegistrationPage().enterFirstName("Name").enterLastName("LastName").enterDateOfBirth("03/04/2000");

        actions.click().perform();

        regisPage
                .enterEmail("sample@gmail.com").enterPassword("123qwer!").enterConfPass("123qwer!").clickSubmit();

        Assert.assertTrue(regisPage.getErrorMessage().equals("Email is already registered"), "The error message does not match what was expected.");
    }

    @Test
    @Description("Checking registration with password less than minimum length(7 characters)")
    @Severity(SeverityLevel.NORMAL)
    @Story("Registration with a 7-character password")
    public void passwordMinLengthMinusOne() {
        regisPage.openRegistrationPage()
                .enterPassword("1234567");

        actions.click().perform();

        Assert.assertTrue(regisPage.getErrorMessage().equals("Minimum 8 characters"), "The error message does not match what was expected.");
    }

    @Test
    @Description("Checking that an error message is displayed if the 'Password' and 'Confirm password' fields do not match")
    @Severity(SeverityLevel.CRITICAL)
    @Story("The input fields 'Password' and 'Confirm password' do not match")
    public void confirmPasswordDoesNotMuchPassword() {
        regisPage.openRegistrationPage()
                .enterPassword("12345678")
                .enterConfPass("123456789");

        actions.click().perform();
        Assert.assertTrue(regisPage.getErrorMessage().equals("Passwords must match"), "The error message does not match what was expected.");
    }

    @Test
    @Description("Checking that clicking the 'Sing in' link leads to the Authorization page")
    @Severity(SeverityLevel.CRITICAL)
    @Story("'Sing in' link leads to the Authorization page")
    public void singInLinkLeadsToSpecifiedPage() {
        regisPage
                .openRegistrationPage()
                .clickSingIn();

        actions.pause(1000).build().perform();

        Assert.assertTrue(driver.getCurrentUrl().contains("/login"), "The link does not lead to the login page.");
    }
}
