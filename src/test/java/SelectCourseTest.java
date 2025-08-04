import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.format.DateTimeFormatter;

public class SelectCourseTest extends BaseTest {

    static class Locators {
        private static By aqaPractice = By.xpath("//*[text()='AQA Practice']");
        private static By selectPractice = By.xpath("//*[text()='Select']");
        private static By selectCountry = By.xpath("//*[@title='Select country']");
        private static By selectLanguage = By.xpath("//*[@title='Select language']");
        private static By selectType = By.xpath("//*[@title='Select type']");
        private static By startDate = By.xpath("//*[@title='Start date']");
        private static By endDate = By.xpath("//*[@title='End date']");
        private static By aqaPython = By.xpath("//*[text()='AQA Python']");
        private static By aqaJava = By.xpath("//*[text()='AQA Java']");
        private static By searchButton = By.xpath("//button[text()='Search']");
        private static By message = By.xpath("//h2[text()='Unfortunately, we did not find any courses matching your chosen criteria.']");
    }

    @Test
    public void selectCourseTestNegative() {
        actions.moveToElement(driver.findElement(Locators.aqaPractice)).perform();
        driver.findElement(Locators.selectPractice).click();
        actions.sendKeys(driver.findElement(Locators.selectCountry), "USA").click().build().perform();
        actions.sendKeys(driver.findElement(Locators.selectLanguage), "English").click().build().perform();
        actions.sendKeys(driver.findElement(Locators.selectType), "Testing").click().build().perform();
        actions.sendKeys(driver.findElement(Locators.startDate), DateGenerator.getNextMonday().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))).perform();
        actions.sendKeys(driver.findElement(Locators.endDate), DateGenerator.twoWeeksAfterNextMonday().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))).perform();
        actions.clickAndHold(driver.findElement(Locators.aqaPython)).moveToElement(driver.findElement(Locators.aqaJava))
                .release().click(driver.findElement(Locators.searchButton)).build().perform();
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.message)).isDisplayed(), "Message not displayed!");
    }
}
