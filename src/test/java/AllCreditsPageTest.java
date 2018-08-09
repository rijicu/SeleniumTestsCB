import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import stoykovPages.AllCreditsPage;
import stoykovPages.MainPage;
import stoykovUtils.DriverFactory;

/**
 * Created by stoykov on 01.06.2018.
 */
public class AllCreditsPageTest {


    private MainPage mainPage = new MainPage();
    private AllCreditsPage allCreditsPage = new AllCreditsPage();



    @BeforeClass
    public void loginToPage() throws Exception {
        mainPage.loginAsTreatyUser();
    }

    @Test
    public void openAllCreditsPage(){
        mainPage.openUserMainPage()
                .openAllCreditsPage();
        Assert.assertEquals(allCreditsPage.numbersOfTreatiesOnPage(), 10);

    }

    @Test
    public void findTreatyByAccNumber(){
        mainPage.openUserMainPage()
                .openAllCreditsPage()
                .findTreatyByAccountNumber();
        Assert.assertEquals(allCreditsPage.getAccountNumberOnTreatyList(),"20630000000118.980");

    }

    @AfterClass
    public void tearDown(){
        DriverFactory.shutDownDriver();
    }
}
