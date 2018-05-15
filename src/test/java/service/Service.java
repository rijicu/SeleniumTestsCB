package service;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.concurrent.TimeUnit;

/**
 * Created by haraman on 26.04.2016.
 */
public class Service {
    //Service functions
    public Service ( ){  }

    public static  WebElement WaitWebElement (WebDriver driver, By by, int timewait, int flag)   {
        //Waiting for webElement by flag
        //flag      -  waiting type
        //timewait  -  waiting time

        //Last Modified:    04-07-2016
        //Resolution:       Work

        WebDriverWait wait = new WebDriverWait(driver,timewait);
      //  wait.ignoring(InvalidSelectorException.class, StaleElementReferenceException.class);
        switch ( flag )
        {
            case 1: wait.until(ExpectedConditions.visibilityOfElementLocated(by)); break;
            case 2: wait.until(ExpectedConditions.presenceOfElementLocated(by)); break;
            case 3: wait.until(ExpectedConditions.elementToBeClickable(by)); break;
            case 4: wait.until(ExpectedConditions.stalenessOf(driver.findElement(by))); break;
            case 5: wait.until(ExpectedConditions.visibilityOf(driver.findElement(by))); break;

        }
        return driver.findElement(by);
    }
    @Step("Comparison of the fact - {0} = standard {1} ?")
    public static void MyAssertEquals (String fact,String etalon) {
        Assert.assertEquals(fact, etalon);
    }


    public static WebElement FindElement( WebDriver driver, final By by, int timewait)
    {
        //Waiting for webElement and return this
        // timewait - waiting time

        //Last Modified:    01-05-2016
        //Resolution:       Work

        if (timewait > 0)
        {
            WebDriverWait wait = new WebDriverWait(driver,timewait);
            return wait.until(new ExpectedCondition<WebElement>(){
                public WebElement apply(WebDriver d) {
                    return d.findElement(by);}} );
        }

        return driver.findElement(by);
    }
    public static boolean IsElementPresent (WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public static void EditDate(WebDriver driver, By byDate, String DateValue ) throws InterruptedException {
        //Edit Date

        //Last Modified:    20-05-2016
        //Resolution:       Work

        WebElement date = driver.findElement(byDate);
        date.clear();
        Thread.sleep(100);
        date.click();
        date.sendKeys(DateValue);
        date.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public static void SelectFromList (WebDriver driver, By byWE, By byItem, String value) throws InterruptedException {
        // Add data to inputbox and choose the first row from list
        // byWE     - inputbox locator
        // value    - value
        // byItem   - first row locator

        // Last Modified:    20-05-2016
        //Resolution:       Work

        WebElement WE;
        WE = FindElement(driver, byWE, 20);
        //WE.clear() ;
      //  ?learInput(WE); //clear inputbox
        WE.sendKeys(value);
        Thread.sleep(500);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //WaitWebElement(driver, byItem, 30, 2);
        FindElement(driver, byItem, 20).click(); //select from a list
        Thread.sleep(1000);
    }
    public static void SelectFromList (WebDriver driver,By byList, Integer Item, Boolean ClearFlar ) throws InterruptedException {
        //Select a value from the drop-down list in numerical order
        // Item     - the serial number in the list
        //ClearFlar - indication to clean

        //Last Modified:    20-05-2016
        //Resolution:       Work

        WebElement dynamicElement = FindElement(driver, byList, 20) ;

        if (ClearFlar) { dynamicElement.clear();}
        for (Integer i=1; i<=Item; i++) {
            dynamicElement.sendKeys(Keys.DOWN);
        }
        dynamicElement.sendKeys(Keys.TAB);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS); // ????
    }
/*
    public static void AddContact ( WebDriver driver,By[] ArrayLocator, String[] ArrayData, String parentHandle, By byBTN) throws InterruptedException {
    // ArrayLocator - ?????? ????????? ??? ?????????? ????????
    // ArrayData    - ?????? ??????
    // parentHandle - ???? ??? ????????
    // byBTN        - add a contact button locator

        //Last Modified:    01-06-2016
        //Resolution:       Work
        BTN_ContatAddClick(driver, byBTN); //???? ???????? ???????
        WebElement  WE;

        for (int i = 0; i<ArrayLocator.length; i++ ) {
            WE =  FindElement(driver, ArrayLocator[i], 20);
           // WebElement  WE = driver.findElement(ArrayLocator[i]);
            WE.clear();
            WE.sendKeys(ArrayData[i]);
            Thread.sleep(500);
        }
        driver.findElement(LocatorsAnket.BTN_ContactSave.getText()).click();
        Thread.sleep(1000);
        // ???????????? ? ????
        driver.switchTo().window(parentHandle);
    }
*/

    public static void SelectFromList (WebDriver driver, By byList, By byItem) throws InterruptedException {
        //Last Modified:    20-04-2016
        //Resolution:          ????

        driver.findElement (byList).click();
        //WaitWebElement(driver, byItem, 30, 1);
        Thread.sleep(1000);
        driver.findElement (byItem).click(); //????? ?? ??????

    }

    public static void WaitNoEmptyValue (){
    /*
        //????????, ??? ????? ?? ??????...
                Service.Service.WaitWebElement(driver, LocatorsAnket.ED_ClientULUridCity.getText(), 10, 2);
        WE = driver.findElement(LocatorsAnket.ED_ClientULUridCity.getText());
      //  (new WebDriverWait(driver, 20)).until(boolean apply function(){ if (WE.getText()== null) return false; else return true; } );
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition <Boolean>() {
            public Boolean apply(WebDriver driver) {
               if (driver.findElement(LocatorsAnket.ED_ClientULUridCity.getText()).getText().length() == 0)
                    return false ;
                else
                    return true;
            }
        });
        */
    }
   /* static void ?learInput(WebElement webElement) {
        //??????? ???????? ??????????? ????? ???????? ??????? ??????? Backspace

        //Last Modified:    01-06-2016
        //Resolution:       Work

        /*isIE() - just checks is it IE or not - use your own implementation
        if (isIE() && "file".equals(webElement.getAttribute("type"))) {
           //  workaround
           //  if IE and input's type is file - do not try to clear it.
           //  If you send:
            // - empty string - it will find file by empty path
            // - backspace char - it will process like a non-visible char
            // In both cases it will throw a bug.
            //
            // Just replace it with new value when it is need to.
            } else {

            // if you have no StringUtils in project, check value still empty yet

            while (!StringUtils.isEmpty(webElement.getAttribute("value"))) {
                // "\u0008" - is backspace char
                webElement.sendKeys("\u0008");
            }
//        }
    }
*/

}
