package stoykovPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;

/**
 * Created by stoykov on 26.10.2018.
 */
public class CreditsOperationsHistoryPage extends AbstractPage {

    @FindBy(css = "span[aria-owns = 'treatyId_listbox'] > span > .k-input")
    private WebElement treatyNumberFieldInput;

    @FindBy(xpath = "//tbody[@role='rowgroup']/tr[1]//a[contains((@href), 'CreditDetails')]")
    private WebElement treatyCodeInList;

    @FindBy(xpath = "//tbody[@role='rowgroup']/tr[1]/td[4]")
    private WebElement treatyTipeInList;

    @FindBy(xpath = "//tbody[@role='rowgroup']/tr[1]/td[2]/span[contains((@class), 'deletedClient')]")
    private WebElement treatyDeleteStatusIconInList;

    @FindBy(xpath = "//tbody[@role='rowgroup']/tr[1]/td[2]/span[contains((@class), 'status-forma')]")
    private WebElement treatyNewStatusIconInList;

    @FindBy(xpath = "//tbody[@role='rowgroup']/tr[1]//a[contains((@href), 'CreditActionView')]")
    private WebElement requestViewLinkInList;

    @FindBy(css = "a.icon-button-edit-active")
    private WebElement requestEditButton;

    @FindBy(css = "td.amount-column")
    private WebElement amountColumn;

    @FindAll(@FindBy(xpath = "//tbody[@role='rowgroup']/tr"))
    private List<WebElement> operationsRowsOnList;

/*    @FindBy(css = "div.action-sign")
    private WebElement signButton;

    @FindBy(css = "div.action-unsign")
    private WebElement unsignButton;

    @FindBy(css = "div.action-send")
    private WebElement sendButton;

    @FindBy(css = "div.action-delete")
    private WebElement deleteButton;

    @FindBy(css = "div.action-recover")
    private WebElement recoverButton;*/

    public boolean isTreatyDeleteStatusIconInList(){
        return treatyDeleteStatusIconInList.isDisplayed();
    }

    public boolean isTreatyNewStatusIconInList(){
        return treatyNewStatusIconInList.isDisplayed();
    }

    public boolean isCorrectTreatyNumberInFilter(String expectedTreatyNumberInFilter){
        openFilter();
        treatyNumberFieldInput.isDisplayed();
        if (treatyNumberFieldInput.getText().equals(expectedTreatyNumberInFilter)){
            return true;
        }
        return false;
    }

    public PartialRepaymentViewPage openRequestToView(){
        requestViewLinkInList.click();
        return new PartialRepaymentViewPage();
    }

    public CreditDetailsPage clickToTreatyCodeLinkInList(){
        treatyCodeInList.click();
        return new CreditDetailsPage();
    }

    public String getTreatyAmount(){
        return amountColumn.getText();
    }

    public String getTreatyNumberFromOperationsPage(){
        return treatyCodeInList.getText();
    }

    public String getTreatyTypeFromOperationsPage(){
        return treatyTipeInList.getText();
    }

    public PartialRepaymentPage clickRequestEditButton(){
        requestEditButton.click();
        return new PartialRepaymentPage();
    }



}
