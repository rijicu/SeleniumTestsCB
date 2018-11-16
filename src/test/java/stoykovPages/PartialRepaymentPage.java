package stoykovPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by stoykov on 26.10.2018.
 */
public class PartialRepaymentPage extends AbstractPage {

    @FindBy(css = "[aria-controls='accountPay_listbox']")
    private WebElement selectAccount;

    @FindBy(xpath = "//span[text()=' 26004002813159.980 UAH']")
    private WebElement accountInList;

    @FindBy(css = ".textAmount")
    private WebElement amountInput;

    @FindBy(css = "span#acc_rest")
    private WebElement accountRestField;

    @FindBy(css = "span.text")
    private WebElement errorText;




    public void selectAccountFromList(){
        selectOptionByWebElement(selectAccount, accountInList);
    }

    public void setAmountInput(){
        amountInput.clear();
        amountInput.sendKeys("1500");
    }

    public void createPartialRepayment(String ...fieldName){
        amountInput.clear();
        for(String f : fieldName) {
            if (f.contains("Account")) {
                selectAccountFromList();
            }
            if (f.contains("Sum")) {
                setAmountInput();
            }
        }
        clickSaveButton();
    }

    public void createCorrectPartialRepayment(){
        createPartialRepayment("Account", "Sum");
    }

    public void createPartialRepaymentWithEmptyFields(){
        createPartialRepayment();
    }

    public void createPartialRepaymentWithEmptySumField(){
        createPartialRepayment("Account");
    }

    public void createPartialRepaymentWithEmptyAccountField(){
        createPartialRepayment("Sum");
    }

    public String getErrorText(){
        return getWebElementText(errorText);
    }



}
