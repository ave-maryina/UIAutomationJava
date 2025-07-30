package lesson15.task4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.driver.DriverSetUp;
import utils.urls.Links;

import java.time.Duration;

public class RegisteredUserLogin {
    public static void main(String[] args) {
        userLogin();
    }

    public static void userLogin() {
        WebDriver driver = DriverSetUp.getDriver();
        driver.get(Links.ANDERSEN_lAB_AUTHORIZATION_PAGE.getLink());
        By pass = By.name("password");
        By email = By.name("email");
        WebDriverWait wail = new WebDriverWait(driver, Duration.ofSeconds(10));
        wail.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys("test@example.com");
        wail.until(ExpectedConditions.visibilityOfElementLocated(pass)).sendKeys("Qwerty123");
        wail.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type=\"submit\"]"))).click();
    }
}
