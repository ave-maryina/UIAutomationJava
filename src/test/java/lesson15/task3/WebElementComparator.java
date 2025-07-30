package lesson15.task3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.driver.DriverSetUp;
import utils.urls.Links;

import java.time.Duration;

public class WebElementComparator {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = DriverSetUp.getDriver();
        driver.get(Links.ANDERSEN_lAB_AUTHORIZATION_PAGE.getLink());
        WebDriverWait wail = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(3000);
        WebElement element1 = driver.findElement(By.xpath("//img"));
        WebElement element2 = driver.findElement(By.xpath("//span[contains(text(), 'Registration')]"));
        compareElements(element1, element2, "Andersen logo", "Registration link");
        driver.quit();
    }

    public static void compareElements(WebElement element1, WebElement element2, String name1, String name2) {
        if (element1.getLocation().y < element2.getLocation().y) {
            System.out.println(name1 + "  is positioned higher then " + name2);
        } else {
            System.out.println(name2 + "  is positioned higher then " + name1);
        }
        if (element1.getLocation().x < element2.getLocation().x) {
            System.out.println(name1 + " is positioned of the left " + name2);
        } else {
            System.out.println(name2 + " is positioned to the left of " + name1);
        }

        if (element1.getSize().height * element1.getSize().width > element2.getSize().height * element2.getSize().width) {
            System.out.println(name1 + " takes up more space then " + name2);
        } else {
            System.out.println(name2 + " takes up more space then " + name1);
        }
    }
}
