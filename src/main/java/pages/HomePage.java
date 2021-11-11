package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import java.util.List;




public class HomePage {

    //this is done in order to be able to use findElement and also to interact with the web page
    private WebDriver driver;
    JavascriptExecutor js =(JavascriptExecutor) driver;

    //This is to declare all the locators needed for the home page
    private  By belgiumCheckBoxID = By.id("Belgium-cb-label-CountryFilter");
    private By searchFieldID = By.id("kendo-Search-for-company");
    private By countryFieldID = By.id("txt-multiselect-static-search-CountryFilter");
    private  By updateBtnID = By.id("btn-update");
    private By updateBtnXpath = By.xpath("/html/body/div[2]/div[2]/aside/div[4]/div/div[2]/div[2]/button[1]");
    private By rightArrowClass=By.className("k-icon k-i-arrow-60-right");
    private By rightArrowXpath=By.xpath("//*[@id='grid']/div[3]/a[3]");
    private By tableRowXpath=By.xpath("/html/body/div[2]/div[2]/article/div[2]/div[2]/table/tbody/tr");
    private By firstBelgiumLink= By.linkText("Aedifica NV");
    private By rowItemXpath = By.xpath("/html/body/div[2]/div[2]/article/div[2]/div[2]/table/tbody/tr[1]/td");

    //create a constructor for the driver
    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void selectBelgium(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement searchElement = wait.until(ExpectedConditions.visibilityOfElementLocated(belgiumCheckBoxID));
        searchElement.click();
    }

    public void clickUpdateBtn(){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");//this is to scroll down the page vertically by 500pixels
        driver.findElement(updateBtnXpath).click();

    }

    public void verifyBelgiumInAllRows(){
        //wait till the expected first row for Belgium meetings is displayed
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstBelgiumLink));

        //count the number of rows returned for all meetings in Belgium
        List<WebElement> rows = driver.findElements(tableRowXpath);
        System.out.println(rows.size());

        //check that only meetings in Belgium are displayed on the table
        List <WebElement> rowItem = driver.findElements(rowItemXpath);

        WebElement cell2 = rowItem.get(5);
        String countryDisplayed = cell2.getText();
        System.out.println("country is : " +countryDisplayed);
        Assert.assertEquals(countryDisplayed, "Belgium");

       /* for(WebElement tdElement : rowItem) {
            System.out.println("text=" + tdElement.getText());
        }*/
    }

    public void clickNextArrow(){
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//scroll to the end of the page

    }
}
