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
        private static By cancelCourse = By.xpath("//button[text()='Cancel course']");
        private static By iframe = By.xpath("//iframe");
        private static By resultsMessage = By.xpath("//span[@class='font-light flex']");
    }

    @Test
    public void actionAlertIframeTest() {
        actions.moveToElement(driver.findElement(Locators.aqaPractice)).perform();
        driver.findElement(Locators.actionsAlertsIframes).click();
        WebElement iframe = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.iframe));
        driver.switchTo().frame(iframe);
        driver.findElement(Locators.confirmButton).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText()
                        .equals("You have called alert!"),
                "Alert message doesn't match.");
        alert.accept();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframe);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.resultsMessage)).getText()
                .equals("Congratulations, you have successfully enrolled in the course!"), "Result message doesn't match");
        actions.doubleClick(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.getDiscont))).perform();
        alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText()
                .equals("Are you sure you want to apply the discount?"), "Alert message doesn't match.");
        alert.accept();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframe);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.resultsMessage)).getText()
                .equals("You received a 10% discount on the second course."), "Result message doesn't match");
        actions.contextClick(driver.findElement(Locators.cancelCourse)).perform();
        alert = driver.switchTo().alert();
        alert.sendKeys("Test");
        Assert.assertTrue(alert.getText().equals("Here you may describe a reason why you are cancelling your " +
                "registration (or leave this field empty)."), "Alert message doesn't match.");
        alert.accept();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iframe);
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.resultsMessage)).getText()
                .contains("Test"), "The message result does not contain the entered word.");
    }
}
