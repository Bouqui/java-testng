package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class HomePage {

    //this is done in order to be able to use findElement and also to interact with the web page
    private WebDriver driver;
    JavascriptExecutor js =(JavascriptExecutor) driver;

    //This is to declare all the locators needed for the home page
    private  By belgiumCheckBoxID = By.id("Belgium-cb-label-CountryFilter");
    private By searchFieldID = By.id("kendo-Search-for-company");
    private By countryFieldID = By.id("txt-multiselect-static-search-CountryFilter");
    private  By updateBtnID = By.id("btn-update");

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

        js.executeScript("arguments[0].scrollIntoView();",updateBtnID );
        driver.findElement(updateBtnID).click();

    }
}
