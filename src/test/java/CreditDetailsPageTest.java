import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import stoykovPages.*;
import stoykovUtils.DriverFactory;


public class CreditDetailsPageTest {
    private String treatyAccountNumber = "20630000000118.980";
    private String paymentCalendarPageTitle = "А24 Бізнес - Платіжний календар";

    private By creditTreatyWithDelayStatus = By.xpath("//a[text()='N20.00.004171 ']");
    private By creditTreatyActiveStatus = By.xpath("//a[text()='N20.00.004174 ']");

    private MainPage mainPage = new MainPage();
    private AllCreditsPage allCreditsPage = new AllCreditsPage();
    private CreditDetailsPage creditDetailsPage = new CreditDetailsPage();
    private StatementPage statementPage = new StatementPage();
    private CreditsOperationsHistoryPage creditsOperationsHistoryPage = new CreditsOperationsHistoryPage();
    private PartialRepaymentPage partialRepaymentPage = new PartialRepaymentPage();
    private PaymentCalendarPage paymentCalendarPage = new PaymentCalendarPage();

    @BeforeClass
    public void loginToPage() throws Exception {
        mainPage.loginAsTreatyUser();
    }

    @Test
    public void  checkTreatyStatement() {
        mainPage.openUserMainPage()
                .openAllCreditsPage()
                .openCreditDetailsPage(creditTreatyActiveStatus)
                .openTreatyStatementPage()
                .createOperStatement();
        Assert.assertTrue(statementPage.isCorrectAccountNumberInStatement(treatyAccountNumber), "Account number is not expected.\n");
    }

    @Test
    public void checkTreatyOperationsHistory(){
        mainPage.openUserMainPage()
                .openAllCreditsPage()
                .openCreditDetailsPage(creditTreatyActiveStatus)
                .openCreditsOperationsHistoryPage();
        Assert.assertTrue(creditsOperationsHistoryPage.isCorrectTreatyNumberInFilter("N20.00.004174"),"Treaty number on filter is not expected.\n");
    }

    @Test
    public void checkTreatyPaymentCalendar(){
        mainPage.openUserMainPage()
                .openAllCreditsPage()
                .openCreditDetailsPage(creditTreatyActiveStatus)
                .openPaymentCalendarPage();
        String actualTitle = paymentCalendarPage.getTitle();
        Assert.assertTrue(paymentCalendarPageTitle.equals(actualTitle), "Page title is not expected. \nExpected: " + paymentCalendarPageTitle + "\nActual: " + actualTitle);
    }

    @Test
    public void createCorrectPartialRepayment() {
        mainPage.openUserMainPage()
                .openAllCreditsPage()
                .openCreditDetailsPage(creditTreatyActiveStatus)
                .openPartialRepaymentPage()
                .createCorrectPartialRepayment();
    }

    @Test
    public void createPartialRepaymentWithEmptyFields(){
        mainPage.openUserMainPage()
                .openAllCreditsPage()
                .openCreditDetailsPage(creditTreatyActiveStatus)
                .openPartialRepaymentPage()
                .createPartialRepaymentWithEmptyFields();
        Assert.assertEquals(partialRepaymentPage.getErrorText(),"Рахунок не вказано");

    }

    @Test
    public void createPartialRepaymentWithEmptySumField(){
        mainPage.openUserMainPage()
                .openAllCreditsPage()
                .openCreditDetailsPage(creditTreatyActiveStatus)
                .openPartialRepaymentPage()
                .createPartialRepaymentWithEmptySumField();
        Assert.assertEquals(partialRepaymentPage.getErrorText(),"Сума документу повинна бути більше нуля");

    }

    @Test
    public void createPartialRepaymentWithEmptyAccountField(){
        mainPage.openUserMainPage()
                .openAllCreditsPage()
                .openCreditDetailsPage(creditTreatyActiveStatus)
                .openPartialRepaymentPage()
                .createPartialRepaymentWithEmptyAccountField();
        Assert.assertEquals(partialRepaymentPage.getErrorText(),"Рахунок не вказано");

    }

    @AfterClass
    public void tearDown(){
        DriverFactory.shutDownDriver();
    }
}
