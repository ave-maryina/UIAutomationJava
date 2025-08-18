import driver.AppiumDriverInit;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import pages.AndroidPage;

import java.util.*;

public class AndroidAppTest {
    AppiumDriver driver;
    AndroidPage androidPage;

    @BeforeMethod
    public void setUp() {
        driver = new AppiumDriverInit().getDriver();
        androidPage = new AndroidPage(driver);
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }

    @Test
    public void testNumberOfButtonsOnViews() {
        androidPage.clickOnViews();
        Assert.assertTrue(androidPage.getAllButtonsNames().size() == 42, "The number of 'button' elements does not match the expected number!");
    }

    @Test
    public void setDateAndTime() throws InterruptedException {
        androidPage.clickOnViews();
        androidPage.clickOnDataWidgets();
        androidPage.clickOnFirstDialod();
        androidPage.clickOnChangeDate();

        String tomorrowDate = LocalDate.now().plusDays(1)
                .format(DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale.ENGLISH));

        String dateCheck = LocalDate.now().plusDays(1)
                .format(DateTimeFormatter.ofPattern("M-d-yyyy"));

        if (LocalDate.now().plusDays(1).getDayOfMonth() == 1) {
            androidPage.clickOnNextMonthButton();
        }

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated
                (AppiumBy.xpath("//android.view.View[@content-desc=\"" + tomorrowDate + "\"]"))).click();

        androidPage.clickOk();
        androidPage.clickOnChangeTimeSpinner();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence tap = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 327, 1028))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        for (int i = 0; i < 3; i++) {
            Thread.sleep(100);
            driver.perform(Collections.singletonList(tap));
        }

        Sequence tap1 = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 540, 1340))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        for (int i = 0; i < 9; i++) {
            driver.perform(Collections.singletonList(tap1));
        }

        Sequence tap2 = new Sequence(finger, 0)
                .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), 738, 1340))
                .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tap2));

        androidPage.clickOk();

        Assert.assertTrue(driver.findElement(By.id("io.appium.android.apis:id/dateDisplay")).getText().equals(dateCheck + " 23:11"));
    }

    @Test
    public void checkNextButtonFunctionality() {
        androidPage.clickOnViews();
        androidPage.scrollUntilTextSwitcherClick();
        for (int i = 0; i < 7; i++) {
            androidPage.clickNext();
        }
        Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text=\"7\"]")).isDisplayed());
    }
}
