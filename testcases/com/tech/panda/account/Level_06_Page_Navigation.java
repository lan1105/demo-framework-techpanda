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
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.MyAccountPageObject;
import pageObjects.user.MyDashboardPageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.SearchTermsPageObject;

public class Level_06_Page_Navigation extends BaseTest{
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	UserHomePageObject homePage; //bien homPage dai dien cho class HomePageObject
	UserLoginPageObject loginPage;
	MyDashboardPageObject myDashboardPage;
	AccountInformationPageObject accountInformationPage;
	AboutUsPageObject aboutUsPage;
	SearchTermsPageObject searchTermsPage;
	MyAccountPageObject myAccountPage;
	
	@Parameters({"browser"})
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		homePage = PageGeneratorManager.getUserHomePage(driver);
	}
	
	@Test
	public void TC_01_Footer_Page() {;
		aboutUsPage = homePage.getFooterContainerPage(driver).openAboutUsPage();
		myAccountPage = aboutUsPage.openMyAccountPage();
		searchTermsPage = myAccountPage.openSearchTermsPage();
		aboutUsPage = searchTermsPage.openAboutUsPage();
	}
	

	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
