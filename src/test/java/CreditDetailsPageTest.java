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
    private String creditsOperationsHistoryPageTitle = "А24 Бізнес - Історія операцій";
    private String creditTreatyType = "Часткове погашення";
    private String creditTreatyCode = "N20.00.004174";
    private String expectedDocumentHeaderText = "Часткове погашення кредиту N20.00.004174 вiд 18.01.2018";
    private String expectedTreatyHeaderText = "Деталі кредиту : N20.00.004174";

    private By creditTreatyWithDelayStatus = By.xpath("//a[text()='N20.00.004171 ']");
    private By creditTreatyActiveStatus = By.xpath("//a[text()='N20.00.004174 ']");

    private MainPage mainPage = new MainPage();
    private AllCreditsPage allCreditsPage = new AllCreditsPage();
    private CreditDetailsPage creditDetailsPage = new CreditDetailsPage();
    private StatementPage statementPage = new StatementPage();
    private CreditsOperationsHistoryPage creditsOperationsHistoryPage = new CreditsOperationsHistoryPage();
    private PartialRepaymentPage partialRepaymentPage = new PartialRepaymentPage();
    private PaymentCalendarPage paymentCalendarPage = new PaymentCalendarPage();
    private PartialRepaymentViewPage partialRepaymentViewPage = new PartialRepaymentViewPage();

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
        Assert.assertTrue(paymentCalendarPageTitle.equals(actualTitle), "Page title is not expected. \nExpected: " + paymentCalendarPageTitle + "\nActual: " + actualTitle + "\n");
    }

    @Test
    public void createCorrectPartialRepayment() {
        mainPage.openUserMainPage()
                .openAllCreditsPage()
                .openCreditDetailsPage(creditTreatyActiveStatus)
                .openPartialRepaymentPage()
                .createCorrectPartialRepayment();
        String actualTitle = creditsOperationsHistoryPage.getTitle();
        Assert.assertTrue(creditsOperationsHistoryPageTitle.equals(actualTitle), "Page title is not expected. \nExpected: " + creditsOperationsHistoryPageTitle + "\nActual: " + actualTitle + "\n");
        String actualTreatyCodeInRequestsList = creditsOperationsHistoryPage.getTreatyNumberFromOperationsPage();
        Assert.assertEquals(actualTreatyCodeInRequestsList, creditTreatyCode, "Treaty code is not expected. \nExpected: " + creditTreatyCode + "\nActual: " + actualTreatyCodeInRequestsList + "\n");
        String actualTreatyTypeInRequestsList = creditsOperationsHistoryPage.getTreatyTypeFromOperationsPage();
        Assert.assertTrue(creditTreatyType.equals(actualTreatyTypeInRequestsList),"Treaty type is not expected. \nExpected: " + creditTreatyType + "\nActual: " + actualTreatyTypeInRequestsList + "\n");
    }

    @Test(dependsOnMethods = "createCorrectPartialRepayment")
    public void editCreditRequestPartialRepayment(){
        mainPage.openUserMainPage()
                .openCreditsOperationsHistoryPage()
                .clickRequestEditButton()
                .editRequestSum("1501");
        String actualTitle = creditsOperationsHistoryPage.getTitle();
        Assert.assertTrue(creditsOperationsHistoryPageTitle.equals(actualTitle), "Page title is not expected. \nExpected: " + creditsOperationsHistoryPageTitle + "\nActual: " + actualTitle + "\n");
        String actualTreatyAmount = creditsOperationsHistoryPage.getTreatyAmount();
        Assert.assertTrue(actualTreatyAmount.equals("1 501,00"), "Treaty amount is not expected. \nExpected: 1 501,00" + "\nActual: " + actualTreatyAmount + "\n");
    }

    @Test(dependsOnMethods = "editCreditRequestPartialRepayment")
    public void viewCreditRequestPartialRepayment(){
        String documentHeaderText =
        mainPage.openUserMainPage()
                .openCreditsOperationsHistoryPage()
                .openRequestToView()
                .getDocumentHeaderText();
        Assert.assertTrue(documentHeaderText.equals(expectedDocumentHeaderText),"Document Header Text is not expected. \nExpected: " + expectedDocumentHeaderText + "\nActual: " + documentHeaderText + "\n");
        Assert.assertTrue(partialRepaymentViewPage.isSignButtonPresentOnViewPage(),"Sign button is not present on view page!\n");
        Assert.assertTrue(partialRepaymentViewPage.isEditButtonPresentOnViewPage(),"Edit button is not present on view page!\n");
        Assert.assertTrue(partialRepaymentViewPage.isDeleteButtonPresentOnViewPage(),"Delete button is not present on view page!\n");
    }

    @Test(dependsOnMethods = "createCorrectPartialRepayment")
    public void openTreatyDetailsPageFromOperationsHistoryPage(){
        mainPage.openUserMainPage()
                .openCreditsOperationsHistoryPage()
                .clickToTreatyCodeLinkInList();
        Assert.assertTrue(creditDetailsPage.isTreatyNumberPresentOnDetailsPage(), "Treaty number is not present on treaty details page!\n");
        Assert.assertEquals(creditDetailsPage.getDocumentHeaderText(), expectedTreatyHeaderText);

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
