
import org.testng.annotations.*;
import org.openqa.selenium.*;

public class SecurityAddIPVerify extends StoykovTestBase{

  @Test (priority = 1, enabled = true)
  public void testSecurityAddIPVerify() throws Exception {
    loginAndFillIpAddressForm("192.168.123.119");
    checkIsUserCanLogin(By.cssSelector("span.text"), "������� ���� ��� ������. ��������� �����!");
    loginAsAdminAndClearUserIpForm();
    userLoginAndCheckMainePageIsOpen();
  }

  @Test (priority = 2, enabled = true)
  public void testSecurityAddCorrectIPVerify() throws Exception {
    loginAndFillIpAddressForm("192.168.123.118");
    userLoginAndCheckMainePageIsOpen();
    loginAsAdminAndClearUserIpForm();
  }

  @Test (priority = 3, enabled = false)
  public void testSecuritySendDocFromWrongIP() throws Exception {
    userLogin("StoykovIV", "12345678");
    goToProfileSecurityPage();
    fillIpAdresForm("192.168.123.119");
    verifyText(By.cssSelector("div.data-ip.ipData"), "192.168.123.119");
    driver.findElement(By.id("loginIp")).click();
    driver.findElement(By.id("docIp")).click();
/*    userLogOff();
    userLogin("StoykovIV", "12345678");
    // ��������� ��� ����� �������
    verifyText(By.cssSelector("span"), "³����, ������� ���� ����������!");
    userLogOff();
    userLogin("admin","1");
    goToUsersPageFromAdmin();
    findUserByLogin("StoykovIV");
    deleteBindedIPAddressOnUser();
    userLogOff();*/
  }

}
