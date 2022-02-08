package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import listeners.ExtentListeners;
import utilities.ExcelReader;
import utilities.TestUtil;;

//http://www.way2automation.com/angularjs-protractor/banking/#/login
public class BaseClass {

	/*
	 * to initialize >> WebDriver Properties Logs -log4j jar, .log log4j.properties
	 * ExtentReports DB Excel Mail ReportNG, ExtentReports Jenkins
	 */

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static FileInputStream fis;
	public static Properties OR = new Properties();

	public static Logger log = Logger.getLogger(BaseClass.class.getName());

	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "//src//test//resources//excel//testdata.xlsx");
	// path of testdata file >> System.getProperty("user.dir")+ " ..........

	// String
	// path=System.getProperty("user.dir")+"/src/test/java/com/bankusers/testdata/LoginDataBank.xlsx";
	public static WebDriverWait wait;

	@BeforeSuite
	public void setUp() {

		Date d = new Date();
		System.out.println("Log4j>> date-timeStamp " + d.toString().replace(":", "_").replace(" ", "_")); // Wed_Feb_02_15_28_19_EST_2022
		System.setProperty("current.date", d.toString().replace(":", "_").replace(" ", "_")); // current.date in
																								// log4j.properties >>
																								// ${current.date}
		PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties"); // // lecture 27
																							// PropertyConfigurator - to
																							// configure log4j file from
																							// any location in the
																							// system
		// PropertyConfigurator.configure("log4j.properties"); //if in project level
		log.info("log file !!!");

		// driver initialization
		if (driver == null) {//driver is null at the beginning of execution
			

			// config.properties file reading
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
				log.info("config.properties file loaded !!!");
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded !!!");

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// OR.properties file reading
			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			////////////////////////////////////////////////

			/*
			 * if (System.getenv("browser") != null && !System.getenv("browser").isEmpty())
			 * {
			 * 
			 * browser = System.getenv("browser"); } else {
			 * 
			 * browser = config.getProperty("browser");
			 * 
			 * }
			 * 
			 * config.setProperty("browser", browser);
			 */

			/////////////////////////////////////////////////////

			// browser invoking- browser name from config.properties
			if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
				// System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				log.debug("Chrome launched!!!");
				log.info("Chrome launched!!!");
			} else if (config.getProperty("browser").equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
////////////////////////////////////////////////////////////////////

			/*
			 * if (config.getProperty("browser").equals("firefox")) {
			 * 
			 * // System.setProperty("webdriver.gecko.driver", "gecko.exe");
			 * WebDriverManager.firefoxdriver().setup(); driver = new FirefoxDriver();
			 * 
			 * } else if (config.getProperty("browser").equals("chrome")) {
			 * 
			 * System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")
			 * + "\\src\\test\\resources\\executables\\chromedriver.exe");
			 * 
			 * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
			 * log.debug("Chrome Launched !!!"); } else if
			 * (config.getProperty("browser").equals("ie")) {
			 * 
			 * System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") +
			 * "\\src\\test\\resources\\executables\\IEDriverServer.exe"); driver = new
			 * InternetExplorerDriver();
			 * 
			 * }
			 */
			///////////////////////////////////////////////////////////////////////
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to the url !!!");
			driver.manage().window().maximize();
			//implcit wait time from config.properties. 
			//implcit wait time from config.properties- config.getProperty.implcit wait time from config.properties.  implcitwait takes only integers - so conversion Integer.parseInt
			
			// driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(config.getProperty("implicit.wait"))));//implicit.wait
			// from config.properties
			//driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getProperty("implicit.wait")),TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);// explicitwait

		} // if (driver == null) {

	} // setUp() {

	public boolean isElementPresent(By by) {

		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {
			return false;
		}

	}// isElementPresent(By by) {

	// code optimization- to click an element -utitlity
	public void click(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		// CustomListeners.testReport.get().log(Status.INFO, "Clicking on : " +
		// locator);
	}

	// code optimization- to type-sendkeys to an element-checking the locator in if
	// -utitlity
	public void type(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}

		// CustomListeners.testReport.get().log(Status.INFO, "Typing in : " + locator +
		// " entered value as " + value);

	}

	static WebElement dropdown;

	public void select(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}

		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
//ExtentListeners.java // in listeners pkg;
		ExtentListeners.testReport.get().log(Status.INFO,
				"Selecting from dropdown : " + locator + " value as " + value);

	}

	
	//to verify anything use this below method like verify title-LIKE SOFTASSERTONS - not by haRD assertion
		//without using testng assertions
//to verifyEquals eAssert.assertEquals(actual, expected);

	public static void verifyEquals(String expected, String actual) throws IOException {

		try {

			Assert.assertEquals(actual, expected);

		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			ExtentListeners.testReport.get().log(Status.FAIL, " Verification failed with exception : " + t.getMessage());
			ExtentListeners.testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(TestUtil.screenshotName)
					.build());
//arg0.getName().toUpperCase()  >>t.getMessage())
			//test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName)); //customListeners.java
		}

	}
	
	
	
	
	@AfterSuite
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		log.debug("test execution completed !!!");
	}// tearDown() {

}
