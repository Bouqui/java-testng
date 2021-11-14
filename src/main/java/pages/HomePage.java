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
    private  By updateBtnID = By.id("btn-update");
    private By updateBtnXpath = By.xpath("/html/body/div[2]/div[2]/aside/div[4]/div/div[2]/div[2]/button[1]");
    private By rightArrowXpath=By.xpath("/html/body/div[2]/div[2]/article/div[2]/div[3]/a[3]/span");
    private By tableRowXpath=By.xpath("/html/body/div[2]/div[2]/article/div[2]/div[2]/table/tbody/tr");
    private By firstBelgiumLink= By.linkText("Aedifica NV");
    private By loadingCheckID= By.xpath("/html/body/div[2]/div[2]/article/div[2]/div[2]/table/tbody/tr[3]/td[4]");
    private By activisionLinkText = By.linkText("Activision Blizzard Inc");
    private By companyLabelID = By.id("detail-issuer-name");

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

    public void verifyBelgiumInAllRows() {
        //wait till the expected first row for Belgium meetings is displayed
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstBelgiumLink));

        //count the number of rows returned for all meetings in Belgium
        List<WebElement> rows = driver.findElements(tableRowXpath);
        System.out.println("Number of rows for Belgium meetings is: " +rows.size());


        for (WebElement trElement : rows) {
            List<WebElement> columns=trElement.findElements(By.xpath("td"));
            String countryDisplayed = columns.get(5).getText();
            System.out.println("country is : " + countryDisplayed);
            Assert.assertEquals(countryDisplayed, "Belgium");
        }

    }

    public void clickNextArrow(){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loadingCheckID));

        //scroll to the end of the page
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

        driver.findElement(rightArrowXpath).click();
        js.executeScript("window.scrollBy(0,-2500)", "");

        driver.findElement(activisionLinkText).click();

    }

    public void verifyActivision(){
        //wait till the company name gets displayed
        WebDriverWait wait = new WebDriverWait(driver, 5);
        WebElement companyName = wait.until(ExpectedConditions.visibilityOfElementLocated(companyLabelID));

        //store the company name in a variable and convert to string
        String actualCompanyName = companyName.getText();
        System.out.println("Displayed company name is: " + actualCompanyName);

        //compare the displayed company name and the expected company name
        Assert.assertEquals(actualCompanyName,"Actidvision Blizzard Inc");

    }
}
