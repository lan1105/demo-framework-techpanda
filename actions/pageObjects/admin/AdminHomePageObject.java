package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.admin.AdminHomePageUI;

public class AdminHomePageObject extends BasePage {
	WebDriver driver;

	public AdminHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void closeIncomingMessagePopup() {
		waitForElementVisible(driver, AdminHomePageUI.INCOMING_MESSAGE_POPUP);
		waitForElementClickable(driver, AdminHomePageUI.CLOSE_BUTTON);
		clickToElement(driver, AdminHomePageUI.CLOSE_BUTTON);
		
	}

	public void clickToLogoutLink() {
		waitForElementVisible(driver, AdminHomePageUI.LOG_OUT_LINK);
		clickToElement(driver, AdminHomePageUI.LOG_OUT_LINK);
		
	}

}
