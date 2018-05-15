import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;

public class test {
    FirefoxDriver wd;
    
    @BeforeMethod
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
    
    @Test
    public void test() {
        wd.get("http://srv-cb-a/iTiny20_Night/Auth/Login?ReturnUrl=%2fiTiny20_Night%2f");

        wd.findElement(By.id("login")).sendKeys("StoykovIV");

        wd.findElement(By.id("password")).sendKeys("12345678");
        wd.findElement(By.xpath("//div[@class='entrance']//p[.='Увійти ']")).click();
        wd.findElement(By.xpath("//ul[@class='main-menu']/li[3]/p")).click();
        wd.findElement(By.linkText("Історія платежів")).click();

        if (!wd.findElement(By.id("period")).isSelected()) {
            wd.findElement(By.id("period")).click();
        }
        wd.findElement(By.id("from")).click();
        wd.findElement(By.id("from")).clear();
        wd.findElement(By.id("from")).sendKeys("15.05.2017");
        wd.findElement(By.id("into")).click();
        wd.findElement(By.id("into")).clear();
        wd.findElement(By.id("into")).sendKeys("15.05.2017");
        wd.findElement(By.xpath("//div[@class='oneLineContent']//span[.='select']")).click();
        if (wd.findElement(By.name("Проведені")).isSelected()) {
            wd.findElement(By.name("Проведені")).click();
        }
        if (!wd.findElement(By.name("Проведені")).isSelected()) {
            wd.findElement(By.name("Проведені")).click();
        }
        wd.findElement(By.cssSelector("p.action-apply")).click();
        wd.findElement(By.xpath("//div[@class='icon-buttons-group']/a[3]")).click();
        wd.findElement(By.xpath("//div[@class='layer']/div[18]/div")).click();
        wd.findElement(By.cssSelector("div.panel1.s1-121417-3c83")).click();
        wd.findElement(By.xpath("//div[@class='layer']/div[17]/div")).click();
    }
    
    @AfterMethod
    public void tearDown() {
        wd.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
