import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import stoykovPages.MainPage;
import stoykovPages.StatementPage;
import stoykovUtils.DriverFactory;

/**
 * Created by stoykov on 29.08.2018.
 */
public class StatementPageTest {

    private MainPage mainPage = new MainPage();
    private StatementPage statementPage = new StatementPage();

    @BeforeClass
    public void loginToPage() throws Exception {
        mainPage.loginAsUser();
    }

    @Test
    public void createStatementFull(){
        mainPage
                .openStatementPage()
                .createOperStatement();

       Assert.assertTrue(statementPage.isCorrectClientOnPage());
    }

    @Test(enabled = false)
    public void createStatementMany(){
        mainPage
                .openStatementPage()
                .createStatementManyTimes();
    }


    @AfterClass
    public void tearDown(){
        DriverFactory.shutDownDriver();
    }


}
