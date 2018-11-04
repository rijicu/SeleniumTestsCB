package stoykovPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by stoykov on 26.10.2018.
 */
public class PartialRepaymentPage extends AbstractPage {

    @FindBy(css = "[aria-controls='accountPay_listbox']")
    private WebElement selectAccount;

    @FindBy(xpath = "//span[text()=' 26004002813159.980']")
    private WebElement accountInList;

    @FindBy(css = ".textAmount")
    private WebElement amountInput;

    @FindBy(css = "span#acc_rest")
    private WebElement accountRestField;




    public void selectAccountFromList(){
        selectOptionByWebElement(selectAccount, accountInList);
    }

    public void setAmountInput(){
        amountInput.sendKeys("1500");
    }



}
