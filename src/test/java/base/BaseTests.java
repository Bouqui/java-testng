package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.HomePage;

public class BaseTests {

    private WebDriver driver;
    protected HomePage homePage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver1.exe");
        //specify the type of driver to use by instantiating the driver object
        driver = new ChromeDriver();
        driver.get("https://viewpoint.glasslewis.com/WD/?siteId=DemoClient"); //this will launch the browser
        homePage = new HomePage(driver);
        //to maximize the window
        driver.manage().window().maximize();
        //to print the title of the url
        System.out.println(driver.getTitle());
    }

    @AfterClass
    public void tearDown(){
        driver.quit(); //this is to close the browser after the test run
    }

}
