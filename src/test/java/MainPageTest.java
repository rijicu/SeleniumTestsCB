import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import stoykovPages.MainPage;
import stoykovUtils.DriverFactory;

/**
 * Created by stoykov on 30.10.2017.
 */
public class MainPageTest {
    private MainPage mainPage = new MainPage();

    private By numbersOfItemsOnPageOption = By.xpath("//div[@class='accounts']//span[@class=\"k-select\"]");
    private By fiveItemsInTableOption = By.xpath("//div[@class='k-animation-container']//li[text()='5']");
    private By tenItemsInTableOption = By.xpath("//div[@class='k-animation-container']//li[text()='10']");
    private By twentyItemsInTableOption = By.xpath("//div[@class='k-animation-container']//li[text()='20']");


/*    @BeforeClass
    public void loginToPage() throws Exception {
        mainPage.loginAsUser();
    }*/


    @Test
    public void checkUserMainPageIsOpen() throws Exception {
        mainPage.loginAsUser();
        mainPage.checkUserNamePresentOnManePage();
        mainPage.logout();
    }

    @Test
    public void checkAdminMainPageIsOpen() throws Exception {
        mainPage.loginAsAdmin();
        mainPage.checkUserNamePresentOnManePage();
        mainPage.logout();
    }

    @Test
    public void numbersOfAccountsOnMainPage() throws Exception {
        mainPage.loginAsUser();
                mainPage.waitUntilLoadingImageNotPresent();
               mainPage.selectOptionByXpath(numbersOfItemsOnPageOption,fiveItemsInTableOption);
/*        int accounts = mainPage.getNumbersOfAccounts();
        System.out.println(accounts);*/
        Assert.assertEquals(5,mainPage.getNumbersOfAccounts());
        mainPage
                .openUserMainPage()
                .waitUntilLoadingImageNotPresent();
                mainPage.selectOptionByXpath(numbersOfItemsOnPageOption,tenItemsInTableOption);
        mainPage.waitUntilLoadingImageNotPresent();
/*        int accounts1 = mainPage.getNumbersOfAccounts();
        System.out.println(accounts1);*/
        Assert.assertEquals(mainPage.getNumbersOfAccounts(),10);

        mainPage
                .openUserMainPage()
                .waitUntilLoadingImageNotPresent();
                mainPage.selectOptionByXpath(numbersOfItemsOnPageOption,twentyItemsInTableOption);
        mainPage.waitUntilLoadingImageNotPresent();
        int accounts = mainPage.getNumbersOfAccounts();
        Assert.assertTrue(accounts>10);

    }

    @AfterClass
    public void tearDown(){
        DriverFactory.shutDownDriver();
    }
}
