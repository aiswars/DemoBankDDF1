package Zrough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilities.ExcelReader;
import java.util.concurrent.TimeUnit;




//http://www.way2automation.com/angularjs-protractor/banking/#/login
public class BaseClass {
	
	/*
	 * to initialize >>
	 * WebDriver
	 * Properties
	 * Logs -log4j jar, .log log4j.properties
	 * ExtentReports
	 * DB
	 * Excel
	 * Mail
	 * ReportNG, ExtentReports
	 * Jenkins
	 */
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static FileInputStream fis;
	public static Properties OR = new Properties();
	//public static Logger log = Logger.getLogger("devpinoyLogger");
	//public static Logger log = Logger.getLogger("BankProject1");
	//public static Logger log = Logger.getLogger("BankProject1");
	
	//4	logger object- Logger class & PropertyConfigurator/DOMCOnfigurator
	//logger = Logger.getLogger("ebanking");
	//PropertyConfigurator.configure("Log4j.properties");
	
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"//src//test//resources//excel//testdata.xlsx");
	//path of testdata file >> System.getProperty("user.dir")+ " ..........
	
	//String path=System.getProperty("user.dir")+"/src/test/java/com/bankusers/testdata/LoginDataBank.xlsx";
public static WebDriverWait wait;


	
	@BeforeSuite
	public void setUp() {
		
	 //   PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties"); // lecture 27
	//	PropertyConfigurator.configure("log4j.properties");
		//check log4j
   
		if (driver == null) {
      
    	//config.properties file reading
    	//fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
       // config.load(fis);
       
        //OR.properties file reading
      //  fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
      //  OR.load(fis);
    	
    	//config.properties file reading
        try {
			fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			config.load(fis);
	//		log.debug("Config file loaded !!!");
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       
        //OR.properties file reading
        try {
			fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			OR.load(fis);
	//		log.debug("OR file loaded !!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (config.getProperty("browser").equalsIgnoreCase("firefox")) {
        //	System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
			
        	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver",  System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
            driver = new ChromeDriver();
       //     log.debug("Chrome launched!!!");
           
        } else if (config.getProperty("browser").equalsIgnoreCase("ie")) {
            System.setProperty("webdriver.ie.driver",  System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
            driver = new InternetExplorerDriver();
        }

        driver.get(config.getProperty("testsiteurl"));
     //   log.debug("Navigated to the url !!!");
        driver.manage().window().maximize();
     
        //driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
       	 // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(config.getProperty("implicit.wait"))));//implicit.wait from config.properties
      	 driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
    	wait = new WebDriverWait(driver,5);//explicitwait
		} // if (driver == null) {
}//setUp() {

	
	
	
	public boolean isElementPresent(By by) {

		try {

			driver.findElement(by);
			return true;

		}catch(NoSuchElementException e) {
			return false;
		}
		
	}//isElementPresent(By by) {
	
	
	
	
	
	
	
	
	
	@AfterSuite
	public void tearDown() {
	if(driver!=null) {
		driver.quit();
		}
//	log.debug("test execution completed !!!");
	}//tearDown() {
	
	
	
	
}


