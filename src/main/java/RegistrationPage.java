import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Links;

import java.time.Duration;

public class RegistrationPage {
    private static WebDriver driver;
    private static WebDriverWait wait;

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

    public RegistrationPage openRegistrationPage() {
        driver.get(Links.ANDERSEN_lAB_REGISTRATION_PAGE.getLink());
        return this;
    }

    public RegistrationPage enterFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(firstNameField)).sendKeys(firstName);
        return this;
    }

    public RegistrationPage enterLastName(String lastName) {
        wait.until(ExpectedConditions.visibilityOf(lastNameField)).sendKeys(lastName);
        return this;
    }


    public RegistrationPage enterDateOfBirth(String dateOfBirth) {
        wait.until(ExpectedConditions.visibilityOf(dateOfBirthField)).sendKeys(dateOfBirth);
        return this;
    }

    public RegistrationPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
        return this;
    }

    public RegistrationPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        return this;
    }

    public RegistrationPage enterConfPass(String confPass) {
        wait.until(ExpectedConditions.visibilityOf(passConformField)).sendKeys(confPass);
        return this;
    }

    public void clickSubmit() {
        submitButton.click();
    }

    public AuthorizationPage clickSignIn() {
        singInLink.click();
        return new AuthorizationPage(driver);
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }

    public void registration(String firstName, String lastName, String dateOfBirth, String email, String password, String confirmPass) {
        enterEmail(email).enterPassword(password).clickSubmit();
    }
}
