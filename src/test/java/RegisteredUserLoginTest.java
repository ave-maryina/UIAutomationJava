import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.urls.Links;

public class RegisteredUserLoginTest extends BaseTest {
    @Test
    @Parameters({"email", "password"})
    public void testParamLogin(String email, String password) {
        driver.get(Links.ANDERSEN_lAB_AUTHORIZATION_PAGE.getLink());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email"))).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password"))).sendKeys(password);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type=\"submit\"]")))
                .click();
        Assert.assertTrue(BaseTest.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath
                ("//div[contains(text(), 'Sign Out')]"))).isDisplayed(), "Login failed for user: "
                + email);
    }
}
