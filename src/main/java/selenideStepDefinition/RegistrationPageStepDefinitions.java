package selenideStepDefinition;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.urls.Links;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static utils.randomEmailGenerator.RandomEmailGenerator.generateEmail;

public class RegistrationPageStepDefinitions {
    private static final SelenideElement firstNameInput = $(By.name("firstName"));
    private static final SelenideElement lastNameInput = $(By.name("lastName"));
    private static final SelenideElement dateOfBirthInput = $(By.name("dateOfBirth"));
    private static final SelenideElement emailInput = $(By.name("email"));
    private static final SelenideElement passwordInput = $(By.name("password"));
    private static final SelenideElement passwordConfirmInput = $(By.name("passwordConfirmation"));
    private static final SelenideElement submitButton = $(By.xpath("//button[@type='submit']"));
    private static final SelenideElement errorMessage = $(By.xpath("//span[@class = 'absolute right-0 text-rose-500 text-sm']"));
    private static final SelenideElement singInLink = $(By.xpath("//*[text() = 'Sing in']"));
    private static final SelenideElement getDateOfBirthLabel = $(By.xpath("//label[contains(., 'First Name')]"));
    private static final SelenideElement tittle = $(By.cssSelector("h1"));

    protected Logger logger = LogManager.getLogger(this.getClass());

    @Given("Opening Registration page")
    public void opening_registration_page() {
        open(Links.ANDERSEN_lAB_REGISTRATION_PAGE.getLink());
        logger.info("opened link is: {}", Links.ANDERSEN_lAB_REGISTRATION_PAGE.getLink());
    }

    @When("Set First name {}")
    public void set_first_name(String firstName) {
        firstNameInput.setValue(firstName);
        logger.info("user's first name is {}", firstName);
    }

    @And("Set Last name {}")
    public void set_last_name(String lastName) {
        lastNameInput.setValue(lastName);
        logger.info("user's last name is {}", lastName);
    }

    @And("Set Date of birth {}")
    public void set_date_of_birth(String dateOfBirth) {
        dateOfBirthInput.setValue(dateOfBirth);
        logger.info("user's date of birth name is {}", dateOfBirth);
    }

    @And("Close pop up calendar")
    public void close_pop_up_calendar() {
        $(getDateOfBirthLabel).click();
        logger.info("pop up calendar is closed");
    }

    @And("Set valid Email")
    public void set_valid_email() {
        String validEmail = generateEmail();
        emailInput.setValue(validEmail);
        logger.info("user's email is {}", validEmail);
    }

    @And("Set Password {}")
    public void set_password(String password) {
        passwordInput.setValue(password);
        logger.info("user's password is {}", password);
    }

    @And("Set Confirm password {}")
    public void set_confirm_password(String confirmPass) {
        passwordConfirmInput.setValue(confirmPass);
        $(getDateOfBirthLabel).click();
        logger.info("user's confirm password is {}", confirmPass);
    }

    @And("Click Submit button")
    public void click_submit_button() {
        submitButton.click();
    }

    @Then("Check  title contains {}")
    public void check_tittle_contains(String path) {
        tittle.shouldHave(text(path));
    }
}


