package stoykovPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by stoykov on 26.10.2018.
 */
public class PartialRepaymentPage extends AbstractPage {

    @FindBy(css = "[aria-controls='accountPay_listbox']")
    private WebElement selectAccount;

    
}
