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
import commons.GlobalConstants;
import pageObjects.admin.AdminHomePageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.user.AccountInformationPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.MyDashBoardPageObject;
import pageObjects.user.PageGeneratorManager;

public class Switch_Role extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	UserHomePageObject userHomePage;
	UserLoginPageObject userLoginPage;
	MyDashBoardPageObject myDashboardPage;
	AccountInformationPageObject accountInformationPage;
	AdminLoginPageObject adminLoginPage;
	AdminHomePageObject adminHomePage;
	
	String userURL, adminURL;
	@Parameters({"browser"})
	@BeforeClass
	public void beforeClass(String browserName) {
		this.userURL = GlobalConstants.LIVE_USER_URL;
		this.adminURL = GlobalConstants.LIVE_ADMIN_URL;
		
		driver = getBrowserDriver(browserName, userURL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
	}

	@Test
	public void TC_01_Switch_Role(){
		userLoginPage = userHomePage.clickToMyAccountLink();
		userLoginPage.inputToEmailAddressTextbox("automation_test@hotmail.com");
		userLoginPage.inputToPasswordTextbox("123456789");
		
		myDashboardPage = userLoginPage.clickToLoginButton();
		Assert.assertTrue(myDashboardPage.isContactInforDisplayed("software Testing Demo"));
		Assert.assertTrue(myDashboardPage.isContactInforDisplayed("automation_test@hotmail.com"));
		
		userHomePage = myDashboardPage.clickToUserLogoutLink(driver);
		
		adminLoginPage = userHomePage.openAdminLoginPage(driver, adminURL);
		
		adminLoginPage.enterToUsernameTextbox("user01");
		adminLoginPage.enterToPasswordTextbox("guru99com");
		adminHomePage= adminLoginPage.clickToLoginButton();
		
		adminHomePage.closeIncomingMessagePopup();
		
		adminLoginPage= adminHomePage.clickToAdminLogoutLink(driver);
		
		userHomePage= adminLoginPage.openUserHomePage(driver, userURL);
		userHomePage.clickToMyAccountLink();
		userLoginPage.inputToEmailAddressTextbox("automation_test@hotmail.com");
		userLoginPage.inputToPasswordTextbox("123456789");
		myDashboardPage = userLoginPage.clickToLoginButton();
		Assert.assertTrue(myDashboardPage.isContactInforDisplayed("software Testing Demo"));
		Assert.assertTrue(myDashboardPage.isContactInforDisplayed("automation_test@hotmail.com"));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
