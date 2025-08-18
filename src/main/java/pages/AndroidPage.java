package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AndroidPage {
    private AppiumDriver driver;
    private WebDriverWait wait;
    protected Logger logger = LogManager.getLogger(this.getClass());

    public AndroidPage(AppiumDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private static final class Locators {
        private static final By views = AppiumBy.accessibilityId("Views");
        private static final By dataWidgets = AppiumBy.xpath("//android.widget.TextView[@content-desc='Date Widgets']");
        private static final By firstDialog = AppiumBy.xpath("//android.widget.TextView[@content-desc='1. Dialog']");
        private static final By changeDateButton = AppiumBy.xpath("//android.widget.Button[@content-desc='change the date']");
        private static final By changeTimeSpinner = AppiumBy.xpath("//android.widget.Button[@content-desc='change the time (spinner)']");
        private static final By okButton = AppiumBy.id("android:id/button1");
        private static final By nextMonthButton = AppiumBy.id("android:id/next");
        private static final By nextButton = AppiumBy.id("io.appium.android.apis:id/next");
        private static final By tapCounterNextButton = AppiumBy.id("android:id/next");
    }

    public void clickOnViews() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.views)).click();
        logger.info("I clicked on Views");
    }

    public void clickOnDataWidgets() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.dataWidgets)).click();
        logger.info("I clicked on Data Widgets");
    }

    public void clickOnFirstDialod() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.firstDialog)).click();
        logger.info("I clicked on 1.Dialod");
    }

    public void clickOnChangeDate() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.changeDateButton)).click();
        logger.info("I clicked on change the date");
    }

    public void clickOk() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.okButton)).click();
        logger.info("I clicked Ok");
    }

    public void clickOnChangeTimeSpinner() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.changeTimeSpinner)).click();
        logger.info("I clicked on the change the time(spinner)");
    }

    public void clickOnNextMonthButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.nextMonthButton)).click();
        logger.info("I clicked on the Next month button");
    }

    public void setDate() {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.changeDateButton)).click();
    }

    public void setTimeBySpinner() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.changeDateButton)).click();
    }

    public void clickNext() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.nextButton)).click();
        logger.info("I clicked on the next button");
    }

    public Set<String> getAllButtonsNames() {
        Set<String> allButtonsNames = new HashSet<>();
        String lastElementText = "";

        while (true) {
            List<WebElement> currentButtons = driver.findElements(
                    AppiumBy.id("android:id/text1")
            );

            List<String> currentNames = currentButtons.stream()
                    .map(WebElement::getText)
                    .filter(text -> !text.isEmpty())
                    .collect(Collectors.toList());

            if (currentButtons.isEmpty() ||
                    currentButtons.get(currentButtons.size() - 1).getText().equals(lastElementText)) {
                break;
            }

            allButtonsNames.addAll(currentNames);
            lastElementText = currentButtons.get(currentButtons.size() - 1).getText();

            driver.findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(1)"
            ));
        }
        return allButtonsNames;
    }

    public void scrollUntilTextSwitcherClick() {
        WebElement element = driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))" +
                ".scrollIntoView(new UiSelector().text(\"TextSwitcher\"))"));
        logger.info("I scrolled down to TextSwitcher");
        element.click();
        logger.info("I clicked on the TextSwitcher");
    }
}
