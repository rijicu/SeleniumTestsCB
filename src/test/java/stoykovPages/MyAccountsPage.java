package stoykovPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stoykov on 15.11.2017.
 */
public class MyAccountsPage extends AbstractPage {

    private By accounts = By.xpath("//li[2]/p");
    private By myAccounts = By.xpath("//a[@href=\'/iTiny20_Night/Account/List\']");
    private By accountLink = By.xpath("//a[text()='26001200558866.980']");
    private By accountNumber = By.className("contentcol");
    private By accountsColumn = By.xpath("//a[text()=\"Рахунок\"]");
    private By firstAccountNumber = By.xpath("//*[@id=\"accounts\"]/table/tbody/tr[1]/td[2]/a");
    //By accountFilterField = By.id("account");
    private By branchNameLink = By.xpath("//div[text()='Lime Systems']");
    private By accountStatusField = By.xpath("//div[text()='Активний']");
    private By accountClientOwnerField = By.xpath("//div[text()='ФОП \"Стойков\"']");
    private By accountClientStateCodeField = By.xpath("//div[text()='39637259']");
    private By createPaymentButton = By.xpath("//p[text()='Створити платiж ']");
    private By accountHistoryButton = By.xpath("//p[text()='Iсторiя ']");
    private By accountStatementButton = By.xpath("//p[text()='Виписки ']");
    private By accountTurnsButton = By.xpath("//p[text()='Обороти ']");
    private By sendAccountDetailsButton = By.xpath("//p[text()='Відпр. реквізити ']");
    private By currencyField = By.xpath("//div[@class='rightcol']//span[text()='select']");
    private By currencyUAHinList = By.xpath("//*[@id=\"currencies_listbox\"]/li[1]/span/span");
    private By accountsListOnMyAccountsPage = By.xpath("//tbody[@role='rowgroup']//tr");
    private By firstRowInCurrencyColumn = By.xpath("");
    private By numbersOfItemsOnPageOption = By.xpath("//div[@id='accounts']//span[@class=\"k-select\"]");
    private By tenItemsInTableOption = By.xpath("//div[@class='k-animation-container']//li[text()='10']"); //By.cssSelector(".k-animation-container>div>ul>li");//By.xpath("//div[10]//li[text()=\"10\"]");
    private By twentyItemsInTableOption = By.xpath("//div[@class='k-animation-container']//li[text()='20']");
    private By accountNumberInStatement = By.xpath("//td[5]/div/div");

    @FindBy(xpath = "//div[@id='accounts']//span[@class=\"k-select\"]")
    private WebElement numbersItemsTable;


    @FindBy(id = "account")
    private WebElement accountFilterField;

    @FindBy(className = "action-apply")
    private WebElement filterApplyButton;

    @FindBy(name = "DebitAccountId_input")
    public WebElement debitAccountIdInput;

    @FindBy(id = "showclosed")
    private WebElement showClosedAccountsInput;

    @FindBy(id = "showarrested")
    private WebElement showArrestedAccountsInput;


/*    @Test
    public void testDropdown() {
        //Get the Dropdown as a Select using its name attribute
        waitUntilLoadingImageNotPresent();
        Select make = new Select(driver.findElement(By.xpath("./*//*[@id='accounts']/div/span[1]/span/select")));
        //Verify Dropdown does not support multiple selection
        //assertFalse(make.isMultiple());
        //Verify Dropdown has four options for selection
        //assertEquals(4, make.getOptions().size());
        //With Select class we can select an option in Dropdown using    //Visible Text
        make.selectByVisibleText("10");
    }*/


    public void clickOnNumItemsOption() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(numbersOfItemsOnPageOption)).click();
/*        if (numbersItemsTable.isEnabled()){
        numbersItemsTable.click();
        } else {
            Thread.sleep(2000);
        }*/
    }

    public void showArrestedAccountsInList(){
        openFilter();
        pressOnCleanFilterButton();
        accountFilterField.clear();
        accountFilterField.sendKeys("2620");
        showArrestedAccountsInput.click();
        filterApplyButton.click();
    }

    public void showClosedAccountsInList(){
       // waitUntilLoadingImageNotPresent();
       // selectTwentyItemsInTable();
        openFilter();
        pressOnCleanFilterButton();
        showClosedAccountsInput.click();
        filterApplyButton.click();
    }


    public void selectTwentyItemsInTable(){
        waitUntilLoadingImageNotPresent();
        selectOptionByXpath(numbersOfItemsOnPageOption,twentyItemsInTableOption);
    }

    public void selectTenItemsInTable(){
        waitUntilLoadingImageNotPresent();
        selectOptionByXpath(numbersOfItemsOnPageOption,tenItemsInTableOption);
    }

    public int getNumbersOfAccounts(){
        waitUntilLoadingImageNotPresent();
        return getElementsListOnPage(accountsListOnMyAccountsPage).size();
    }

    public void searchByAccountCurrency(){
        openFilter();
        selectOptionByXpath(currencyField,currencyUAHinList);
        filterApplyButton.click();
        waitUntilLoadingImageNotPresent();
    }

    public String searchAccountByAccNumber(String accNumber) {
        openFilter();
        accountFilterField.isDisplayed();
        accountFilterField.clear();
        accountFilterField.sendKeys(accNumber);
        filterApplyButton.click();
        waitUntilLoadingImageNotPresent();
        return getFirstAccountNumber();
    }

    public void openAccountDetails(){
       // clickAt(accounts);
       // clickAt(myAccounts);
        clickAt(accountLink);
    }

    public void checkAccountDetailsFieldsIsDisplayed(){
        //openAccountDetails();
        isElementPresent(accountNumber);
        isElementPresent(branchNameLink);
        isElementPresent(accountStatusField);
        isElementPresent(accountClientOwnerField);
        isElementPresent(accountClientStateCodeField);
    }

    public MyAccountsPage checkAccountStatementsFromDetailsPage(){
        openAccountDetails();
        clickAt(accountStatementButton);
        openFilter();
        //waitUntilElementPresent(By.xpath(".//*[@id='filter']/div/div[2]/div[3]/div[2]/div/div/span/span/input"));
        //wait.until(ExpectedConditions.attributeToBe(By.xpath("//input[@name='AccountId_input']"),"value","26001200558866.980 ФОП \"Стойков\""));
        waitForAccountFieldOnFilterNotEmpty();
        filterApplyButton.isEnabled();
        waitForVisibilityOf(By.className("action-apply"),5);
        filterApplyButton.click();
        //wait.until(ExpectedConditions.attributeToBe(By.name("AccountId_input").))
        //wait.until(ExpectedConditions.textToBe(By.xpath(".//*[@id='reportViewer']/div/div[2]/div[1]/div[3]/div[1]/div/div/div[1]/div/div[22]/div"),"26001200558866.980"));
        return this;
    }

    public boolean isCorrectAccountNumberInStatement(String correctAccountNumberInStatement){
        if (getText(accountNumberInStatement).equals(correctAccountNumberInStatement)){
            return true;
        }
        return false;
    }

    public PaymentsHistoryPage openPaymentsHistoryPageFromAccountDetailsPage(){
        openAccountDetails();
        clickAt(accountHistoryButton);
        //wait.until(ExpectedConditions.textToBe(By.cssSelector("span.non-emphasis-column"),"26001200558866.980"));
        return new PaymentsHistoryPage();
    }

    public CreatePaymentPage openCreateDocPageFromAccDetailsPage(){
        openAccountDetails();
        clickAt(createPaymentButton);
        //System.out.print(driver.getTitle());
        //wait.until(ExpectedConditions.attributeToBe(debitAccountIdInput,"value","26001200558866.980 ФОП \"Стойков\""));
        return new CreatePaymentPage();
    }

    public String getAccountNumberFromAccDetailsPage(){
        openAccountDetails();
        return driver.findElement(accountNumber).getText();
    }

    public List<String> accountsColumnSort(){
        List<String> accountsNumbers = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            waitUntilLoadingImageNotPresent();
            accountsNumbers.add(getFirstAccountNumber());
            clickAt(accountsColumn);
        }
        return accountsNumbers;
    }

    public void waitUntilElementPresentAndClick() {
        waitUntilLoadingImageNotPresent();
        clickAt(accountsColumn);
    }

    public void clickAtAcc() throws InterruptedException {
        Thread.sleep(2000);
        clickAt(accountsColumn);
    }

    public String getFirstAccountNumber(){
        return driver.findElement(firstAccountNumber).getText();
    }

    public String getFirstRowInListOnPage(By linkToFirstRow){
        return driver.findElement(linkToFirstRow).getText();
    }
}
