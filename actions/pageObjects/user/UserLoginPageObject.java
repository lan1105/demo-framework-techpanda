package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage{
	WebDriver driver;
	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToEmailAddressTextbox(String emailAddress) {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		sendkeyToElement(driver, UserLoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
		
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
		
	}

	public MyDashboardPageObject clickToLoginButton() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return new PageGeneratorManager().getMyDashboardPage(driver);
		
	}

	public String getEmailAddressEmptyErrorMsg() {
		waitForElementVisible(driver,UserLoginPageUI.EMAIL_ADDRESS_EMPTY_ERROR_MSG);
		return getElementText(driver, UserLoginPageUI.EMAIL_ADDRESS_EMPTY_ERROR_MSG);
	}

	public String getPasswordEmptyErrorMsg() {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_EMPTY_ERROR_MSG);
		return getElementText(driver, UserLoginPageUI.PASSWORD_EMPTY_ERROR_MSG);
	}

	public String getEmailAddressInvalidMsg() {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_ADDRESS_INVALID_ERROR_MSG);
		return getElementText(driver, UserLoginPageUI.EMAIL_ADDRESS_INVALID_ERROR_MSG);
	}

	public String getAddressPasswordIncorrectMsg() {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_ADDRESS_PASSWORD_INCORRECT_ERROR_MSG);
		return getElementText(driver, UserLoginPageUI.EMAIL_ADDRESS_PASSWORD_INCORRECT_ERROR_MSG);
	}

	public String getPasswordInvalidMsg() {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_INVALID_ERROR_MSG);
		return getElementText(driver, UserLoginPageUI.PASSWORD_INVALID_ERROR_MSG);
	}

}
