import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Links;

import java.time.Duration;

public class AuthorizationPage {
    private static WebDriver driver;
    private static WebDriverWait wait;

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


    public AuthorizationPage openAuthorizationPage() {
        driver.get(Links.ANDERSEN_lAB_AUTHORIZATION_PAGE.getLink());
        return this;
    }

    public AuthorizationPage enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);
        return this;
    }

    public AuthorizationPage enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        return this;
    }

    public void clickSubmit() {
        signInButton.click();
    }

    public RegistrationPage clickRegistrationLink() {
        registrationLink.click();
        return new  RegistrationPage(driver);
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
    }

    public void login(String email, String password) {
        enterEmail(email).enterPassword(password).clickSubmit();
    }
}
