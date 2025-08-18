package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.android.options.UiAutomator2Options;
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
//        UiAutomator2Options options = new UiAutomator2Options();
//        options.setDeviceName("emulator-5554");
//        options.setPlatformName("Android");
//        options.setAppPackage("io.appium.android.apis");
//        options.setAppActivity(".ApiDemos");
//        options.setPlatformVersion("16.0");
//        options.setAutomationName("UiAutomator2");
//        driver = new AndroidDriver(new URL("http://127.0.0.1.4723"), options);


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
