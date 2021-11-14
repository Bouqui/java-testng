# java-testng

#INSTALLATION PROCESS
1. Download and install Intellij on https://www.jetbrains.com/idea/download/#section=windows
2. Download and install the latest version of Java on https://www.oracle.com/java/technologies/downloads/
3. Download and install all the browsers (Chrome, Edge, Firefox) required for the tests
4. Download and install chromedriver, edgedriver and firefoxdriver and save the files in a location that you can easily access
5. Copy and save all the dependencies for the browsers in the pom.xml file. The dependencies can be gotten from https://mvnrepository.com/artifact/org.seleniumhq.selenium

#PROJECT SETUP
1. Create a Maven project on Intellij and give it a name 
2. Create a separate folder to store all the browser drivers in the folder. In this case, the folder is named "resources"

#TEST FRAMEWORK SETUP
In order to adopt Page Object Model (POM) methodology, there is a need to create separate folders for the pages and the tests
1. Pages folder - This contains the framework and all actions such as locating an element, clicking an element
2. Test folder - This is where the test scenarios are stored

#RUNNING THE TESTS
For the purpose of this test, testNG was used for the test run 
1. Create a class (BaseTests) within the test>base folder to store all pre-requisites - such as 
   1. setup method for the browsers which include cross-browser feature 
   2. Before and After test methods 
   3. Screenshot method for capturing the test failures screenshots 
2. Write all functions (test actions) in a class named HomePage within the pages folder
3. Write all test scenarios in a class named HomeTests within the test>home folder
4. Create a file named TestNG.xml in the project directory and set the parameters that allow the test to be run across all browsers within the file 
5. To run the test, right-click on the TestNG.xml file and choose the option to run the file 

#SCREENSHOTS
Screenshots of all failed tests are stored in the resources>screenshots folder. 


