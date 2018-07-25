package stoykovPages;


import org.openqa.selenium.By;

/**
 * Created by stoykov on 24.05.2018.
 */
public class AllCreditsPage extends AbstractPage {

    private By creditTreatysLinks = By.className("action-link");



    public int numbersOfTreatiesOnPage(){
       //return driver.findElements(creditTreatysLinks).size();
        return getElementsListOnPage(creditTreatysLinks).size();
    }



}
