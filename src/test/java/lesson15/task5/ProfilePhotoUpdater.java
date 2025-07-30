package lesson15.task5;

import lesson15.task4.RegisteredUserLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.driver.DriverSetUp;

import java.time.Duration;

public class ProfilePhotoUpdater {
    public static void main(String[] args) {
        try {
            updatePhoto("C:\\Users\\user\\Pictures\\dog.jpg");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updatePhoto(String photoPath) throws InterruptedException {
        RegisteredUserLogin.userLogin();
        WebDriver driver = DriverSetUp.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        Thread.sleep(3000);
        WebElement pic = driver.findElement(By.xpath("//div[@class='relative w-[95px] h-[95px] cursor-pointer group']"));
        Actions action = new Actions(driver);
        action.moveToElement(pic).perform();
        Thread.sleep(3000);
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("input[type='file']")));
        fileInput.sendKeys(photoPath);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Close']"))).click();
        driver.quit();
    }
}
