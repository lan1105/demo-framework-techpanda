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
import pageObjects.user.UserRegisterPageObject;
import reportConfig.ExtentManager;
import pageObjects.user.MyDashBoardPageObject;
import pageObjects.user.PageGeneratorManager;

public class User_Register extends BaseTest {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	UserHomePageObject homePage;
	UserLoginPageObject loginPage;
	MyDashBoardPageObject myDashboardPage;
	UserRegisterPageObject userRegisterPage;
	AccountInformationPageObject accountInformationPage;

	String firstName, lastName, emailAddress, password;
	String editFirstName, editLastName, editEmailAddress;
	Random rand;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Lan";
		lastName = "Nguyen";
		emailAddress = "automation_demo" + getRandomNumber() + "@live.com";
		password = "123456789";

		editFirstName = "Lan Testing";
		editLastName = "Automation";
		editEmailAddress = "software_test" + getRandomNumber() + "@hotmail.com";

	}

	@Test
	public void TC_01_Register_Success_To_System(Method method) {
		ExtentManager.startTest(method.getName(), "TC_01_Register_Success_To_System");
		ExtentManager.getTest().log(Status.INFO, "Step 01: Click to My Account link");

		loginPage = homePage.clickToMyAccountLink();

		ExtentManager.getTest().log(Status.INFO, "Step 02: Click to Create an account");
		userRegisterPage = loginPage.clickToCreateAnAccountButton();

		ExtentManager.getTest().log(Status.INFO, "Step 03: Enter data to FirstName textbox");
		userRegisterPage.inputToFirstNameTextbox(firstName);

		ExtentManager.getTest().log(Status.INFO, "Step 04: Enter data to LastName textbox");
		userRegisterPage.inputToLastNameTextbox(lastName);

		ExtentManager.getTest().log(Status.INFO, "Step 05: Enter data to Email Address textbox");
		userRegisterPage.inputToEmailAddressTextbox(emailAddress);

		ExtentManager.getTest().log(Status.INFO, "Step 06: Enter data to Password textbox");
		userRegisterPage.inputToPasswordTextbox(password);

		ExtentManager.getTest().log(Status.INFO, "Step 07: Enter data to Confirm password textbox");
		userRegisterPage.inputToConfirmPasswordTextbox(password);

		ExtentManager.getTest().log(Status.INFO, "Step 08: Click to Register Button");
		myDashboardPage = userRegisterPage.clickToRegisterButton();

		ExtentManager.getTest().log(Status.INFO, "Step 09: Verify success message is displayed");
		verifyEquals(myDashboardPage.getSuccessRegisterMessage(), "Thank you for registering with Main Website Store.");

	}

	@Test
	public void TC_02_Verify_User_Information_Is_Correct(Method method) {
		ExtentManager.startTest(method.getName(), "TC_02_Verify_Information_Is_Correct");
		ExtentManager.getTest().log(Status.INFO, "Step 01: Click Account Information link");
		accountInformationPage = myDashboardPage.openAccountInformationPage();

		ExtentManager.getTest().log(Status.INFO, "Step 02: Verify Firstname is correct");
		verifyTrue(accountInformationPage.isFirstNameDisplayed(firstName));

		ExtentManager.getTest().log(Status.INFO, "Step 03: Verify LastName is correct");
		verifyTrue(accountInformationPage.isLastNameDisplayed(lastName));

		ExtentManager.getTest().log(Status.INFO, "Step 02: Verify Email Address is correct");
		verifyTrue(accountInformationPage.isEmailAddressDisplayed(emailAddress));

	}

	@Test
	public void TC_03_Update_Account_Information(Method method) {
		ExtentManager.startTest(method.getName(), "TC_03_Update_Account_Information");
		ExtentManager.getTest().log(Status.INFO, "Step 01: Enter data to First Name textbox");
		accountInformationPage.inputToFirstNameField(editFirstName);

		ExtentManager.getTest().log(Status.INFO, "Step 02: Enter data to Last Name textbox");
		accountInformationPage.inputToLastNameField(editLastName);

		ExtentManager.getTest().log(Status.INFO, "Step 03: Enter data to Email Address textbox");
		accountInformationPage.inputToEmailAddressField(editEmailAddress);

		ExtentManager.getTest().log(Status.INFO, "Step 04: Enter data to Current Password textbox");
		accountInformationPage.inputToCurrentPasswordField(password);

		ExtentManager.getTest().log(Status.INFO, "Step 05: Click to Save button");
		myDashboardPage = accountInformationPage.clickToSaveButton();

		ExtentManager.getTest().log(Status.INFO, "Step 06: Verify  Message is displayed");
		verifyTrue(myDashboardPage.isAccountInformationMessageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
