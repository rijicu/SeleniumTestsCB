package stoykovPages;

import org.openqa.selenium.By;

/**
 * Created by stoykov on 03.01.2018.
 */
public class PaymentsHistoryPage extends AbstractPage{
    private By accountNumberInSenderColumn = By.xpath(".//*[@id='docs']/table/tbody/tr[1]/td[6]/span[1]");



    public void waitForPaymentsHistoryPageToLoad(){
        waitForVisibilityOf(accountNumberInSenderColumn);
    }

    public boolean isCorrectAccountNumberOnPaymentsHistoryPage(String correctAccountNumberOnPaymentsHistoryPage){
        if (getText(accountNumberInSenderColumn).equals(correctAccountNumberOnPaymentsHistoryPage)){
            return true;
        }
        return false;
    }

}
