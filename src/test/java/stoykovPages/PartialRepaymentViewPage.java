package stoykovPages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartialRepaymentViewPage extends AbstractPage {

    private ActionButtons actionButtons = new ActionButtons();
    private ConfirmWindowButtons confirmWindowButtons = new ConfirmWindowButtons();

    @FindBy(css = "div.document .itemBlockHeader > p")
    private WebElement documentHeader;


    @FindBy(css = "div.notify")
    private WebElement notifyMessage;

    public Boolean isNotifyPresent(){
        waitUntilWebElementPresent(notifyMessage);
        return notifyMessage.isDisplayed();
    }

    public PartialRepaymentPage editOperationFromViewPage(){
        actionButtons.clickEditButton();
        return new PartialRepaymentPage();
    }

    public void recoverOperationFromViewPage(){
        actionButtons.clickRecoverButton();
        confirmWindowButtons.clickRecoverConfirmButton();
    }

    public void deleteOperationFromViewPage(){
        actionButtons.clickDeleteButton();
        confirmWindowButtons.clickDeleteConfirmButton();
    }

    public Boolean isEditButtonPresentOnViewPage(){
        return  actionButtons.isEditButtonDisplayed();
    }

    public Boolean isDeleteButtonPresentOnViewPage(){
        return actionButtons.isDeleteButtonDisplayed();
    }

    public Boolean isSignButtonPresentOnViewPage(){
        return actionButtons.isSignButtonDisplayed();
    }

    public String getDocumentHeaderText(){
        return documentHeader.getText();
    }

}
