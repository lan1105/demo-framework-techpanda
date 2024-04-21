package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObjects.user.PageGeneratorManager;
import pageUI.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage{
	WebDriver driver;

	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToUsernameTextbox(String userName) {
		waitForElementVisible(driver, AdminLoginPageUI.USER_NAME_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.USER_NAME_TEXTBOX, userName);
		
	}

	public void enterToPasswordTextbox(String password) {
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXT_BOX);
		sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXT_BOX, password);
		
	}

	public AdminHomePageObject clickToLoginButton() {
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminHomePage(driver);
		
	}



}
