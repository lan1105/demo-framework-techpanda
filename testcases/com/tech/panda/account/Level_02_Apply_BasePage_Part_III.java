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

public class Level_02_Apply_BasePage_Part_III extends BasePage{
	// Khai bao
	WebDriver driver;
	
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		//Che giau viec khoi tao 1 doi tuong
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void beforeMethod() {
		openPageURL(driver, "http://live.techpanda.org/");
		clickToElement(driver, "//div[@class='footer']//a[text()='My Account']");

	}

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		sendkeyToElement(driver, "//input[@id='email']", "");
		sendkeyToElement(driver, "//input[@id='pass']", "");
		clickToElement(driver, "//button[@id='send2']");

		Assert.assertEquals(getElementText(driver, "//div[@id='advice-required-entry-email']"),
				"This is a required field.");
		Assert.assertEquals(getElementText(driver, "//div[@id='advice-required-entry-pass']"),
				"This is a required field.");
	}
	

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		sendkeyToElement(driver, "//input[@id='email']", "123@45.789");
		sendkeyToElement(driver, "//input[@id='pass']", "123456789");
		clickToElement(driver, "//button[@id='send2']");
		Assert.assertEquals(getElementText(driver, "//div[@id='advice-validate-email-email']"), "Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test(description = "Email not exist in application")
	public void TC_03_LoginWithIncorrectEmail() {
		sendkeyToElement(driver, "//input[@id='email']", "auto" + randomNumber() + "@live.com");
		sendkeyToElement(driver, "//input[@id='pass']", "123456789");
		clickToElement(driver, "//button[@id='send2']");
		
		Assert.assertEquals(getElementText(driver, "//li[@class='error-msg']//span"), "Invalid login or password.");

	}

	@Test(description = "Password less than 6 characters")
	public void TC_04_LoginWithInValidPassword() {
		sendkeyToElement(driver, "//input[@id='email']", "auto" + randomNumber() + "@live.com");
		sendkeyToElement(driver, "//input[@id='pass']", "1234");
		clickToElement(driver, "//button[@id='send2']");
		
		Assert.assertEquals(getElementText(driver, "//div[@id='advice-validate-password-pass']"), "Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_05_LoginWithIncorrectPassword() {
		sendkeyToElement(driver, "//input[@id='email']", "auto" + randomNumber() + "@live.com");
		sendkeyToElement(driver, "//input[@id='pass']", randomNumber() + "");
		clickToElement(driver, "//button[@id='send2']");
		
		Assert.assertEquals(getElementText(driver, "//li[@class='error-msg']//span"), "Invalid login or password.");

	}

	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		sendkeyToElement(driver, "//input[@id='email']", "automation_test@hotmail.com");
		sendkeyToElement(driver, "//input[@id='pass']", "123456789");
		clickToElement(driver, "//button[@id='send2']");
		
		Assert.assertTrue(isElementDisplayed(driver, "//div[@class='box']//div[@class='box-content']/p[contains(.,'Auto Testing')]"));
		Assert.assertTrue(isElementDisplayed(driver, "//div[@class='box']//div[@class='box-content']/p[contains(.,'automation_test@hotmail.com')]"));

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
