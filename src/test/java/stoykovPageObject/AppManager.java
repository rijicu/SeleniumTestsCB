package stoykovPageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

/**
 * Created by stoykov on 20.10.2017.
 */
public class AppManager {
    protected WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    public void initApp() {
        System.setProperty("webdriver.chrome.driver","D:\\LAN\\AUTOtest\\seleniumDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://srv-cb-a/";
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void stopApp(){
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
