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

public class Level_01_DRY {
		WebDriver driver;
		String projectPath = System.getProperty("user.dir");
		
		@BeforeClass
		public void beforeClass() {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get("http://live.techpanda.org/");
		}
		@BeforeMethod
		public void beforeMethod() {
			driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		}
		
		@Test
		public void TC_01_LoginWithEmptyEmailAndPassword() {
			  driver.findElement(By.id("email")).sendKeys("");
			  driver.findElement(By.id("pass")).sendKeys("");
			  driver.findElement(By.id("send2")).click();
			  
			  Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
			  Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
		}
		  
		@Test
		public void TC_02_LoginWithInvalidEmail() {
			  driver.findElement(By.id("email")).sendKeys("123@45.789");
			  driver.findElement(By.id("pass")).sendKeys("123456789");
			  driver.findElement(By.id("send2")).click();
			  
			  assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), 
					  "Please enter a valid email address. For example johndoe@domain.com.");		  
		}
		  
		@Test(description = "Email not exist in application")
		public void TC_03_LoginWithIncorrectEmail() {
			  driver.findElement(By.id("email")).sendKeys("auto" + randomNumber() + "@live.com");
			  driver.findElement(By.id("pass")).sendKeys("123456789");
			  driver.findElement(By.id("send2")).click();
			  
			  assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
		}
		
		@Test(description = "Password less than 6 characters")
		public void TC_04_LoginWithInValidPassword() {
			  driver.findElement(By.id("email")).sendKeys("auto_test" + randomNumber() + "@live.com");
			  driver.findElement(By.id("pass")).sendKeys("1234");
			  driver.findElement(By.id("send2")).click();
			  
			  assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), 
					  "Please enter 6 or more characters without leading or trailing spaces.");
		}
		
		@Test
		public void TC_05_LoginWithIncorrectPassword() {
			  driver.findElement(By.id("email")).sendKeys("auto_test" + randomNumber() + "@live.com");
			  driver.findElement(By.id("pass")).sendKeys(randomNumber() + "");
			  driver.findElement(By.id("send2")).click();
			  
			  assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), 
					  "Invalid login or password.");
		}  
		
		@Test
		public void TC_06_LoginWithValidEmailAndPassword() {
			  driver.findElement(By.id("email")).sendKeys("automation_test@hotmail.com");
			  driver.findElement(By.id("pass")).sendKeys("123456789");
			  driver.findElement(By.id("send2")).click();
			  
			  assertTrue(driver.findElement(By.xpath("//div[@class='box']//div[@class='box-content']/p[contains(.,'Auto Testing')]")).isDisplayed());
			  assertTrue(driver.findElement(By.xpath("//div[@class='box']//div[@class='box-content']/p[contains(.,'automation_test@hotmail.com')]")).isDisplayed());
			  
		}
		@AfterClass
		public void afterClass() {
			driver.quit();
		  }
		private int randomNumber() {
			Random rand =  new Random();
			return rand.nextInt(999999);
		}
		
}
