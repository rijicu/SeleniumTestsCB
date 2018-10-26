import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import stoykovPages.*;
import stoykovUtils.DriverFactory;

/**
 * Created by stoykov on 26.10.2018.
 */
public class CreditDetailsPageTest {
    private By creditTreatyWithDelayStatus = By.xpath("//a[text()='N20.00.004171 ']");
    private By creditTreatyActiveStatus = By.xpath("//a[text()='N20.00.004174 ']");

    private MainPage mainPage = new MainPage();
    private AllCreditsPage allCreditsPage = new AllCreditsPage();
    private CreditDetailsPage creditDetailsPage = new CreditDetailsPage();
    private StatementPage statementPage = new StatementPage();
    private CreditsOperationsHistoryPage creditsOperationsHistoryPage = new CreditsOperationsHistoryPage();

    @BeforeClass
    public void loginToPage() throws Exception {
        mainPage.loginAsTreatyUser();
    }

    @Test
    public void createPartialRepayment(){
        mainPage.openUserMainPage()
                .openAllCreditsPage()
                .openCreditDetailsPage(creditTreatyActiveStatus)
                .openPartialRepaymentPage();

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
    }


    @AfterClass
    public void tearDown(){
        DriverFactory.shutDownDriver();
    }
}
