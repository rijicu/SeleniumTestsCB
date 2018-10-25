package stoykovPages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by stoykov on 24.05.2018.
 */
public class AllCreditsPage extends AbstractPage {

    private By creditTreatysLinks = By.className("action-link");
    private By accountSelectField = By.xpath("//span[@aria-owns=\"accounts_listbox\"]");
    private By accountNumberLink = By.xpath("//span[@data-value=\"20630000000118.980\"]");
    private By accountNumberOnTreatyList = By.xpath("//td[text()=\"20630000000118.980\"]");
    private By treatyNameColumn = By.xpath("//td[text()='Name sets by test']");
    private By newTreatyNameColumn = By.xpath("//td[text()='New Test Name']");

    @FindBy(className = "action-link")
    private List<WebElement> creditTreatiesLinks;



    public int numbersOfTreatiesOnPage(){
       //return driver.findElements(creditTreatysLinks).size();
       //17.10.18 return getElementsListOnPage(creditTreatysLinks).size();
        return creditTreatiesLinks.size();
    }

    public void findTreatyByAccountNumber(){
        //openFilter();
        selectOptionByXpath(accountSelectField, accountNumberLink);
        clickApplyButton();
        waitUntilLoadingImageNotPresent();
    }

    public CreditDetailsPage openCreditDetailsPage(By treatyLink){
        clickAt(treatyLink);
        return new CreditDetailsPage();
    }

    public String getAccountNumberOnTreatyList(){
        return driver.findElement(accountNumberOnTreatyList).getText();
    }

    public boolean isCorrectTreatyName(){
        return isElementPresent(treatyNameColumn);
    }

    public boolean isNewTreatyNameCorrect(){
        return isElementPresent(newTreatyNameColumn);
    }



}
