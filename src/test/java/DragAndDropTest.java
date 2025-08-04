import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.time.Duration;

public class DragAndDropTest extends BaseTest {
    private static class Locators {
        private static By aqaPractice = By.xpath("//*[text()='AQA Practice']");
        private static By dragAndDrop = By.xpath("//*[text()='Drag & Drop']");
        private static By writeTextCases = By.xpath("//*[text()='Write test cases']");
        private static By testingRequirements = By.xpath("//*[text()='Testing requirements']");
        private static By writeAutScripts = By.xpath("//*[text()='Write automation scripts']");
        private static By frameworkSetUp = By.xpath("//*[text()='Framework set up']");
        private static By manualWorkLeft = By.id("target-manual1");
        private static By manualWorkRight = By.id("target-manual2");
        private static By automationWorkLeft = By.id("target-auto1");
        private static By automationWorkRight = By.id("target-auto2");
        private static By message = By.xpath("//*[contains(@class, 'text-lg flex absolute')]");
    }

    @Test
    public void selectCourseTestNegative() {
        actions.moveToElement(driver.findElement(Locators.aqaPractice)).perform();
        driver.findElement(Locators.dragAndDrop).click();
        actions.moveToElement(driver.findElement(Locators.writeTextCases)).clickAndHold().moveToElement(driver
                .findElement(Locators.manualWorkLeft)).release().pause(Duration.ofSeconds(1)).build().perform();
        actions.moveToElement(driver.findElement(Locators.testingRequirements)).clickAndHold().moveToElement(driver
                .findElement(Locators.manualWorkRight)).release().pause(Duration.ofSeconds(1)).build().perform();
        actions.moveToElement(driver.findElement(Locators.writeAutScripts)).clickAndHold().moveToElement(driver
                .findElement(Locators.automationWorkLeft)).release().pause(Duration.ofSeconds(1)).build().perform();
        actions.moveToElement(driver.findElement(Locators.frameworkSetUp)).clickAndHold().moveToElement(driver
                .findElement(Locators.automationWorkRight)).release().pause(Duration.ofSeconds(1)).build().perform();
    }
}
