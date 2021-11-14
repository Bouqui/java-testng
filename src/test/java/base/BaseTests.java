package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;
import pages.HomePage;
import org.testng.ITestResult;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;

import java.util.concurrent.TimeUnit;

public class BaseTests {

    private WebDriver driver;
    protected HomePage homePage;



    @BeforeMethod
    @Parameters({   "browser"})
    public void setUp(String browser) throws Exception {
        //this conditional block is used for cross browser test
        if (browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "resources/chromedriver1.exe");
            driver = new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "resources/msedgedriver.exe");
            driver = new EdgeDriver();
        }
        else {
            throw new Exception("Browser is not available");
        }
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        driver.get("https://viewpoint.glasslewis.com/WD/?siteId=DemoClient"); //this will launch the browser

        homePage = new HomePage(driver);
        //to maximize the window
        driver.manage().window().maximize();
        //to print the title of the url
        System.out.println(driver.getTitle());

    }

    @AfterTest
    public void tearDown(){
        driver.quit(); //this is to close the browser after the test run
    }

    @AfterMethod  //this will run after each test run. This is used to take the screnshot of only failed tests
    public void recordFailure(ITestResult result) {
        //use iTestResult.Failure because we are only interested in taking screenshots of failed tests
        if (ITestResult.FAILURE == result.getStatus()) {
            var camera = (TakesScreenshot) driver; //TakesScreenshot is in screenshot package
            File screenshot = camera.getScreenshotAs(OutputType.FILE); //the screenshot will be saved as a file

            //this is to get the path where the screenshot is stored
            System.out.println("Screenshot taken for failed test: " + screenshot.getAbsolutePath());

            //this is to move the screenshot file to another directory within the project for easy accessiblity
            try {
                //result.getName gets the name of the failed test and stores it in the screenshots folder
                Files.move(screenshot, new File("resources/screenshots/" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
