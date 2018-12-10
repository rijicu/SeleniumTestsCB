package stoykovPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by stoykov on 24.05.2018.
 */
public class CreditDetailsPage extends AbstractPage {
    String treatyLocalName = "Name sets by test";
    String newTreatyLocalName = "New Test Name";

    private By messageLinkAboutDelay = By.xpath("//span[@class='text']");
    private By treatyNumberOnDetailsPageLink = By.xpath("//div[text()='N20.00.004174']");
    private By tretyAccountStatementButton = By.xpath("//p[text()='Виписка ']");
    //private By treatyLocalNameField = By.xpath("//textarea[@id='localName']");

    @FindBy(id = "localName")
    private WebElement treatyLocalNameField;
    @FindBy(css = ".itemDetailsHeader > p")
    private WebElement documentHeader;
    @FindBy(xpath = "//div[contains((@class), 'action-savename')]")
    private WebElement changeTreatyNameButton;
    @FindBy(css = "div[data-url*='CreditsEvents']>p")
    private WebElement paymentCalendarButton;
    @FindBy(css="div[data-url*='TreatyCreditActions']>p")
    private WebElement operationsHistoryButton;
    @FindBy(css="div[data-url*='CreditPay']>p")
    private WebElement partialRepaymentButton;
    @FindBy(css = "div[data-url*='CreditFullPay']>p")
    private WebElement fullRepayButton;

    public PartialRepaymentPage openPartialRepaymentPage(){
        partialRepaymentButton.click();
        return new PartialRepaymentPage();
    }

    public CreditsOperationsHistoryPage openCreditsOperationsHistoryPage(){
        operationsHistoryButton.click();
        waitUntilLoadingImageNotPresent();
        return new CreditsOperationsHistoryPage();
    }

    public PaymentCalendarPage openPaymentCalendarPage(){
        paymentCalendarButton.click();
        waitUntilLoadingImageNotPresent();
        return new PaymentCalendarPage();
    }

    public StatementPage openTreatyStatementPage(){
        clickAt(tretyAccountStatementButton);
        return new StatementPage();
    }

    public String getMessageTextAboutDelay(){
        return getText(messageLinkAboutDelay);
    }

    public boolean isTreatyNumberPresentOnDetailsPage(){
        return isElementPresent(treatyNumberOnDetailsPageLink);
    }

    public void changeTreatyLocalName(){
        treatyLocalNameField.clear();
        treatyLocalNameField.sendKeys(treatyLocalName);
        changeTreatyNameButton.click();
    }

    public void setTreatyNewLocalName(){
        treatyLocalNameField.clear();
        treatyLocalNameField.sendKeys(newTreatyLocalName);
        changeTreatyNameButton.click();
    }

    public String getDocumentHeaderText(){
        return documentHeader.getText();
    }
}
