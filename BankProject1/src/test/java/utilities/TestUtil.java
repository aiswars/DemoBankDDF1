package utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;



import base.BaseClass;

public class TestUtil extends BaseClass {

	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() throws IOException {

		File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		System.out.println(sourceFile);
		Date d = new Date();
		//screenshotName = "error";
		//Report.log "\\target\\surefire-reports\\html\\" + screenshotName));//the screenshot for all the failed tests get overwritten as the name is hardcoded, hence use timestamp
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";//screenshot with the timestamp
		System.out.println(screenshotName);
		FileUtils.copyFile(sourceFile,	new File(System.getProperty("user.dir") + "\\sshots" + screenshotName));
		
		FileUtils.copyFile(sourceFile,	new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));
		FileUtils.copyFile(sourceFile,	new File(".\\reports\\" + screenshotName));
	//	FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+ "/target/surefire-reports/html/"+screenshotName));
	}
	
	
	
//below code >> SAME CODE - AS ABOVE>> not required for this project - its from the latest updates extentreports -extentManager-ExtentListeners
	public static void captureElementScreenshot(WebElement element) throws IOException {
		
		Date d = new Date();
		String fileName = d.toString().replace(":", "_").replace(" ", "_")+".jpg";

		
		
		File screeshot = ((TakesScreenshot) element).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screeshot, new File(".//screenshot//"+"Element_"+fileName));
	}

 
	
	
	
	

	@DataProvider(name="dp")
	public Object[][] getData(Method m) {

		String sheetName = m.getName(); //"AddCustomerTest - same as Classname"
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1]; //col is 1 as Hashtable is used here- always the column is 1-as the method tc2AddCustomer(Hashtable<String,String> data parameter
		//always the column is 1-as the method tc2AddCustomer(Hashtable<String,String> data) is keeping only one parameter- title in the excel sheet and its value- key,value- so columns are not n number
		
		Hashtable<String,String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2
//data starts from row 2, rows is rowcount
			table = new Hashtable<String,String>();
			
			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;// data[rowNum - 2][0] to get data at [0][0] position- first time- data[0][0]
				// Hashtable - only one key value at a time- so one column so col -0 >>> addCustomerTest(Hashtable<String,String> data)
			}

		}

		return data;

	}
	
	//runmode- to control the tests to be run or not - through excel
	public static boolean isTestRunnable(String testName, ExcelReader excel){
		
		String sheetName="test_suite";
		int rows = excel.getRowCount(sheetName);
		
		
		for(int rNum=2; rNum<=rows; rNum++){
			
			String testCase = excel.getCellData(sheetName, "TCID", rNum); //TCID-column name in excel sheet or give ColNum
			
			if(testCase.equalsIgnoreCase(testName)){
				
				String runmode = excel.getCellData(sheetName, "Runmode", rNum);
				
				if(runmode.equalsIgnoreCase("Y"))
					return true;
				else
					return false;
			}
			
			
		}
		return false;
	}
	
}
