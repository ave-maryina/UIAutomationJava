package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumDriverInit {
    AppiumDriver driver;

    public AppiumDriver getDriver() {
        try {
            driver = initDriver();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return this.driver;
    }

    private AppiumDriver initDriver() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Pixel_9");
        caps.setCapability("appPackage", "io.appium.android.apis");
        caps.setCapability("appActivity", ".ApiDemos");
        caps.setCapability("platformVersion", "16.0");
        caps.setCapability("automationName", "UiAutomator2");
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        return driver;
    }
}
