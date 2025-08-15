package selenideStepDefinition;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseBrowserActions {
    @Before
    public void set_up_driver() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.headless = false;
        Configuration.browserSize = "1920x1080";
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            Allure.addAttachment(scenario.getName() + "_Failed", "image/png", new ByteArrayInputStream(Objects.requireNonNull(screenshot)), "png");
        }
        closeWebDriver();
    }
}
