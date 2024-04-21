package com.tech.panda.account;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.MyDashboardPageObject;

public class Level_04_Multiple_Browsers extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	UserHomePageObject homePage; //bien homPage dai dien cho class HomePageObject
	UserLoginPageObject loginPage;
	MyDashboardPageObject myDashboardPage;
	
	@Parameters({"browser"})
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		//1 - Mo URL di den trang HomePage
		//2 - Muon dung duoc class HomePageObject thi phai khoi tao no len
		homePage = new UserHomePageObject(driver);
	}


	@Test
	public void TC_01_Login_With_Empty_Email_And_Password() {
		homePage.clickToMyAccountLink();
		//tu HomePage -> click My Account -> Login Page
		//khoi tao LoginPage
		loginPage = new UserLoginPageObject(driver);
		
		loginPage.inputToEmailAddressTextbox("");
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getEmailAddressEmptyErrorMsg(), "This is a required field.");
		Assert.assertEquals(loginPage.getPasswordEmptyErrorMsg(), "This is a required field.");
	}


	@Test
	public void TC_02_Login_With_Invalid_Email() {
		homePage.clickToMyAccountLink();
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailAddressTextbox("123@45.789");
		loginPage.inputToPasswordTextbox("123456789");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getEmailAddressInvalidMsg(), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	
	@Test(description = "Email not exist in application")
	public void TC_03_Login_With_Incorrect_Email() {
		homePage.clickToMyAccountLink();
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailAddressTextbox("auto" + randomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123456789");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getAddressPasswordIncorrectMsg(), "Invalid login or password.");

	}

	@Test(description = "Password less than 6 characters")
	public void TC_04_Login_With_InValid_Password() {
		homePage.clickToMyAccountLink();
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailAddressTextbox("auto" + randomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getPasswordInvalidMsg(), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_05_Login_With_Incorrect_Password() {
		homePage.clickToMyAccountLink();
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailAddressTextbox("auto" + randomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox(String.valueOf(randomNumber()));
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getAddressPasswordIncorrectMsg(), "Invalid login or password.");

	}
	

	@Test
	public void TC_06_Login_With_Valid_Email_And_Password() {
		homePage.clickToMyAccountLink();
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailAddressTextbox("automation_test@hotmail.com");
		loginPage.inputToPasswordTextbox("123456789");
		loginPage.clickToLoginButton();
		
		myDashboardPage = new MyDashboardPageObject(driver);
		Assert.assertTrue(myDashboardPage.isContactInforDisplayed("Auto Testing"));
		Assert.assertTrue(myDashboardPage.isContactInforDisplayed("automation_test@hotmail.com"));

	}
	


	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

}
