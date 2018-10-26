package stoykovPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by stoykov on 26.10.2018.
 */
public class CreditsOperationsHistoryPage extends AbstractPage {

    @FindBy(css = "span[aria-owns = 'treatyId_listbox'] > span > .k-input")
    private WebElement treatyNumberFieldInput;

    public boolean isCorrectTreatyNumberInFilter(String expectedTreatyNumberInFilter){
        openFilter();
        treatyNumberFieldInput.isDisplayed();
        if (treatyNumberFieldInput.getText().equals(expectedTreatyNumberInFilter)){
            return true;
        }
        return false;
    }



}
