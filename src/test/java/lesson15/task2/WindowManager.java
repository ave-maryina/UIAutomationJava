package lesson15.task2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import utils.driver.DriverSetUp;
import utils.urls.Links;

import java.util.List;
import java.util.Set;

public class WindowManager {
    public static void main(String[] args) {
        WebDriver driver = DriverSetUp.getDriver();
        String mainWindow = driver.getWindowHandle();
        List<String> urls = List.of(Links.AUTOMATION_PRACTICE.getLink(), Links.ZOO_WAW.getLink(),
                Links.W_3_SCHOOLS.getLink(), Links.CLICK_SPEED_TESTER.getLink(), Links.ANDERSEN_lAB.getLink());

        for (String url : urls) {
            driver.switchTo().newWindow(WindowType.WINDOW);
            driver.get(url);
        }

        Set<String> windowHandles = driver.getWindowHandles();

        for (String handle : windowHandles) {
            if (handle.equals(mainWindow)) continue;

            driver.switchTo().window(handle);
            System.out.println("Title: " + driver.getTitle() + "; URL: " + driver.getCurrentUrl());

            if (driver.getTitle().contains("Zoo")) {
                System.out.println(driver.getTitle() + " is closed");
                driver.close();
            }
        }
        driver.quit();
        System.out.println("Finished");
    }
}
