package com.tech.panda.account;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import commons.BasePage;
import pageObjects.user.HomePageObject;
import pageObjects.user.LoginPageObject;
import pageObjects.user.MyDashboardPageObject;

public class Level_03_Page_Object_Pattern {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	HomePageObject homePage; //bien homPage dai dien cho class HomePageObject
	LoginPageObject loginPage;
	MyDashboardPageObject myDashboardPage;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.techpanda.org/");
		//1 - Mo URL di den trang HomePage
		//2 - Muon dung duoc class HomePageObject thi phai khoi tao no len
		homePage = new HomePageObject(driver);
	}


	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		homePage.clickToMyAccountLink();
		//tu HomePage -> click My Account -> Login Page
		//khoi tao LoginPage
		loginPage = new LoginPageObject(driver);
		
		loginPage.inputToEmailAddressTextbox("");
		loginPage.inputToPasswordTextbox("");
		loginPage.clickToLoginButton();
		
		Assert.assertEquals(loginPage.getEmailAddressEmptyErrorMsg(), "This is a required field.");
		Assert.assertEquals(loginPage.getPasswordEmptyErrorMsg(), "This is a required field.");
	}


	@Test
	public void TC_02_LoginWithInvalidEmail() {
		homePage.clickToMyAccountLink();
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailAddressTextbox("123@45.789");
		loginPage.inputToPasswordTextbox("123456789");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getEmailAddressInvalidMsg(), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	
	@Test(description = "Email not exist in application")
	public void TC_03_LoginWithIncorrectEmail() {
		homePage.clickToMyAccountLink();
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailAddressTextbox("auto" + randomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123456789");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getAddressPasswordIncorrectMsg(), "Invalid login or password.");

	}

	@Test(description = "Password less than 6 characters")
	public void TC_04_LoginWithInValidPassword() {
		homePage.clickToMyAccountLink();
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailAddressTextbox("auto" + randomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox("123");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getPasswordInvalidMsg(), "Please enter 6 or more characters without leading or trailing spaces.");
	}

	@Test
	public void TC_05_LoginWithIncorrectPassword() {
		homePage.clickToMyAccountLink();
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailAddressTextbox("auto" + randomNumber() + "@live.com");
		loginPage.inputToPasswordTextbox(randomNumber() + "");
		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getAddressPasswordIncorrectMsg(), "Invalid login or password.");

	}

	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		homePage.clickToMyAccountLink();
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailAddressTextbox("automation_test@hotmail.com");
		loginPage.inputToPasswordTextbox("123456789");
		loginPage.clickToLoginButton();
		
		myDashboardPage = new MyDashboardPageObject(driver);
		Assert.assertTrue(myDashboardPage.checkDisplayContactInfor());

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
