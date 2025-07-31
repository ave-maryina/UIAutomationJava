package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.driver.DriverSetUp;
import utils.urls.Links;

import java.time.Duration;

public class LoginParametersTest {

    @Test
    @Parameters({"email", "password"})
    public void testParamLogin(String email, String password) {
        WebDriver driver = DriverSetUp.getDriver();
        driver.get(Links.ANDERSEN_lAB_AUTHORIZATION_PAGE.getLink());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type=\"submit\"]")))
                .click();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//div[contains(text(), 'Sign Out')]"))).isDisplayed(), "Login failed for user: "
                + email);
    }
}
