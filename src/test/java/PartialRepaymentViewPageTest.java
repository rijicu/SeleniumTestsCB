import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import stoykovPages.*;
import stoykovUtils.DriverFactory;

/**
 * Created by stoykov on 08.12.2018.
 */
public class PartialRepaymentViewPageTest {

    private MainPage mainPage = new MainPage();
    private CreditsOperationsHistoryPage creditsOperationsHistoryPage = new CreditsOperationsHistoryPage();
    private PartialRepaymentPage partialRepaymentPage = new PartialRepaymentPage();
    private PartialRepaymentViewPage partialRepaymentViewPage = new PartialRepaymentViewPage();


    @BeforeClass
    public void loginToPage() throws Exception {
        mainPage.loginAsTreatyUser();
    }

    @Test
    public void deletePartialRepaymentOperationFromViewPage(){
        mainPage.openUserMainPage()
                .openCreditsOperationsHistoryPage()
                .openRequestToView()
                .deleteOperationFromViewPage();
        Assert.assertTrue(creditsOperationsHistoryPage.isTreatyDeleteStatusIconInList(),
                "Treaty status is not correct after delete action!\n");
        //Assert.assertTrue(partialRepaymentViewPage.isNotifyPresent());

    }

    @Test(dependsOnMethods = "deletePartialRepaymentOperationFromViewPage")
    public void recoverPartialRepaymentOperationFromViewPage() {
        mainPage.openUserMainPage()
                .openCreditsOperationsHistoryPage()
                .openRequestToView()
                .recoverOperationFromViewPage();
        Assert.assertTrue(creditsOperationsHistoryPage.isTreatyNewStatusIconInList(),
                "Treaty status is not correct after recover action!\n");
    }

    @Test
    public void editPartialRepaymentOperationFromViewPage(){
        mainPage.openUserMainPage()
                .openCreditsOperationsHistoryPage()
                .openRequestToView()
                .editOperationFromViewPage()
                .editRequestSum("1505");
        String actualTreatyAmount = creditsOperationsHistoryPage.getTreatyAmount();
        Assert.assertTrue(actualTreatyAmount.equals("1 505,00"),
                "Treaty amount is not expected. \nExpected: 1 505,00" + "\nActual: " + actualTreatyAmount + "\n");
    }


    @AfterClass
    public void tearDown(){
        DriverFactory.shutDownDriver();
    }
}
