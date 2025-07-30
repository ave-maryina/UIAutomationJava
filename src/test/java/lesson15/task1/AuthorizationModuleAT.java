package lesson15.task1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.driver.DriverSetUp;
import utils.urls.Links;

import java.time.Duration;

public class AuthorizationModuleAT {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverSetUp.getDriver();
        driver.get(Links.ANDERSEN_lAB_AUTHORIZATION_PAGE.getLink());
        By pass = By.name("password");
        By email = By.name("email");

        //Authorization with walid data
        WebDriverWait wail = new WebDriverWait(driver, Duration.ofSeconds(10));
        wail.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys("test@example.com");
        wail.until(ExpectedConditions.visibilityOfElementLocated(pass)).sendKeys("Qwerty123");
        wail.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type=\"submit\"]"))).click();
        Thread.sleep(3000);

        driver.navigate().refresh();

        //Email format: no @
        wail.until(ExpectedConditions.visibilityOfElementLocated(email)).sendKeys(" www.ww");
        driver.findElement(pass).click();
        Thread.sleep(3000);
        driver.quit();
    }
}
