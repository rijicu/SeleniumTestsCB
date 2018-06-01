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
        mainPage.openAllCreditsPage();
    }

    @AfterClass
    public void tearDown(){
        DriverFactory.shutDownDriver();
    }
}
