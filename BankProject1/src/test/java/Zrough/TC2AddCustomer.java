package Zrough;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;

public class TC2AddCustomer extends BaseClass {
//BankManager>> AddCustomer button

	@Test(dataProvider = "getData")
	
	public void TC2addCustomer(String fName, String lName, String postCode) {//pass no of columns in testdata.xls as parameters 
		
	//public void TC2addCustomer(String fName, String lName, String postCode, String alertText) {//pass no of columns in testdata.xls as parameters 
			System.out.println(fName);
			System.out.println(lName);
			System.out.println(postCode);
		driver.findElement(By.cssSelector(OR.getProperty("addCustBtn_CSS"))).click();
		driver.findElement(By.cssSelector(OR.getProperty("firstname_CSS"))).sendKeys(fName);
		driver.findElement(By.xpath(OR.getProperty("lastname_XPATH"))).sendKeys(lName);
		driver.findElement(By.cssSelector(OR.getProperty("postcode_CSS"))).sendKeys(postCode);
		driver.findElement(By.cssSelector(OR.getProperty("addCbtn_CSS"))).click();
		//alert popups confirmation messages
		//Alert alert = wait.until(ExpectedConditions.alertIsPresent());//explcit wait
		//Assert.assertTrue(alert.getText().contains(alertText));
	//	alert.accept(); 
		
	}
	
//	@DataProvider(name="dp")
	@DataProvider
	public Object[][] getData() {
		String sheetName = "TC2addCustomer"; //"TC2addCustomer - excel sheetname same as Classname"
		//String sheetName = m.getName(); // same as Classname"
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
	System.out.println(rows);
	System.out.println(cols);
	//	int rownum=XLUtils.getRowCount(path, "Sheet1");
	//	int colcount=XLUtils.getCellCount(path,"Sheet1",1);
	
		//Object[][] data = new Object[rows - 1][1];
		Object[][] data = new Object[rows - 1][cols];
	//	Hashtable<String,String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2
//data starts from row 2, rows is rowcount
		//	table = new Hashtable<String,String>();
			
			for (int colNum = 0; colNum < cols; colNum++) {
				System.out.println("Colnum " + colNum);
				System.out.println("rownum " + rowNum);
				// data[0][0]
		//		table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
			//	data[rowNum - 2][0] = table;// data[rowNum - 2][0] to get data at [0][0] position- first time- data[0][0]
				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);// data[rowNum - 2][0] to get data at [0][0] position- first time- data[0][0]
			System.out.println(data[rowNum - 2][colNum] );
				//data[rowNum - 2][0] = excel.getCellData(sheetName, colNum, rowNum 1);// data[rowNum - 2][0] to get data at [0][0] position- first time- data[0][0]

			
			}

		}

		return data;

	}//getData@DataProvider
	
}
