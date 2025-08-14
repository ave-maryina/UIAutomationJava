package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        plugin = {"pretty",
                "html:target/cucumber-reports/report.html",
                "json:target/cucumber-reports/report.json"},
        features = {"src/test/resources/feature"},
        glue = {"selenideStepDefinition"}
)

public class TestRunner extends AbstractTestNGCucumberTests {
}
