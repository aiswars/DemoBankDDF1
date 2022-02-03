package utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;



import base.BaseClass;

public class TestUtil extends BaseClass {

	public static String screenshotPath;
	public static String screenshotName;

	public static void captureScreenshot() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		//screenshotName = "error";
		//Report.log "\\target\\surefire-reports\\html\\" + screenshotName));//the screenshot for all the failed tests get overwritten as the name is hardcoded, hence use timestamp
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";//screenshot with the timestamp

		FileUtils.copyFile(scrFile,
				new File(System.getProperty("user.dir") + "\\target\\surefire-reports\\html\\" + screenshotName));
		FileUtils.copyFile(scrFile,
				new File(".\\reports\\" + screenshotName));

	}

	@DataProvider(name="dp")
	public Object[][] getData(Method m) {

		String sheetName = m.getName(); //"AddCustomerTest - same as Classname"
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];
		
		Hashtable<String,String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2
//data starts from row 2, rows is rowcount
			table = new Hashtable<String,String>();
			
			for (int colNum = 0; colNum < cols; colNum++) {

				// data[0][0]
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;// data[rowNum - 2][0] to get data at [0][0] position- first time- data[0][0]
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
