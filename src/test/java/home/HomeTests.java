package home;

import base.BaseTests;
import org.testng.annotations.Test;


//all tests in HomeTests will inherit from BaseTests as this is where the set up the browser and driver
//with this we don't have to set it up in all test files

@Test
public class HomeTests extends BaseTests {

    public void testBelgiumMeetings(){
        homePage.selectBelgium();
        homePage.clickUpdateBtn();
        homePage.verifyBelgiumInAllRows();
    }

    public void testActivisionVoteCard(){
        //homePage.selectBelgium();
        homePage.clickNextArrow();
    }
}
