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

public class Level_02_Apply_BasePage_Part_I {
	// Khai bao
	WebDriver driver;
	BasePage basepage;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		basepage = new BasePage();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@BeforeMethod
	public void beforeMethod() {
		basepage.openPageURL(driver, "http://live.techpanda.org/");
		basepage.clickToElement(driver, "//div[@class='footer']//a[text()='My Account']");

	}

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		basepage.sendkeyToElement(driver, "//input[@id='email']", "");
		basepage.sendkeyToElement(driver, "//input[@id='pass']", "");
		basepage.clickToElement(driver, "//button[@id='send2']");

		Assert.assertEquals(basepage.getElementText(driver, "//div[@id='advice-required-entry-email']"),
				"This is a required field.");
		Assert.assertEquals(basepage.getElementText(driver, "//div[@id='advice-required-entry-pass']"),
				"This is a required field.");
	}
	

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		basepage.sendkeyToElement(driver, "//input[@id='email']", "123@45.789");
		basepage.sendkeyToElement(driver, "//input[@id='pass']", "123456789");
		basepage.clickToElement(driver, "//button[@id='send2']");
		Assert.assertEquals(basepage.getElementText(driver, "//div[@id='advice-validate-email-email']"), "Please enter a valid email address. For example johndoe@domain.com.");

	}

	@Test(description = "Email not exist in application")
	public void TC_03_LoginWithIncorrectEmail() {
		basepage.sendkeyToElement(driver, "//input[@id='email']", "auto" + randomNumber() + "@live.com");
		basepage.sendkeyToElement(driver, "//input[@id='pass']", "123456789");
		basepage.clickToElement(driver, "//button[@id='send2']");
		
		Assert.assertEquals(basepage.getElementText(driver, "//li[@class='error-msg']//span"), "Invalid login or password.");

	}

	@Test(description = "Password less than 6 characters")
	public void TC_04_LoginWithInValidPassword() {
		basepage.sendkeyToElement(driver, "//input[@id='email']", "auto" + randomNumber() + "@live.com");
		basepage.sendkeyToElement(driver, "//input[@id='pass']", "1234");
		basepage.clickToElement(driver, "//button[@id='send2']");
		
		Assert.assertEquals(basepage.getElementText(driver, "//div[@id='advice-validate-password-pass']"), "Please enter 6 or more characters without leading or trailing spaces.");

	}

	@Test
	public void TC_05_LoginWithIncorrectPassword() {
		basepage.sendkeyToElement(driver, "//input[@id='email']", "auto" + randomNumber() + "@live.com");
		basepage.sendkeyToElement(driver, "//input[@id='pass']", randomNumber() + "");
		basepage.clickToElement(driver, "//button[@id='send2']");
		
		Assert.assertEquals(basepage.getElementText(driver, "//li[@class='error-msg']//span"), "Invalid login or password.");

	}

	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		basepage.sendkeyToElement(driver, "//input[@id='email']", "automation_test@hotmail.com");
		basepage.sendkeyToElement(driver, "//input[@id='pass']", "123456789");
		basepage.clickToElement(driver, "//button[@id='send2']");
		
		Assert.assertTrue(basepage.isElementDisplayed(driver, "//div[@class='box']//div[@class='box-content']/p[contains(.,'Auto Testing')]"));
		Assert.assertTrue(basepage.isElementDisplayed(driver, "//div[@class='box']//div[@class='box-content']/p[contains(.,'automation_test@hotmail.com')]"));

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
