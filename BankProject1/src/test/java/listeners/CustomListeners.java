package listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;



import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;


import base.BaseClass;
import utilities.TestUtil;
//add this CustomListeners1.java in <listeners> tag in testng.xml file
public class CustomListeners extends BaseClass implements ITestListener,ISuiteListener {

	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
	static String messageBody;
//	private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\reports\\"+fileName);
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	

	public void onTestStart(ITestResult result) {

	test = rep.startTest(arg0.getName().toUpperCase());//copied video
	if(!TestUtil.isTestRunnable(fileName, excel)) {
		throw new SkipException("Skipping the testcase-+ "arg0.getName().toUpperCase() + "  - Runmode is NO");
	} //copied code
		ExtentTest test = extent.createTest(result.getTestClass().getName()+"     @TestCase : "+result.getMethod().getMethodName());
      testReport.set(test);
        

	}

	public void onTestSuccess(ITestResult result) {

		
		String methodName=result.getMethod().getMethodName();
		/*
		 * String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+
		 * " PASSED"+"</b>"; Markup m=MarkupHelper.createLabel(logText,
		 * ExtentColor.GREEN); testReport.get().pass(m);
		 */

	}
	
	 public void onTestFailure(ITestResult result) {
		 
		 Reporter.log("in listeners>> onTestFailure(ITestResult resul");
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
	  		
		 
	 }

	/*
	 * public void onTestFailure(ITestResult result) {
	 * 
	 * 
	 * //Report.logs //screenshot links
	 * 
	 * String
	 * excepionMessage=Arrays.toString(result.getThrowable().getStackTrace());
	 * testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" +
	 * "red>" + "Exception Occured:Click to see" + "</font>" + "</b >" +
	 * "</summary>" +excepionMessage.replaceAll(",", "<br>")+"</details>"+" \n");
	 * 
	 * try {
	 * 
	 * TestUtil.captureScreenshot(); testReport.get().fail("<b>" + "<font color=" +
	 * "red>" + "Screenshot of failure" + "</font>" + "</b>",
	 * MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.screenshotName)
	 * .build()); } catch (IOException e) {
	 * 
	 * }
	 * 
	 * String failureLogg="TEST CASE FAILED"; Markup m =
	 * MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
	 * testReport.get().log(Status.FAIL, m);
	 * 
	 * }
	 */

	public void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"Test Case:- "+ methodName+ " Skipped"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

		

	}

	/*
	 * public void onFinish(ISuite arg0) {
	 * 
	 * MonitoringMail mail = new MonitoringMail();
	 * 
	 * try { messageBody = "http://" + InetAddress.getLocalHost().getHostAddress() +
	 * ":8080/job/DataDrivenLiveProject/Extent_Reports/"; } catch
	 * (UnknownHostException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * try { mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to,
	 * TestConfig.subject, messageBody); } catch (AddressException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); } catch (MessagingException
	 * e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * 
	 * }
	 */

	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {

		/*
		 * if (extent != null) {
		 * 
		 * extent.flush(); }
		 */
		
	}

	@Override
	public void onFinish(ISuite suite) {
		// TODO Auto-generated method stub
		
	}


}
