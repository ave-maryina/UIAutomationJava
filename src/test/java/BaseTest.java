import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import utils.driver.DriverSetUp;

import java.time.Duration;

public class BaseTest {
    static WebDriver driver;
    static WebDriverWait wait;
    static Actions actions;

    @BeforeClass
    public void varInit() {
        driver = DriverSetUp.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    @AfterSuite
    public void closeDriver() {
        driver.quit();
    }
}
