package testcases;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import base.BaseClass;
import utilities.TestUtil;

public class TC2AddCustomer extends BaseClass{
//BankManager>> AddCustomer button
	
	//AddCustomerTest classname - give same name to excel sheetname-AddCustomerTest
		//@Test(dataProvider = "getData")
		//3 inputs to textboxes on AddCustomer page - so 3 parameters- from DataProvider
		//public void addCustomer(String firstName, Stirng LastName, Sring postcode)
		

	//@Test(dataProvider = "dp")   //ERROR ??
	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
	
	//public void TC2addCustomer(String fName, String lName, String postCode) {//pass no of columns in testdata.xls as parameters 
		
	//public void TC2addCustomer(String fName, String lName, String postCode, String alertText) {//pass no of columns in testdata.xls as parameters 
		
	public void tc2AddCustomer(Hashtable<String,String> data) throws InterruptedException { //store the data as (key,value)pair - (firstname,isha) (lastname,.....
				//addCustomerTest
		

		if(!data.get("runmode").equals("Y")){
			
			throw new SkipException("Skipping the test case as the Run mode for data set is NO"); //if runmode is N in testdata excelsheet that test will be executed - 2nd row  in excel sheet
		}
		
		
		
		click("addCustBtn_CSS");
		type("firstname_CSS",data.get("firstname"));
		type("lastname_XPATH",data.get("lastname"));
		type("postcode_CSS",data.get("postcode"));
		
		//click("addbtn_CSS"); 
		click("addCbtn_CSS"); //addbtn_CSS in OR.properties
		
	//	Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		//msg in alert box - alert ref of type Alert Class
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());//explcit wait
		Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
		Thread.sleep(2000);
		alert.accept();
		
		
		
		
//////////////////////////////////////////////////////////////////////////////////////////////////////
		 //data from excelsheet to dataprovider then to method parameters and directly accessing
//		driver.findElement(By.cssSelector(OR.getProperty("addCustBtn_CSS"))).click(); // OR.getProperty("addCustBtn_CSS") elementlocators are added in OR.properties file in src/test/resources/properties
//		driver.findElement(By.cssSelector(OR.getProperty("firstname_CSS"))).sendKeys(fName);
//		driver.findElement(By.xpath(OR.getProperty("lastname_XPATH"))).sendKeys(lName);
//		driver.findElement(By.cssSelector(OR.getProperty("postcode_CSS"))).sendKeys(postCode);
//		driver.findElement(By.cssSelector(OR.getProperty("addCbtn_CSS"))).click();
		
		//alert popups confirmation messages
		/*
		 * Alert alert = wait.until(ExpectedConditions.alertIsPresent());//explcit wait
		 * Assert.assertTrue(alert.getText().contains(alertText)); 
		 * alert.accept();
		 */
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////data from excelsheet to dataprovider storig to hashtable then to method parameters as hashtable
		/*
		 * driver.findElement(By.cssSelector(OR.getProperty("addCustBtn_CSS"))).click();
		 * driver.findElement(By.cssSelector(OR.getProperty("firstname_CSS"))).sendKeys(
		 * data.get("firstname")); //testdata.xlsx titles> firstname
		 * driver.findElement(By.xpath(OR.getProperty("lastname_XPATH"))).sendKeys(data.
		 * get("lastname"));
		 * driver.findElement(By.cssSelector(OR.getProperty("postcode_CSS"))).sendKeys(
		 * data.get("postcode"));
		 * driver.findElement(By.cssSelector(OR.getProperty("addCbtn_CSS"))).click();
		 * 
		 * //alert popups confirmation messages 
		 * Alert alert = wait.until(ExpectedConditions.alertIsPresent());//explcit wait
		 * Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
		 * alert.accept();
		 */
//////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	}
	
	/*
	 *  //DIDNT WORK THIS PART
	 * @DataProvider(name="dp") public Object[][] getData(Method m) {
	 * 
	 * String sheetName = m.getName(); //"AddCustomerTest - same as Classname" TC2AddCustomer
	 *  int	 * rows = excel.getRowCount(sheetName); int cols =
	 * excel.getColumnCount(sheetName);
	 * 
	 * Object[][] data = new Object[rows - 1][1];
	 * 
	 * Hashtable<String,String> table = null;
	 * 
	 * for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2 //data starts from row
	 * 2, rows is rowcount table = new Hashtable<String,String>();
	 * 
	 * for (int colNum = 0; colNum < cols; colNum++) {
	 * 
	 * // data[0][0] table.put(excel.getCellData(sheetName, colNum, 1),
	 * excel.getCellData(sheetName, colNum, rowNum)); data[rowNum - 2][0] = table;//
	 * data[rowNum - 2][0] to get data at [0][0] position- first time- data[0][0] }
	 * 
	 * }
	 * 
	 * return data;
	 * 
	 * }
	 * 
	 */
	
	
	
	
/*	
	@DataProvider(name="dp")
//	@DataProvider
	public Object[][] getData() {
		String sheetName = "TC2addCustomer"; //"TC2addCustomer - excel sheetname same as Classname"
		//String sheetName = m.getName(); // same as Classname"
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		System.out.println(sheetName);
	System.out.println(rows);
	System.out.println(cols);
	//	int rownum=XLUtils.getRowCount(path, "Sheet1");
	//	int colcount=XLUtils.getCellCount(path,"Sheet1",1);
	
		Object[][] data = new Object[rows - 1][1]; //??
		//Object[][] data = new Object[rows - 1][cols]; //?? diff ??
		Hashtable<String,String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2
//data starts from row 2, rows is rowcount
			table = new Hashtable<String,String>();
			
			for (int colNum = 0; colNum < cols; colNum++) {
				System.out.println("Colnum " + colNum);
				System.out.println("rownum " + rowNum);
				// data[0][0]
			table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;// data[rowNum - 2][0] to get data at [0][0] position- first time- data[0][0]
				//data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);// data[rowNum - 2][0] to get data at [0][0] position- first time- data[0][0]
	//	System.out.println(data[rowNum - 2][colNum] );
				//data[rowNum - 2][0] = excel.getCellData(sheetName, colNum, rowNum 1);// data[rowNum - 2][0] to get data at [0][0] position- first time- data[0][0]
			
			}

		}

		return data;
		

	}//getData@DataProvider
	*/
	
} //public class TC2AddCustomer() {
