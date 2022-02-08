package testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.BaseClass;


//log.debug --log4j
//Reporter.log("Reporter.log: 

public class TC1BankManagerLogin extends BaseClass{
//	public class TC1Login extends BaseClass{
	
	@Test
	public void tc1BankManagerLogin() throws InterruptedException, IOException {
		
		
		verifyEquals("abc", "xyz"); //userdefined method in base class to verify instead of Assert.assertEquals(abc,xyz) to continue this testecase execution after an error-like soft assertion
		//without using testng assertions
		Thread.sleep(2000);
		
		 // Assert.assertEquals("abc","xyz"); 
	
		 //assertions
		 
			/*
			 * // Assert.assertEquals("abc","xyz"); //to check the title-sample
			 * 
			 * 
			 * try { Assert.assertEquals("abc","xyz"); // like to check the title-
			 * System.out.println("after assertion-titlec check"); //this wont execute if
			 * assertion fails-catch gets executed but rest of the test gets terminated.
			 * control goes to next testcase/@test //so use soft assertions- this will
			 * report the error also - to continue the testcsse }catch(Throwable t) {
			 * System.out.
			 * println("Assert.assertEquals(abc,xyz) failed - check-inside catch"); }
			 * System.out.
			 * println("Assert.assertEquals(abc,xyz) failed sample - check-outside catch"
			 * );//this wont gets executes if assertion fails //so use soft assertions- to
			 * continue this test
			 * System.out.println("Assert.assertEquals(abc,xyz) failed- BUT  bmlBtn_CSS is clicked -WHY??? outside catch");//this wont gets executes if assertion fails
		
			 */
	
	
	
	
	
	log.debug("Inside BankMangerLogin "); //in logs folder - log4j
		log.info("Inside BankMangerLogin "); //why log.debug is not working??
		click("bmlBtn_CSS"); //driver.findElement(By.cssSelector(OR.getProperty("bmlBtn_CSS"))).click();
		
     // Assert.assertTrue(false, null);
		 Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))),"Login not successful");
	// Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))));
							   //isElementPresent method in testbase to check element is present or not
							  		//OR.getProperty - css locator Add customer btn named as addCustBtn_CSS in OR properties file
		 log.debug("Login successfully executed!!");

  
    //TestNG ReportNG >>> classes in <listeners> tag in testNgxml file
  		System.setProperty("org.uncommons.reportng.escape-output","false");  //to generate html output>> instead of plain text in html report of TestNG ReportNG
  		 
  		//TestNG Reporter >>>  just import org.testng.Reporter;
  		//-------------------------------------------------------
  		//Reporter.log("<a href=\"F:\\screenshot\\error.jpg\" target=\"_blank\">Screenshot link</a>");
  		
  		//Reporter.log("<br>");
  		//Reporter.log("<a href=\"F:\\screenshot\\error.jpg\" target=\"_blank\"><img height=200 width=200 src=\"F:\\screenshot\\error.jpg\"></a>");
  	
  		Reporter.log("Reporter.log: Failed test>> Screenshot link");//to print in emailablereport.html
  		Reporter.log("Reporter.log  Screenshot link "); //to print in emailablereport.html
  	
  		//to add a link to screenshot location C:\\automation\\screenshots\\sample.png
  		Reporter.log("  <a href=\"C:\\automation\\screenshots\\sample.png\">   Screenshotlink opening same window </a>"); //screenshot link in emailablereport.html
  	
  		//link to open in new wiwndow>>   target=\"_blank\" 
  		Reporter.log("  <a href=\"C:\\automation\\screenshots\\sample.png\" target=\"_blank\">   Screenshotlink opening in new window </a>"); //opening screenshot in new window
  		
  		Reporter.log("<br>");//break- to get a newline
  		//adding a thumbnail, instead of text.
  		Reporter.log("  <a href=\"C:\\automation\\screenshots\\sample.png\" target=\"_blank\">  <img height=200 width=200 src=\"C:\\automation\\screenshots\\sample.png\">    </a>");
  	 
  		//Reporter.log("  <a href=\"F:\\screenshot\\error.jpg\"               target=\"_blank\"> <img height=200 width=200 src=\"F:\\screenshot\\error.jpg\"> </a>");
  		
  	    Reporter.log("loginAsBankManager-Login successfully executed");
  	    

		//Assert.fail("Login not successful");//to makke a test fail to get screenshot-ontestfailure() in listenersjava
		
		//capture screenshot in basetest
		
  	 //to make a test fail
  	  Assert.fail("make a test fail - not successful");// to make a test fail to check listeners-onTestFailure-screenshot
        System.out.println("to make a test fail"); //this wont gets executed as assertion failed above line- control goes to next @test
   		
}//loginAsBankManager() {
}
