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
import pageObjects.user.AboutUsPageObject;
import pageObjects.user.AccountInformationPageObject;
import pageObjects.user.BillingAgreementsPageObject;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.MyAccountPageObject;
import pageObjects.user.MyApplicationsPageObject;
import pageObjects.user.MyDashBoardPageObject;
import pageObjects.user.MyOrdersPageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.SearchTermsPageObject;

public class Page_Navigation extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	UserHomePageObject userHomePage;
	UserLoginPageObject userLoginPage;
	MyDashBoardPageObject myDashBoardPage;
	AccountInformationPageObject accountInformationPage;
	AboutUsPageObject aboutUsPage;
	SearchTermsPageObject searchTermsPage;
	MyAccountPageObject myAccountPage;
	MyOrdersPageObject myOrdersPage;
	MyApplicationsPageObject myApplicationsPage;
	BillingAgreementsPageObject billingAgreementsPage;
	
	@Parameters({"browser"})
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);	
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
	}
	
	@Test
	public void TC_01_Footer_Page() {;
		aboutUsPage = userHomePage.getFooterContainerPage(driver).openAboutUsPage();
		myAccountPage = aboutUsPage.openMyAccountPage();
		searchTermsPage = myAccountPage.openSearchTermsPage();
		aboutUsPage = searchTermsPage.openAboutUsPage();
	}
	
	@Test
	public void TC_02_SideBar_Page() {
		userLoginPage = userHomePage.clickToMyAccountLink();
		userLoginPage.inputToEmailAddressTextbox("automation_test@hotmail.com");
		userLoginPage.inputToPasswordTextbox("123456789");
		
		myDashBoardPage = userLoginPage.clickToLoginButton();
		Assert.assertTrue(myDashBoardPage.isContactInforDisplayed("software Testing Demo"));
		Assert.assertTrue(myDashBoardPage.isContactInforDisplayed("automation_test@hotmail.com"));
	
		myDashBoardPage.openSideBarLinkByPageName("Account Information");
		accountInformationPage = PageGeneratorManager.getAccountInformationPage(driver);
		
		accountInformationPage.openSideBarLinkByPageName("Account Dashboard");
		myDashBoardPage = PageGeneratorManager.getMyDashBoardPage(driver);
		
		myDashBoardPage.openSideBarLinkByPageName("My Orders");
		myOrdersPage = PageGeneratorManager.getMyOrdersPage(driver);
		
		myOrdersPage.openSideBarLinkByPageName("My Applications");
		myApplicationsPage = PageGeneratorManager.getMyApplicationsPage(driver);		
	}	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
