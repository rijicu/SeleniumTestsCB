package stoykovPages;

import org.openqa.selenium.By;

/**
 * Created by stoykov on 24.05.2018.
 */
public class CreditDetailsPage extends AbstractPage {

    public String getMessageTextAboutDelay(){
        return getText(By.xpath("//span[@class='text']"));
    }
}
