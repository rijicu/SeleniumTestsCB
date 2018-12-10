package stoykovPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmWindowButtons extends AbstractPage {

    @FindBy(css = "p.action-delete-confirm")
    private WebElement deleteConfirmButton;

    @FindBy(css = "p.action-delete-cancel")
    private WebElement deleteCancelButton;

    @FindBy(css = "p.action-recover-confirm")
    private WebElement recoverConfirmButton;

    @FindBy(css = "p.action-recover-cancel")
    private WebElement recoverCancelButton;

    public void clickRecoverCancelButton(){
        waitForWebElementToBeClickable(recoverCancelButton);
        recoverCancelButton.click();
    }
    public void clickRecoverConfirmButton(){
        waitForWebElementToBeClickable(recoverConfirmButton);
        recoverConfirmButton.click();
    }

    public void clickDeleteCancelButton(){
        waitForWebElementToBeClickable(deleteCancelButton);
        deleteCancelButton.click();
    }

    public void clickDeleteConfirmButton(){
        waitForWebElementToBeClickable(deleteConfirmButton);
        deleteConfirmButton.click();
    }
}
