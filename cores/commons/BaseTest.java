package commons;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	protected final Log log; 
	
	public BaseTest() {
		//Khoi tao Log
		log = LogFactory.getLog(getClass());
	}

	String projectPath = System.getProperty("user.dir");
	
	public WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case FIREFOX:
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case CHROME:
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;
		default:
			throw new RuntimeException("Browser is not valid!");
		}
		driver.get(GlobalConstants.LIVE_USER_URL);
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		return driver;
	}
	
	public WebDriver getBrowserDriver(String browserName, String urlValue) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
		switch (browserList) {
		case FIREFOX:
			driver = WebDriverManager.firefoxdriver().create();
			break;
		case CHROME:
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;
		default:
			throw new RuntimeException("Browser is not valid!");
		}
		driver.get(urlValue);
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		return driver;
	}
	
	
	protected int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}
	
	protected boolean verifyTrue(boolean condition) {
		boolean status = true;
		try {
			Assert.assertTrue(condition);
			log.info("----------------------PASSED----------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("----------------------FAILED----------------------");
		}
		return status;
	}
	
	protected boolean verifyFalse(boolean condition) {
		boolean status = true;
		try {
			Assert.assertFalse(condition);
			log.info("----------------------PASSED----------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("----------------------FAILED----------------------");
		}
		return status;
	}
	
	protected boolean verifyEquals(Object actual, Object expected) {
		boolean status = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info("----------------------PASSED----------------------");
		} catch (Throwable e) {
			status = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
			log.info("----------------------FAILED----------------------");
		}
		return status;
	}
	
	@BeforeSuite
	public void beforeSuite() {
		deleteFilesInReportNGFolder();
	}
	
	public void deleteFilesInReportNGFolder() {
		try {
			File file = new File(GlobalConstants.REPORTING_SCREENSHOT_PATH);
			File[] listOfFiles = file.listFiles();
			if (listOfFiles.length != 0) {
				for (int i = 0; i < listOfFiles.length; i++) {
					if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
						new File(listOfFiles[i].toString()).delete();
					}
				}
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	//Ham getter
	public WebDriver getDriver() {
	return this.driver;
	}

}
