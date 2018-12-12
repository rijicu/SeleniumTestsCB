package stoykovPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by stoykov on 08.12.2018.
 */
public class ActionButtons extends AbstractPage {

    @FindBy(css = "div[data-url*='Edit']")
    private WebElement editButton;

    @FindBy(css = "div.action-sign")
    private WebElement signButtonOn;

    @FindBy(css = "div.action-unsign")
    private WebElement unsignButton;

    @FindBy(css = "div.action-send")
    private WebElement sendButton;

    @FindBy(css = "div.action-delete")
    private WebElement deleteButton;

    @FindBy(css = "div.action-recover")
    private WebElement recoverButton;

    public void clickEditButton(){
        this.editButton.click();
    }

    public void clickRecoverButton(){
        this.recoverButton.click();
    }

    public void clickDeleteButton(){
        this.deleteButton.click();
    }

    public boolean isEditButtonDisplayed(){
        return editButton.isDisplayed();
    }

    public boolean isDeleteButtonDisplayed(){
        return deleteButton.isDisplayed();
    }

    public boolean isSignButtonDisplayed(){
        return signButtonOn.isDisplayed();
    }
}
