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

public class AuthorizationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    protected Logger logger = LogManager.getLogger(this.getClass());

    @FindBy(name = "email")
    private WebElement emailField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//span[@class = 'absolute right-0 text-rose-500 text-sm']")
    private WebElement errorMessage;

    @FindBy(xpath = "//*[text() = 'Registration']")
    private WebElement registrationLink;

    public AuthorizationPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @Step("Open Authorization page")
    public AuthorizationPage openAuthorizationPage() {
        driver.get(Links.ANDERSEN_lAB_AUTHORIZATION_PAGE.getLink());
        logger.info("I opened " + Links.ANDERSEN_lAB_AUTHORIZATION_PAGE.getLink());
        return this;
    }

    @Step("Set email")
    public AuthorizationPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
        logger.info("I set email: " + email);
        return this;
    }

    @Step("Set password")
    public AuthorizationPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        logger.info("I set password: " + password);
        return this;
    }

    @Step("Click Submit")
    public void clickSubmit() {
        signInButton.click();
        logger.info("I clicked Submit");
    }

    @Step("Click on the Registration link")
    public RegistrationPage clickRegistrationLink() {
        registrationLink.click();
        logger.info("I clicked on the Registration link");
        return new RegistrationPage(driver);
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

    public void login(String email, String password) {
        enterEmail(email).enterPassword(password).clickSubmit();
        logger.error("Trying to login with email: " + email + " and password: " + password);
    }
}
