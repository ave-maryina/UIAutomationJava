package pages;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.urls.Links;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    protected Logger logger = LogManager.getLogger(this.getClass());

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "firstName")
    private WebElement firstNameField;

    @FindBy(name = "lastName")
    private WebElement lastNameField;

    @FindBy(name = "dateOfBirth")
    private WebElement dateOfBirthField;

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "passwordConfirmation")
    private WebElement passConformField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//span[@class = 'absolute right-0 text-rose-500 text-sm']")
    private WebElement errorMessage;

    @FindBy(xpath = "//*[text() = 'Sing in']")
    private WebElement singInLink;

    @Step("Open Registration page")
    public RegistrationPage openRegistrationPage() {
        driver.get(Links.ANDERSEN_lAB_REGISTRATION_PAGE.getLink());
        logger.info("I opened " + Links.ANDERSEN_lAB_REGISTRATION_PAGE.getLink());
        return this;
    }

    @Step("Set First name")
    public RegistrationPage enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField)).sendKeys(firstName);
        logger.info("I set First name: " + firstName);
        return this;
    }

    @Step("Set Last Name")
    public RegistrationPage enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameField)).sendKeys(lastName);
        logger.info("I set Last name: " + lastName);
        return this;
    }

    @Step("Set Date of birth")
    public RegistrationPage enterDateOfBirth(String dateOfBirth) {
        wait.until(ExpectedConditions.visibilityOf(dateOfBirthField)).sendKeys(dateOfBirth);
        logger.info("I set Date of birth: " + dateOfBirth);
        return this;
    }

    @Step("Get Date of birth value")
    public String getDateOfBirthValue() {
        String dateOfBirth = dateOfBirthField.getAttribute("value");
        logger.info("I have got Date of birth: " + dateOfBirth);
        return dateOfBirth;
    }

    @Step("Set Email")
    public RegistrationPage enterEmail(String email) {
        logger.warn("Trying to use a valid email address");
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
        logger.info("I set email: " + email);
        return this;
    }

    @Step("Set Password")
    public RegistrationPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        logger.info("I set Password: " + password);
        return this;
    }

    @Step("Set Confirm password")
    public RegistrationPage enterConfPass(String confPass) {
        wait.until(ExpectedConditions.visibilityOf(passConformField)).sendKeys(confPass);
        logger.info("I set Confirm password: " + confPass);
        return this;
    }

    @Step("Click Submit")
    public void clickSubmit() {
        submitButton.click();
        logger.info("I clicked Submit");
    }

    @Step("Click to the Sing in link")
    public AuthorizationPage clickSingIn() {
        singInLink.click();
        logger.info("I clicked 'Sigh in'");
        return new AuthorizationPage(driver);
    }

    @Step("Get error message")
    public String getErrorMessage() {
        String errorMess = "";
        try {
            errorMess = wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
            logger.info("Error message is: " + errorMess);
        } catch (Exception e) {
            logger.error("No error message shown");
        }
        return errorMess;
    }

    public void registration(String firstName, String lastName, String dateOfBirth, String email, String password, String confirmPass) {
        enterFirstName(firstName).enterLastName(lastName).enterDateOfBirth(dateOfBirth)
                .enterEmail(email).enterPassword(password).enterConfPass(confirmPass).clickSubmit();
    }
}
