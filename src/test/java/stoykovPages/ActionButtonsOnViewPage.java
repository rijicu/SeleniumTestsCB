package stoykovPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by stoykov on 08.12.2018.
 */
public class ActionButtonsOnViewPage extends AbstractPage {

    @FindBy(css = "div[data-url*='Edit']")
    private WebElement editButton;

    @FindBy(css = "div.action-sign")
    private WebElement signButtonOn;

    @FindBy(css = "div.action-delete")
    private WebElement deleteButton;

    @FindBy(css = "div.action-recover")
    private WebElement recoverButton;

    public void clickRecoverButton(){
        this.recoverButton.click();
    }

    public void clickDeleteButton(){
        this.deleteButton.click();
    }
}
