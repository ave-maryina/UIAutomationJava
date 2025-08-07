import driver.DriverSetUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {
    static WebDriver driver;
    static WebDriverWait wait;
    static Actions actions;
    static AuthorizationPage authPage;
    static RegistrationPage regisPage;

    @BeforeClass
    public void varInit() {
        driver = DriverSetUp.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        authPage = new AuthorizationPage(driver);
        regisPage = new RegistrationPage(driver);
        System.out.println(authPage.toString());
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }
}
