package stoykovPages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartialRepaymentViewPage extends AbstractPage {
    @FindBy(css = "div.document .itemBlockHeader > p")
    private WebElement documentHeader;

    @FindBy(css = "div[data-url*='Edit']")
    private WebElement editButtonOnViewPage;

    @FindBy(css = "div.action-sign")
    private WebElement signButtonOnViewPage;

    @FindBy(css = "div.action-delete")
    private WebElement deleteButtonOnViewPage;

    public Boolean isSignButtonPresentOnViewPage(){
        return signButtonOnViewPage.isDisplayed();
    }

    public String getDocumentHeaderText(){
        return documentHeader.getText();
    }

}
