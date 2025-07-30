package lesson15.task1;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.driver.DriverSetUp;
import utils.urls.Links;

import java.time.Duration;

public class RegistrationModuleAT {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver;
        driver = DriverSetUp.getDriver();
        driver.get(Links.ANDERSEN_lAB_REGISTRATION_PAGE.getLink());

        //emptyFieldsRegistration
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.
                visibilityOfElementLocated(By.xpath("//button[text()=\"Submit\"]"))).click();
        Thread.sleep(3000);

        //notValidDateFormat
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(Links.ANDERSEN_lAB_REGISTRATION_PAGE.getLink());
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.
                visibilityOfElementLocated(By.name("dateOfBirth"))).sendKeys("30/04/1990" + Keys.ENTER);
        Thread.sleep(3000);
        driver.quit();
    }
}
