package stoykovPages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartialRepaymentViewPage extends AbstractPage {

    private ActionButtonsOnViewPage actionButtonsOnViewPage = new ActionButtonsOnViewPage();
    private ConfirmWindowButtons confirmWindowButtons = new ConfirmWindowButtons();

    @FindBy(css = "div.document .itemBlockHeader > p")
    private WebElement documentHeader;

    @FindBy(css = "div[data-url*='Edit']")
    private WebElement editButtonOnViewPage;

    @FindBy(css = "div.action-sign")
    private WebElement signButtonOnViewPage;

    @FindBy(css = "div.action-delete")
    private WebElement deleteButtonOnViewPage;

    @FindBy(css = "p.action-delete-confirm")
    private WebElement deleteConfirmButton;

    @FindBy(css = "div.action-recover")
    private WebElement recoverButton;



    @FindBy(css = "div.notify")
    private WebElement notifyMessage;

    public Boolean isNotifyPresent(){
        waitUntilWebElementPresent(notifyMessage);
        return notifyMessage.isDisplayed();
    }

    public void recoverOperationFromViewPage(){
        actionButtonsOnViewPage.clickRecoverButton();
        confirmWindowButtons.clickRecoverConfirmButton();
    }

    public void deleteOperationFromViewPage(){
        actionButtonsOnViewPage.clickDeleteButton();
        confirmWindowButtons.clickDeleteConfirmButton();
    }

    public Boolean isEditButtonPresentOnViewPage(){
        return  editButtonOnViewPage.isDisplayed();
    }

    public Boolean isDeleteButtonPresentOnViewPage(){
        return deleteButtonOnViewPage.isDisplayed();
    }

    public Boolean isSignButtonPresentOnViewPage(){
        return signButtonOnViewPage.isDisplayed();
    }

    public String getDocumentHeaderText(){
        return documentHeader.getText();
    }

}
