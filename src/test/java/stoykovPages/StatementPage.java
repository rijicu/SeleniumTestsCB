package stoykovPages;

import org.openqa.selenium.By;

/**
 * Created by stoykov on 10.08.2018.
 */
public class StatementPage extends AbstractPage {


    public void createOperStatement(){
        openFilter();
        filterApplyButton.isDisplayed();
        waitForVisibilityOf(By.className("action-apply"),5);
        filterApplyButton.click();
    }

    public boolean isCorrectClientOnPage(){
        String clientOnStatement = getText(By.xpath("//tr[9]/td[3]/div/div"));
        String clientOnHeader = getText(By.className("username"));
        System.out.println(clientOnHeader);
        System.out.println(clientOnStatement);
        if(clientOnHeader.equals("Стойков Илья Васильевич") && clientOnStatement.equals("ФОП \"Стойков\"")){
            return true;
        } else {
            return false;
        }
    }

    public void createStatementManyTimes(){
        boolean correctClient = true;
        while (correctClient) {
            createOperStatement();
            correctClient = isCorrectClientOnPage();
        }
    }

}
