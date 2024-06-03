package com.tech.panda.account;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import commons.BasePage;
import commons.BaseTest;
import pageObjects.user.AccountInformationPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import reportConfig.ExtentManager;
import pageObjects.user.MyDashBoardPageObject;
import pageObjects.user.PageGeneratorManager;

public class User_Login extends BaseTest {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	UserHomePageObject homePage;
	UserLoginPageObject loginPage;
	MyDashBoardPageObject myDashboardPage;
	AccountInformationPageObject accountInformationPage;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
	}

	@Test
	public void TC_01_Login_With_Empty_Email_And_Password(Method method) {
		ExtentManager.startTest(method.getName(), "TC_01_Login_With_Empty_Email_And_Password");
		ExtentManager.getTest().log(Status.INFO, "Login_01 - Step 01: Click to My Account link");

		loginPage = homePage.clickToMyAccountLink();

		ExtentManager.getTest().log(Status.INFO, "Login_01 - Step 02: Enter to Email Address textbox");
		loginPage.inputToEmailAddressTextbox("");

		ExtentManager.getTest().log(Status.INFO, "Login_01 - Step 03: Enter to Password textbox");
		loginPage.inputToPasswordTextbox("");

		ExtentManager.getTest().log(Status.INFO, "Login_01 - Step 04: Click to Login button");
		loginPage.clickToLoginButton();

		ExtentManager.getTest().log(Status.INFO, "Login_01 - Step 05: Verify error message is displayed");
		verifyEquals(loginPage.getEmailAddressEmptyErrorMsg(), "This is a required field");
		verifyEquals(loginPage.getPasswordEmptyErrorMsg(), "This is a required field.");
	}

	@Test
	public void TC_02_Login_With_Invalid_Email(Method method) {
		ExtentManager.startTest(method.getName(), "TC_02_Login_With_Invalid_Email");
		ExtentManager.getTest().log(Status.INFO, "Login_02 - Step 01: Click to My Account link");
		loginPage = homePage.clickToMyAccountLink();

		ExtentManager.getTest().log(Status.INFO, "Login_02 - Step 02: Enter to Email Address textbox");
		loginPage.inputToEmailAddressTextbox("123@45.789");

		ExtentManager.getTest().log(Status.INFO, "Login_02 - Step 03: Enter to Password textbox");
		loginPage.inputToPasswordTextbox("123456789");

		ExtentManager.getTest().log(Status.INFO, "Login_02 - Step 04: Click to Login button");
		loginPage.clickToLoginButton();

		ExtentManager.getTest().log(Status.INFO, "Login_02 - Step 05: Verify error message is displayed.");
		verifyEquals(loginPage.getEmailAddressInvalidMsg(),
				"Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test(description = "Email not exist in application")
	public void TC_03_Login_With_Incorrect_Email(Method method) {
		ExtentManager.startTest(method.getName(), "TC_03_Login_With_Incorrect_Email");
		ExtentManager.getTest().log(Status.INFO, "Login_03 - Step 01: Click to My Account link");
		loginPage = homePage.clickToMyAccountLink();

		ExtentManager.getTest().log(Status.INFO, "Login_03 - Step 02: Enter to Email Address textbox");
		loginPage.inputToEmailAddressTextbox("auto" + getRandomNumber() + "@live.com");

		ExtentManager.getTest().log(Status.INFO, "Login_03 - Step 03: Enter to Password textbox");
		loginPage.inputToPasswordTextbox("123456789");

		ExtentManager.getTest().log(Status.INFO, "Login_03 - Step 04: Click to Login button");
		loginPage.clickToLoginButton();

		ExtentManager.getTest().log(Status.INFO, "Login_03 - Step 05: Verify error message is displayed");
		verifyEquals(loginPage.getAddressPasswordIncorrectMsg(), "Invalid login or password.");
	}

	@Test(description = "Password less than 6 characters")
	public void TC_04_Login_With_InValid_Password(Method method) {
		ExtentManager.startTest(method.getName(), "TC_04_Login_With_InValid_Password");
		ExtentManager.getTest().log(Status.INFO, "Login_04 - Step 01: Click to My Account link");
		loginPage = homePage.clickToMyAccountLink();

		ExtentManager.getTest().log(Status.INFO, "Login_04 - Step 02: Enter to Email Address textbox");
		loginPage.inputToEmailAddressTextbox("auto" + getRandomNumber() + "@live.com");

		ExtentManager.getTest().log(Status.INFO, "Login_04 - Step 03: Enter to Password textbox");
		loginPage.inputToPasswordTextbox("123");

		ExtentManager.getTest().log(Status.INFO, "Login_04 - Step 04: Click to Login button");
		loginPage.clickToLoginButton();

		ExtentManager.getTest().log(Status.INFO, "Login_04 - Step 05: Verify error message is displayed");
		verifyEquals(loginPage.getPasswordInvalidMsg(),
				"Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_05_Login_With_Incorrect_Password(Method method) {
		ExtentManager.startTest(method.getName(), "TC_05_Login_With_Incorrect_Password");
		ExtentManager.getTest().log(Status.INFO, "Login_05 - Step 01: Click to My Account link");
		loginPage = homePage.clickToMyAccountLink();

		ExtentManager.getTest().log(Status.INFO, "Login_05 - Step 02: Enter to Email Address textbox");
		loginPage.inputToEmailAddressTextbox("automation_fc@gmail.com");

		ExtentManager.getTest().log(Status.INFO, "Login_05 - Step 03: Enter to Password textbox");
		loginPage.inputToPasswordTextbox(String.valueOf(getRandomNumber()));

		ExtentManager.getTest().log(Status.INFO, "Login_05 - Step 04: Click to Login button");
		loginPage.clickToLoginButton();

		ExtentManager.getTest().log(Status.INFO, "Login_05 - Step 05: Verify error message is displayed");
		verifyEquals(loginPage.getAddressPasswordIncorrectMsg(), "Invalid login or password.");
	}

	@Test
	public void TC_06_Login_With_Valid_Email_And_Password(Method method) {
		ExtentManager.startTest(method.getName(), "TC_06_Login_With_Valid_Email_And_Password");
		ExtentManager.getTest().log(Status.INFO, "Login_06 - Step 01: Click to My Account link");
		loginPage = homePage.clickToMyAccountLink();

		ExtentManager.getTest().log(Status.INFO, "Login_06 - Step 02: Enter to Email Address textbox");
		loginPage.inputToEmailAddressTextbox("automation_test@hotmail.com");

		ExtentManager.getTest().log(Status.INFO, "Login_06 - Step 03: Enter to Password textbox");
		loginPage.inputToPasswordTextbox("123456789");

		ExtentManager.getTest().log(Status.INFO, "Login_06 - Step 04: Click to Login button");
		myDashboardPage = loginPage.clickToLoginButton();

		ExtentManager.getTest().log(Status.INFO, "Login_06 - Step 05: Verify information is displayed successfuly");
		verifyTrue(myDashboardPage.isContactInforDisplayed("software Testing Demo"));
		verifyTrue(myDashboardPage.isContactInforDisplayed("automation_test@hotmail.com"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
