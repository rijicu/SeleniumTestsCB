import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import stoykovPageObject.AppManager;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

/**
 * Created by stoykov on 10.10.2017.
 */
public class StoykovTestBase {
    protected AppManager app;
    protected WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
      System.setProperty("webdriver.chrome.driver","D:\\LAN\\AUTOtest\\seleniumDriver\\chromedriver.exe");
      driver = new ChromeDriver();
      baseUrl = "http://srv-cb-a/";
      driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
      driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
      driver.quit();
      String verificationErrorString = verificationErrors.toString();
      if (!"".equals(verificationErrorString)) {
        fail(verificationErrorString);
      }
    }

    protected void checkIsUserCanLogin(By selector, String expectedResult) throws InterruptedException {
      userLogin("StoykovIV", "12345678");
      verifyText(selector, expectedResult);
    }

    protected void userLoginAndCheckMainePageIsOpen() throws InterruptedException {
      checkIsUserCanLogin(By.cssSelector("span"), "Вітаємо, Стойков Илья Васильевич!");
      userLogOff();
    }

    protected void loginAsAdminAndClearUserIpForm() throws InterruptedException {
      userLogin("admin", "1");
      goToUsersPageFromAdmin();
      findUserByLogin("StoykovIV");
      deleteBindedIPAddressOnUser();
      userLogOff();
    }

    protected void loginAndFillIpAddressForm(String ipAddress) throws InterruptedException {
      userLogin("StoykovIV", "12345678");
      goToProfileSecurityPage();
      fillIpAdresForm(ipAddress);
      verifyText(By.cssSelector("div.data-ip.ipData"), ipAddress);
      userLogOff();
    }

    private void deleteBindedIPAddressOnUser() throws InterruptedException {
      clickAndWait(By.linkText("StoykovIV"));
      // Удаляем привязанный IP
      clickAndWait(By.cssSelector("#t1 > a.k-link"));
      clickAndWait(By.xpath("//form[@id='userInfoInfo']/div[6]/div/div/div[2]/div/div/span"));
      driver.findElement(By.cssSelector("div.saveButton.saveUserParams > p")).click();
    }

    private void findUserByLogin(String userLogin) throws InterruptedException {
      driver.findElement(By.id("filter_login")).clear();
      driver.findElement(By.id("filter_login")).sendKeys(userLogin);
      clickAndWait(By.cssSelector("div.apply.action-apply > p"));
    }

    private void goToUsersPageFromAdmin() throws InterruptedException {
      clickAndWait(By.xpath("//li[3]/p"));
      clickAndWait(By.cssSelector("li.activeMenuTab > div.sub-menu > ul > li > a > p"));
    }

    private void userLogOff() throws InterruptedException {
      driver.findElement(By.cssSelector("span.icon.icon-exit")).click();
      Thread.sleep(2000);
    }

    protected void verifyText(By selector, String expectedResult) {
      try {
        assertEquals(driver.findElement(selector).getText(), expectedResult);
      } catch (Error e) {
        verificationErrors.append(e.toString());
      }
    }

    protected void fillIpAdresForm(String ipAddress) throws InterruptedException {
      driver.findElement(By.cssSelector("input.textBox.data-addip")).clear();
      driver.findElement(By.cssSelector("input.textBox.data-addip")).sendKeys(ipAddress);
      driver.findElement(By.cssSelector(".icon-add")).click();
      clickAndWait(By.cssSelector("div.saveButton.action-security"));
    }

    protected void goToProfileSecurityPage() throws InterruptedException {
      driver.findElement(By.xpath("//li[12]/p")).click();
      driver.findElement(By.cssSelector("li.activeMenuTab > div.sub-menu > ul > li > a > p")).click();
      clickAndWait(By.xpath("//div/div/ul/li[3]/a"));
    }

    protected void userLogin(String login, String password) throws InterruptedException {
      driver.get(baseUrl + "/iTiny20_Night/Auth/Login?ReturnUrl=%2fiTiny20_Night%2f");
      driver.findElement(By.id("login")).clear();
      driver.findElement(By.id("login")).sendKeys(login);
      driver.findElement(By.id("password")).clear();
      driver.findElement(By.id("password")).sendKeys(password);
      driver.findElement(By.cssSelector("div.entrance > p")).click();
    }

    private void clickAndWait(By by) throws InterruptedException {
      driver.findElement(by).click();
      Thread.sleep(2000);
    }

    private boolean isElementPresent(By by) {
      try {
        driver.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    private boolean isAlertPresent() {
      try {
        driver.switchTo().alert();
        return true;
      } catch (NoAlertPresentException e) {
        return false;
      }
    }

    private String closeAlertAndGetItsText() {
      try {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        if (acceptNextAlert) {
          alert.accept();
        } else {
          alert.dismiss();
        }
        return alertText;
      } finally {
        acceptNextAlert = true;
      }
    }
}
