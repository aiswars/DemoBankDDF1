package testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.BaseClass;
import utilities.TestUtil;


public class TC3OpenAccount extends BaseClass {
	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	public void tc3OpenAccount(Hashtable<String,String> data) throws InterruptedException {
				//openAccountTest
	
		//runmode in excel sheetname tc3OpenAccount >> - listener.java-onTestStart
		//now runmode is No for TC3OpenAccount
		//skiping the test based on a condition so its not handling in listener onTestStart
		if(!(TestUtil.isTestRunnable("tc3OpenAccount", excel))){
			
			throw new SkipException("Skipping the test "+"openAccountTest".toUpperCase()+ "as the Run mode is NO");
		}
		
		
		click("openaccount_CSS");
		select("customer_CSS", data.get("customer"));
		select("currency_CSS", data.get("currency"));
		click("process_CSS");
		Thread.sleep(2000);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();

	}
}
