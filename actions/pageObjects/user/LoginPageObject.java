package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.user.LoginPageUI;

public class LoginPageObject extends BasePage{
	WebDriver driver;
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToEmailAddressTextbox(String emailAddress) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
		
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
		
	}

	public void clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		
	}

	public String getEmailAddressEmptyErrorMsg() {
		waitForElementVisible(driver,LoginPageUI.EMAIL_ADDRESS_EMPTY_ERROR_MSG);
		return getElementText(driver, LoginPageUI.EMAIL_ADDRESS_EMPTY_ERROR_MSG);
	}

	public String getPasswordEmptyErrorMsg() {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_EMPTY_ERROR_MSG);
		return getElementText(driver, LoginPageUI.PASSWORD_EMPTY_ERROR_MSG);
	}

	public String getEmailAddressInvalidMsg() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_INVALID_ERROR_MSG);
		return getElementText(driver, LoginPageUI.EMAIL_ADDRESS_INVALID_ERROR_MSG);
	}

	public String getAddressPasswordIncorrectMsg() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_PASSWORD_INCORRECT_ERROR_MSG);
		return getElementText(driver, LoginPageUI.EMAIL_ADDRESS_PASSWORD_INCORRECT_ERROR_MSG);
	}

	public String getPasswordInvalidMsg() {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_INVALID_ERROR_MSG);
		return getElementText(driver, LoginPageUI.PASSWORD_INVALID_ERROR_MSG);
	}

}
