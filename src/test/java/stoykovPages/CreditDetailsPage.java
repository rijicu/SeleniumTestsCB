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
    //private By treatyLocalNameField = By.xpath("//textarea[@id='localName']");

    @FindBy(id = "localName")
    private WebElement treatyLocalNameField;

    @FindBy(xpath = "//div[contains((@class), 'action-savename')]")
    private WebElement changeTreatyNameButton;


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
}
