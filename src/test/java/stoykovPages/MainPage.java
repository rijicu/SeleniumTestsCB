package stoykovPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by stoykov on 27.10.2017.
 */
public class MainPage extends AbstractPage {
    private String baseUrl = "http://srv-cb-a/iTiny20_Night/";
    private String userLogin = "StoykovIV";
    private String userPass = "12345678";
    private String adminLogin = "admin";
    private String adminPass = "1";
    private String treatyUserLogin = "TumanovaNA";
    private String treatyUserPass = "12345678";

    @FindBy(id = "login")
    private WebElement loginFild;

    @FindBy(id = "password")
    private WebElement passwordFild;

    @FindBy(className= "entrance")
    private WebElement loginButton;

    @FindBy(className = "exit")
    private WebElement exitButton;

    @FindBy(className = "username")
    private WebElement usernameOnManePageFB;

    /*@FindBy(xpath = "//tbody[@role='rowgroup']//tr")
    private List<WebElement> accountsListOnMainPage;*/

    private By numbersOfItemsOnPageOption = By.xpath("//div[@class='accounts']//span[@class=\"k-select\"]");
    private By fiveItemsInTableOption = By.xpath("//li[text()=\"5\"]");
    private By accountsListOnMainPage = By.xpath("//tbody[@role='rowgroup']//tr");
    private By usernameOnManePage = By.className("username");
    private By accountsMenuLink = By.xpath("//li[2]/p");
    private By mainMenuLink = By.xpath("//a[@href=\'/iTiny20_Night/\']");
    private By myAccountsPageLink = By.xpath("//a[@href=\'/iTiny20_Night/Account/List\']");
    private By paymentsMenuLink = By.xpath("//span[contains(normalize-space(@class), 'icon icon-menu-payments')]");
    private By paymentsHistoryPageLink = By.xpath("//a[@href=\'/iTiny20_Night/Payment/List\']");
    private By creditsMenuLink = By.xpath("//li[5]/p");
    private By allCreditsPageLink = By.xpath("//a[@href=\'/iTiny20_Night/Treaty/CreditsList\']");
    private By statementMenuLink = By.xpath("//a[@href=\'/iTiny20_Night/Account/Extract\']");

    public void loginToiTiny(String login, String password){
        driver.get(baseUrl);
        loginFild.clear();
        loginFild.sendKeys(login);
        passwordFild.clear();
        passwordFild.sendKeys(password);
        loginButton.click();
    }

    public StatementPage openStatementPage(){
        clickAt(accountsMenuLink);
        waitForVisibilityOf(statementMenuLink);
        clickAt(statementMenuLink);
        return new StatementPage();
    }

    public MainPage openUserMainPage()  {
        //loginAsUser();
        clickAt(mainMenuLink);
        waitUntilLoadingImageNotPresent();
        //driver.get("http://srv-cb-a/iTiny20_Night/");
        return this;
    }

    public MyAccountsPage openMyAccountsPage(){
        //clickAt(mainMenuLink);
        clickAt(accountsMenuLink);
        clickAt(myAccountsPageLink);
        waitUntilLoadingImageNotPresent();
        //driver.findElement(accountsMenuLink).click();
        //driver.findElement(myAccountsPageLink).click();
        return new MyAccountsPage();
    }

    public AllCreditsPage openAllCreditsPage(){
        clickAt(creditsMenuLink);
        clickAt(allCreditsPageLink);
        waitUntilLoadingImageNotPresent();
        return new AllCreditsPage();
    }

    public PaymentsHistoryPage openPaymentsHistoryPage(){
        clickAt(paymentsMenuLink);
        clickAt(paymentsHistoryPageLink);
        return new PaymentsHistoryPage();
    }

    public int getNumbersOfAccounts(){
       //getElementsListOnPage(accountsListOnMainPage);
        //List<WebElement> aclist = driver.findElements(By.xpath("//tbody[@role='rowgroup']//tr"));
        waitUntilLoadingImageNotPresent();
        return getElementsListOnPage(accountsListOnMainPage).size();
        //System.out.println(getElementsListOnPage(accountsListOnMainPage).size());
    }

    public void loginAsTreatyUser()throws Exception{
        loginToiTiny(treatyUserLogin, treatyUserPass);
    }

    public void loginAsUser()throws Exception{
        loginToiTiny(userLogin, userPass);
    }

    public void loginAsAdmin()throws Exception{
        loginToiTiny(adminLogin, adminPass);
    }

    public void checkUserNamePresentOnManePage() throws Exception {
        //тут два варианта решени€. — поиском через By и через @FindBy
        isElementPresent(usernameOnManePage);
        //usernameOnManePageFB.isDisplayed();
    }

    public void logout() throws Exception{
        exitButton.click();
    }
}
