import driver.AppiumDriverInit;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

        androidPage.clickOnTomorrowDate(tomorrowDate);
        androidPage.clickOk();
        androidPage.clickOnChangeTimeSpinner();
        androidPage.chooseElevenHours();
        androidPage.chooseElevenMinutes();
        androidPage.choosePm();
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
