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

    @FindBy(css = "a.icon-button-edit-active")
    private WebElement requestEditButton;

    @FindBy(css = "td.amount-column")
    private WebElement amountColumn;

    @FindAll(@FindBy(xpath = "//tbody[@role='rowgroup']/tr"))
    private List<WebElement> operationsRowsOnList;

    public boolean isCorrectTreatyNumberInFilter(String expectedTreatyNumberInFilter){
        openFilter();
        treatyNumberFieldInput.isDisplayed();
        if (treatyNumberFieldInput.getText().equals(expectedTreatyNumberInFilter)){
            return true;
        }
        return false;
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
