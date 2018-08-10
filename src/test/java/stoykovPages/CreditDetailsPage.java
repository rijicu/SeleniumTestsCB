package stoykovPages;

import org.openqa.selenium.By;

/**
 * Created by stoykov on 24.05.2018.
 */
public class CreditDetailsPage extends AbstractPage {

    private By messageLinkAboutDelay = By.xpath("//span[@class='text']");
    private By treatyNumberOnDetailsPageLink = By.xpath("//div[text()='N20.00.004174']");

    public String getMessageTextAboutDelay(){
        return getText(messageLinkAboutDelay);
    }

    public boolean isTreatyNumberPresentOnDetailsPage(){
        return isElementPresent(treatyNumberOnDetailsPageLink);
    }
}
