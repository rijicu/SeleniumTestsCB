package stoykovPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by stoykov on 04.01.2018.
 */
public class CreatePaymentPage extends AbstractPage {
    private By debitAccountFieldLink = By.xpath("//input[@name='DebitAccountId_input']");

    @FindBy(name = "DebitAccountId_input")
    private WebElement debitAccountIdInput;

    public void waitForCreatePaymentPageToLoad(){
        waitForVisibilityOf(debitAccountFieldLink);
    }

    public boolean isCorrectAccountNumberOnCreatePaymentPage(String correctAccountNumberOnCreatePaymentPage){
        waitForAttributeInWebElementToBeNotEmpty(debitAccountIdInput,"value");
        //waitForAttributeToBe(debitAccountFieldLink,"value","26001200558866.980 ФОП \"Стойков\"");
        return getElementAttribute(debitAccountIdInput,"value").equals(correctAccountNumberOnCreatePaymentPage);
/*        if (getText(debitAccountFieldLink).equals(correctAccountNumberOnCreatePaymentPage)){
            return true;
        }
        return false;*/
    }
}
