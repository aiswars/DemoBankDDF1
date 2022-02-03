package listeners;

import java.io.IOException;
import java.util.Date;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import base.BaseClass;




//public class ExtentListeners implements ITestListener, ISuiteListener {
public class ExtentListeners  extends BaseClass implements ITestListener, ISuiteListener {

	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	private static ExtentReports extent = ExtentManager
			.createInstance(".\\reports\\" + fileName);

	public static ExtentTest test;
	
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	
	
	
	

	public void onTestStart(ITestResult result) {

		  test = extent.createTest(result.getTestClass().getName() + "     @TestCase : " + result.getMethod().getMethodName());

		//	ExtentTest test = extent.createTest(result.getTestClass().getName()+"     @TestCase : "+result.getMethod().getMethodName());
	      testReport.set(test);
	}

	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + " PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		test.pass(m);

	}

	public void onTestFailure(ITestResult result) {
		

		///test.fail(result.getThrowable().getMessage());
		try {
			ExtentManager.captureScreenshot(); //ExtentManager.java class in listeners package
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ " FAILED"+"</b>";		
	
	

		test.fail("<b><font color=red>" + "Screenshot of failure" + "</font></b><br>",MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.fileName)
				.build());
	
		
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		test.log(Status.FAIL, m);
		
		

	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		//test.skip(m); //extentListener
		testReport.get().skip(m); //CustomListener-old
		/*
		 * String methodName=result.getMethod().getMethodName(); String
		 * logText="<b>"+"Test Case:- "+ methodName+ " Skipped"+"</b>"; Markup
		 * m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		 * testReport.get().skip(m);
		 */

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

		if (extent != null) {

			extent.flush();
		}

	}

	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
	}

}
