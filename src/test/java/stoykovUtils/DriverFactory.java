package stoykovUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by stoykov on 26.10.2017.
 */
public class DriverFactory {
    private static final Integer TIMEOUT_IN_SECONDS = 5;
    private static WebDriver driver;

    public static WebDriver getDriver() {

        if(driver == null) {
            System.setProperty("webdriver.chrome.driver","D:\\LAN\\AUTOtest\\seleniumDriver\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(TIMEOUT_IN_SECONDS, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            return driver;
        }

        return driver;
    }

    public static void shutDownDriver() {
        driver.quit();
        driver = null;
    }
}
