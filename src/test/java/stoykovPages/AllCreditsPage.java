package stoykovPages;


import org.openqa.selenium.By;

/**
 * Created by stoykov on 24.05.2018.
 */
public class AllCreditsPage extends AbstractPage {

    private By creditTreatysLinks = By.className("action-link");
    private By accountSelectField = By.xpath("//span[@aria-owns=\"accounts_listbox\"]");
    private By accountNumberLink = By.xpath("//span[@data-value=\"20630000000118.980\"]");
    private By accountNumberOnTreatyList = By.xpath("//td[text()=\"20630000000118.980\"]");



    public int numbersOfTreatiesOnPage(){
       //return driver.findElements(creditTreatysLinks).size();
        return getElementsListOnPage(creditTreatysLinks).size();
    }

    public void findTreatyByAccountNumber(){
        openFilter();
        selectOptionByXpath(accountSelectField, accountNumberLink);
        clickApplyButton();
        waitUntilLoadingImageNotPresent();
    }

    public String getAccountNumberOnTreatyList(){
        return driver.findElement(accountNumberOnTreatyList).getText();
    }



}
