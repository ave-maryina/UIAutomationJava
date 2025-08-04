import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ActionAlertIframeTest extends BaseTest {
    private static class Locators {
        private static By aqaPractice = By.xpath("//*[text()='AQA Practice']");
        private static By actionsAlertsIframes = By.xpath("//*[text()='Actions, Alerts & Iframes']");
        private static By confirmButton = By.xpath("//button[text()='Confirm']");
        private static By getDiscont = By.xpath("//button[text()='Get Discount']");
        private static By iframe = By.xpath("//iframe");
        private static By results = By.xpath("//span[contains(text(), 'Results: ')]");
    }

    @Test
    public void actionAlertIframeTest() throws InterruptedException {
        actions.moveToElement(driver.findElement(Locators.aqaPractice)).perform();
        driver.findElement(Locators.actionsAlertsIframes).click();
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.iframe));
        driver.switchTo().frame(iframe);
        driver.findElement(Locators.confirmButton).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText()
                        .equals("You have called alert!"),
                "Alert message not displayed!");
        alert.accept();
        driver.switchTo().frame(iframe);
        driver.findElement(Locators.getDiscont).click();
    }
}
