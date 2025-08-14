package selenideStepDefinition;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import utils.urls.Links;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationPageStepDefinitions {
    private static final SelenideElement emailInput = $(By.name("email"));
    private static final SelenideElement passwordInput = $(By.name("password"));
    private static final SelenideElement signInButton = $(By.xpath("//button[@type='submit']"));
    private static final SelenideElement registrationLink = $(By.xpath("//*[text() = 'Registration]"));
    private static final SelenideElement errorMessage = $(By.xpath("//span[@class = 'absolute right-0 text-rose-500 text-sm']"));
    private static final SelenideElement signInTitle = $(By.xpath("//h1"));

    protected Logger logger = LogManager.getLogger(this.getClass());


    @When("Opening Authorization page")
    public void opening_authorization_page() {
        open(Links.ANDERSEN_lAB_AUTHORIZATION_PAGE.getLink());
        logger.info("opened link is: {}", Links.ANDERSEN_lAB_AUTHORIZATION_PAGE.getLink());
    }

    @When("Set invalid Email {}")
    public void set_invalid_email_nane_surname_gmail(String email) {
        emailInput.setValue(email);
        signInTitle.click();
        logger.info("user's email is {}", email);
    }

    @When("Check that error message is {}")
    public void check_error_message(String errorMess) {
            errorMessage.shouldBe(Condition.text(errorMess));
            logger.info("error message is: {}", errorMessage.text());
    }
}
