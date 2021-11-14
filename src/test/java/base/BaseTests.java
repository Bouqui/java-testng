package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pages.HomePage;
import org.testng.ITestResult;
import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;

import java.util.concurrent.TimeUnit;

public class BaseTests {

    private WebDriver driver;
    protected HomePage homePage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver1.exe");
        //specify the type of driver to use by instantiating the driver object
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        driver.get("https://viewpoint.glasslewis.com/WD/?siteId=DemoClient"); //this will launch the browser

        homePage = new HomePage(driver);
        //to maximize the window
        driver.manage().window().maximize();
        //to print the title of the url
        System.out.println(driver.getTitle());

    }

    @AfterClass
    public void tearDown(){
        //driver.quit(); //this is to close the browser after the test run
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
                Files.move(screenshot, new File("resources/screenshots/" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
