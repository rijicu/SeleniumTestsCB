import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import stoykovPages.CreatePaymentPage;
import stoykovPages.MainPage;
import stoykovPages.MyAccountsPage;
import stoykovPages.PaymentsHistoryPage;
import stoykovUtils.DriverFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by stoykov on 15.11.2017.
 */
public class MyAccountsPageTest {
    WebDriver driver = DriverFactory.getDriver();
    private String accountNumber = "26001200558866.980";
    private String accountNumberOnCreatePaymentPage = "26001200558866.980 ФОП \"Стойков\"";
    private String accountStatementPageTitle = "iTiny - Виписка";
    private String accountHistoryPageTitle = "АБанк - Мої платежі";
    private String createDocumentPageTitle = "АБанк - Створити платіж";
    private List<String> firstAccountNumbers = new ArrayList<String>() {{
        add("2600458475.980");
        add("260008458455.840");
        add("26009845632365.980");}};
    private By currencyUsdLink = By.xpath("//div[@class='content']//td[text()='USD']");


    private MainPage mainPage = new MainPage();
    private MyAccountsPage accountsPage = new MyAccountsPage();
    private PaymentsHistoryPage paymentsHistoryPage = new PaymentsHistoryPage();
    private CreatePaymentPage createPaymentPage = new CreatePaymentPage();



    @BeforeClass
    public void loginToPage() throws Exception {
        mainPage.loginAsUser();
    }

/*    @Test
    public void clickOnNumOfItems() throws InterruptedException {
        mainPage.openUserMainPage()
                .openMyAccountsPage()
                .testDropdown();
    }*/

    @Test
    public void numbersOfAccountsOnMyAccountsPage() throws Exception {
        mainPage
                .openUserMainPage()
                .openMyAccountsPage()
                .selectTenItemsInTable();
        Assert.assertEquals(accountsPage.getNumbersOfAccounts(),10);

        accountsPage
                .selectTwentyItemsInTable();
        int accounts = accountsPage.getNumbersOfAccounts();
        Assert.assertTrue(accounts>10);
    }

    @Test
    public void accountsDetailsVerify() throws Exception {
        String accountNumberOnPage = mainPage
                .openUserMainPage()
                .openMyAccountsPage()
                .getAccountNumberFromAccDetailsPage();
        Assert.assertEquals(accountNumberOnPage, accountNumber);

        accountsPage.checkAccountDetailsFieldsIsDisplayed();
    }

    @Test
    public void accountsTableSort() throws Exception {
        List<String> firstAccountNumbersFromPage = mainPage
                .openUserMainPage()
                .openMyAccountsPage()
                .accountsColumnSort();

        Assert.assertEquals(firstAccountNumbersFromPage, firstAccountNumbers);
    }

    @Test
    public void searchAccountByAccountNumber() {
        String filteringAccountNumber = mainPage
                .openUserMainPage()
                .openMyAccountsPage()
                .searchAccountByAccNumber(accountNumber);
        Assert.assertEquals(filteringAccountNumber, accountNumber);
    }

    @Test
    public void searchAccountByAccountCurrency(){
        mainPage.openUserMainPage()
                .openMyAccountsPage()
                .searchByAccountCurrency();
            Assert.assertFalse(accountsPage.isElementPresent(currencyUsdLink));
    }

    @Test
    //not finished
    public void openCreateDocumentPageFromAccountDetailsPage() throws Exception {
       mainPage
                .openUserMainPage()
                .openMyAccountsPage()
                .openCreateDocPageFromAccDetailsPage()
                .waitForCreatePaymentPageToLoad();
       String actualTitle = createPaymentPage.getTitle();
       Assert.assertTrue(createDocumentPageTitle.equals(actualTitle), "Page title is not expected. \nExpected: " + createDocumentPageTitle + "\nActual: " + actualTitle);
       //assertEquals(createPaymentPage.getTitle(),"iTiny - Створити платіж");
       Assert.assertTrue(createPaymentPage.isCorrectAccountNumberOnCreatePaymentPage(accountNumberOnCreatePaymentPage), "Account number is not expected.\n");
       //System.out.print(debitAccountIdInput.getAttribute("value"));
       //assertEquals(debitAccountIdInput.getAttribute("value"),"26001200558866.980 ФОП \"Стойков\"");
    }

    @Test
    public void checkAccountHistoryFromAccountDetailsPage(){
        mainPage
                .openUserMainPage()
                .openMyAccountsPage()
                .openPaymentsHistoryPageFromAccountDetailsPage()
                .waitForPaymentsHistoryPageToLoad();
        Assert.assertTrue(paymentsHistoryPage.isCorrectAccountNumberOnPaymentsHistoryPage(accountNumber), "Account number is not expected.\n");
        //System.out.println(accountsPage.getText(By.cssSelector("span.non-emphasis-column")));
        String actualTitle = paymentsHistoryPage.getTitle();
        Assert.assertTrue(accountHistoryPageTitle.equals(actualTitle), "Page title is not expected. \nExpected: " + accountHistoryPageTitle + "\nActual: " + actualTitle);
        //assertEquals(driver.getTitle(), "iTiny - Мої платежі");
        //System.out.println(driver.getTitle());
    }

    @Test
    public void createAccountStatementsFromAccountDetailsPage(){
        mainPage
                .openUserMainPage()
                .openMyAccountsPage()
                .checkAccountStatementsFromDetailsPage();
        String actualTitle = accountsPage.getTitle();
        Assert.assertTrue(accountStatementPageTitle.equals(actualTitle), "Page title is not expected. \nExpected: " + accountStatementPageTitle + "\nActual: " + actualTitle);
        Assert.assertTrue(accountsPage.isCorrectAccountNumberInStatement(accountNumber), "Account number is not expected.\n");
        //assertEquals(driver.getTitle(),"iTiny - Виписка");
    }

    @Test
    public void showClosedAccountsInList(){
        mainPage
                .openUserMainPage()
                .openMyAccountsPage()
                .showClosedAccountsInList();
        Assert.assertTrue(accountsPage.isElementPresent(By.xpath("//span[contains(normalize-space(@class), 'icon icon-status-deletedClient')]")));
    }

    @Test
    public void showArrestedAccountsInList(){
        mainPage
                .openUserMainPage()
                .openMyAccountsPage()
                .showArrestedAccountsInList();
        Assert.assertTrue(accountsPage.isElementPresent(By.xpath("//span[contains(normalize-space(@class), 'icon icon-status-forbidden')]")));
    }


    @AfterClass
    public void tearDown(){
        DriverFactory.shutDownDriver();
    }
}
