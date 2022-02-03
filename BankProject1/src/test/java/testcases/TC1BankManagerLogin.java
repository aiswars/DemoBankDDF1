package testcases;

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
	public void tc1BankManagerLogin() throws InterruptedException {
		
		 Thread.sleep(2000);
		log.debug("Inside BankMangerLogin "); //in logs folder - log4j
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
  	  //Assert.fail("Login not successful");// to make a test fail to check listeners-onTestFailure-screenshot
        
   		
}//loginAsBankManager() {
}
