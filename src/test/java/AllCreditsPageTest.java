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
    private By creditTreatyActiveStatus = By.xpath("//a[text()='N20.00.004172 ']");


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
    public void openTreatyDetailsPage(){
        mainPage.openUserMainPage()
                .openAllCreditsPage()
                .openCreditDetailsPage(creditTreatyWithDelayStatus);
        //System.out.println(creditDetailsPage.getMessageTextAboutDelay());
        Assert.assertEquals(creditDetailsPage.getMessageTextAboutDelay(), "�� ��������� � ������������! ���������� � ���� ��� ��������� ���� �������� �������, �� ��������� ��������� �����." );
    }

    @AfterClass
    public void tearDown(){
        DriverFactory.shutDownDriver();
    }
}
