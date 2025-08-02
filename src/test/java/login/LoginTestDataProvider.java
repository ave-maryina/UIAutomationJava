package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.automation.utils.driver.DriverSetUp;
import org.automation.utils.urls.Links;

import java.time.Duration;

public class LoginTestDataProvider {
    static WebDriver driver;
    static WebDriverWait wait;

    @BeforeClass
    public void varInit() {
        driver = DriverSetUp.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

    @DataProvider(name = "users")
    public Object[][] getUsers() {
        return new Object[][]{
                {"test@example.com", "Qwerty123"},
                {"test@exam.com", "12345678"},
                {"tester@exam.com", "1234qwer"}
        };
    }

    @Test(dataProvider = "users")
    public void testLogin(String email, String password) throws InterruptedException {
        driver.get(Links.ANDERSEN_lAB_AUTHORIZATION_PAGE.getLink());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type=\"submit\"]")))
                .click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//div[contains(text(), 'Sign Out')]"))).isDisplayed(), "Login failed for user: "
                + email);
    }
}