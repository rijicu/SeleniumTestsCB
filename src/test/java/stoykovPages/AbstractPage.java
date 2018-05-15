package stoykovPages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import stoykovUtils.DriverFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

/**
 * Created by stoykov on 26.10.2017.
 */
public abstract class AbstractPage {
    WebDriver driver = DriverFactory.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, 10);

    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private By loadingImage = By.className("k-loading-image");
    private By filterCollapsed = By.xpath("//p[@class='collapsed']");
    private By filterExpanded = By.xpath("//p[@class='expanded']");

    @FindBy(xpath = "//input[@name='AccountId_input']")
    private WebElement accountFieldLinkOnFilter;

    @FindBy(className = "action-apply")
    private WebElement filterApplyButton;

    @FindBy(xpath = "//p[@class='action-clean']")
    private WebElement cleanButtonLink;

    AbstractPage() {
        PageFactory.initElements(driver, this);
    }

    public void getElementText(By by){
        if (isElementPresent(by)){
           // String elementText =
        }
    }

    public void pressOnCleanFilterButton(){
        waitForVisibilityOf(By.xpath("//p[@class='action-clean']"),3);
        cleanButtonLink.isDisplayed();
        cleanButtonLink.click();
        waitUntilLoadingImageNotPresent();
    }

    public String getText(By element){
        return driver.findElement(element).getText();
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void openFilter(){
        clickAt(filterCollapsed);
        filterApplyButton.isDisplayed();
    }

    public List<WebElement> getElementsListOnPage(By element){
        return driver.findElements(element);
    }

    public void closeFilter(){
        clickAt(filterExpanded);
    }

    public boolean isElementPresent(By by){
        try {
           driver.findElement(by);
           return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void clickAt(By link) {
        driver.findElement(link).click();
    }

    public void waitForVisibilityOf(By locator, Integer... timeOutInSeconds){
        int attempts = 0;
        while (attempts < 2){
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),(timeOutInSeconds.length > 0 ? timeOutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e){
            }
            attempts++;
        }
    }

    public void waitForAttributeToBe(By locator, String attribute, String expectedValue){
        int attempts = 0;
        while (attempts < 2){
            try {
                wait.until(ExpectedConditions.attributeToBe(locator,attribute, expectedValue));
                break;
            } catch (StaleElementReferenceException e){
            }
            attempts++;
        }
    }

    public void waitForAccountFieldOnFilterNotEmpty(){
        waitForAttributeInWebElementToBeNotEmpty(accountFieldLinkOnFilter,"value");
    }

    public void waitForAttributeInWebElementToBeNotEmpty(WebElement element, String attribute){
        int attempts = 0;
        while (attempts < 2){
            try {
                wait.until(ExpectedConditions.attributeToBeNotEmpty(element,attribute));
                break;
            } catch (StaleElementReferenceException e){
            }
            attempts++;
        }
    }

    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutInSeconds) {
        timeOutInSeconds = timeOutInSeconds != null ? timeOutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.until(condition);
    }

    public void waitUntilLoadingImageNotPresent(){
        waitUntilElementNotPresent(loadingImage);
    }

    public void waitUntilElementNotPresent(By link) {
       // if (isElementPresent(link))
        try {
            wait.until(ExpectedConditions.invisibilityOf(driver.findElement(link)));
        } catch (org.openqa.selenium.NoSuchElementException e){}
    }

    public void waitUntilElementPresent(By link) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(link)));
    }

/* Пример выбора элемента из выпадающего списка
    public void selectOptionByXpath(String controlName, String optionName) {
        driver.findElement(By.name(controlName)).click();
        driver.findElement(By.xpath("//option[text()='" + optionName + "']")).click();
    }
*/

    public void selectOptionByXpath(By controlXpath, By optionXpath) {
        driver.findElement(controlXpath).click();
        //System.out.println("//input[@name='" + optionName + "']");
        waitUntilElementPresent(optionXpath);
        driver.findElement(optionXpath).click();

    }

    public String getElementAttribute(WebElement element, String attribute){
        return element.getAttribute(attribute);
    }

    public void switchToFrame(String frameId) {
        driver.switchTo().frame(frameId);
    }

    public void printList(List<String> list){
        for (String accNum : list) {
            System.out.println(accNum);}
    }

}
