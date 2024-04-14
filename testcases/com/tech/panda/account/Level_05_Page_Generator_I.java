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
import pageObjects.user.AccountInformationPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.MyDashboardPageObject;
import pageObjects.user.PageGeneratorManager;

public class Level_05_Page_Generator_I extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	HomePageObject homePage; //bien homPage dai dien cho class HomePageObject
	LoginPageObject loginPage;
	MyDashboardPageObject myDashboardPage;
	AccountInformationPageObject accountInformationPage;
	
	@Parameters({"browser"})
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		homePage = PageGeneratorManager.getHomePage(driver);
	}


	@Test
	public void TC_01_Login_With_Empty_Email_And_Password() {
		loginPage = homePage.clickToMyAccountLink();
		
		loginPage.inputToEmailAddressTextbox("");
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getEmailAddressEmptyErrorMsg(), "This is a required field.");
		Assert.assertEquals(loginPage.getPasswordEmptyErrorMsg(), "This is a required field.");
	}

	@Test
	public void TC_02_Login_With_Invalid_Email() {
		loginPage = homePage.clickToMyAccountLink();

		loginPage.inputToEmailAddressTextbox("123@45.789");
		loginPage.inputToPasswordTextbox("123456789");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getEmailAddressInvalidMsg(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test(description = "Email not exist in application")
	public void TC_03_Login_With_Incorrect_Email() {
		loginPage = homePage.clickToMyAccountLink();
		loginPage.inputToEmailAddressTextbox("auto" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123456789");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getAddressPasswordIncorrectMsg(), "Invalid login or password.");

	}

	@Test(description = "Password less than 6 characters")
	public void TC_04_Login_With_InValid_Password() {
		loginPage = homePage.clickToMyAccountLink();
		loginPage.inputToEmailAddressTextbox("auto" + getRandomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getPasswordInvalidMsg(), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_05_Login_With_Incorrect_Password() {
		loginPage = homePage.clickToMyAccountLink();
		loginPage.inputToEmailAddressTextbox("automation_fc@gmail.com");
		loginPage.inputToPasswordTextbox(String.valueOf(getRandomNumber()));
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getAddressPasswordIncorrectMsg(), "Invalid login or password.");

	}
	
	@Test
	public void TC_06_Login_With_Valid_Email_And_Password() {
		loginPage = homePage.clickToMyAccountLink();
		loginPage.inputToEmailAddressTextbox("automation_test@hotmail.com");
		loginPage.inputToPasswordTextbox("123456789");
		
		myDashboardPage = loginPage.clickToLoginButton();
		Assert.assertTrue(myDashboardPage.isContactInforDisplayed("Auto Testing"));
		Assert.assertTrue(myDashboardPage.isContactInforDisplayed("automation_test@hotmail.com"));

	}
	
	@Test
	public void TC_07_Update_Account_Information() {
		accountInformationPage = myDashboardPage.clickToAccountInformationLink();
		accountInformationPage.inputToFirstNameField("software");
		accountInformationPage.inputToLastNameField("Testing Demo");
		accountInformationPage.inputToEmailAddressField("software_test" + getRandomNumber() + "@hotmail.com");
		accountInformationPage.inputToCurrentPasswordField("123456789");
		myDashboardPage = accountInformationPage.clickToSaveButton();
		
		Assert.assertTrue(myDashboardPage.isAccountInformationMessageDisplayed());		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
