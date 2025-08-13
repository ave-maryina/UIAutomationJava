import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import pages.AuthorizationPage;
import pages.RegistrationPage;
import utils.driver.DriverSetUp;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    static WebDriver driver;
    static WebDriverWait wait;
    static Actions actions;
    static AuthorizationPage authPage;
    static RegistrationPage regisPage;

    @BeforeClass
    public void varInit() {
        driver = DriverSetUp.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        authPage = new AuthorizationPage(driver);
        regisPage = new RegistrationPage(driver);
    }

    @AfterMethod
    public void makeScreenIfTestFailed(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(DriverSetUp.getDriver(), result.getMethod().getMethodName());
        }
    }

    @AfterClass
    public void closeDriver() {
        driver.quit();
    }

    public static void takeScreenshot(WebDriver driver, String methodName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);

        try {
            String screenPath = "target/allure-results/screenshot-" + methodName + ".png";
            FileUtils.copyFile(source, new File(screenPath));

            Allure.addAttachment("Screenshot for " + methodName, new FileInputStream(screenPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
