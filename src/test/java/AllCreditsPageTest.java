import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import stoykovPages.AllCreditsPage;
import stoykovPages.CreditDetailsPage;
import stoykovPages.MainPage;
import stoykovUtils.DriverFactory;

/**
 * Created by stoykov on 01.06.2018.
 */
public class AllCreditsPageTest {
    private By creditTreatyWithDelayStatus = By.xpath("//a[text()='N20.00.004171 ']");
    private By creditTreatyActiveStatus = By.xpath("//a[text()='N20.00.004174 ']");


    private MainPage mainPage = new MainPage();
    private AllCreditsPage allCreditsPage = new AllCreditsPage();
    private CreditDetailsPage creditDetailsPage = new CreditDetailsPage();



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

    @Test
    public void openTreatyWithDelayDetailsPage(){
        mainPage.openUserMainPage()
                .openAllCreditsPage()
                .openCreditDetailsPage(creditTreatyWithDelayStatus);
        //System.out.println(creditDetailsPage.getMessageTextAboutDelay());
        Assert.assertEquals(creditDetailsPage.getMessageTextAboutDelay(), "За договором є прострочення! Зверніться в банк для уточнення суми штрафних санкцій, що підлягають додатковій оплаті." );
    }

    @Test
    public void openActiveTreatyDetailsPage() {
        mainPage.openUserMainPage()
                .openAllCreditsPage()
                .openCreditDetailsPage(creditTreatyActiveStatus);
        Assert.assertTrue(creditDetailsPage.isTreatyNumberPresentOnDetailsPage(), "Treaty number is not present on treaty details page!\n");
    }

    @Test
    public void changeTreatyName() {
        mainPage.openUserMainPage()
                .openAllCreditsPage()
                .openCreditDetailsPage(creditTreatyActiveStatus)
                .changeTreatyLocalName();
        mainPage.openAllCreditsPage();
        Assert.assertTrue(allCreditsPage.isCorrectTreatyName(), "Treaty name not changed!\n");
    }

    @Test
    public void setTreatyNewName() {
        mainPage.openUserMainPage()
                .openAllCreditsPage()
                .openCreditDetailsPage(creditTreatyActiveStatus)
                .setTreatyNewLocalName();
        mainPage.openAllCreditsPage();
        Assert.assertTrue(allCreditsPage.isNewTreatyNameCorrect(), "New Treaty name not set!\n");
    }


    @AfterClass
    public void tearDown(){
        DriverFactory.shutDownDriver();
    }
}
