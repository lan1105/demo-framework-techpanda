package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUI.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
	WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Enter to Email Address textbox")
	public void inputToEmailAddressTextbox(String emailAddress) {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		sendkeyToElement(driver, UserLoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);

	}

	@Step("Enter to Password textbox")
	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);

	}

	@Step("Click to Login button")
	public MyDashBoardPageObject clickToLoginButton() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return new PageGeneratorManager().getMyDashBoardPage(driver);

	}

	@Step("Get error message when Email is empty")
	public String getEmailAddressEmptyErrorMsg() {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_ADDRESS_EMPTY_ERROR_MSG);
		return getElementText(driver, UserLoginPageUI.EMAIL_ADDRESS_EMPTY_ERROR_MSG);
	}

	@Step("Get error message when Password is empty")
	public String getPasswordEmptyErrorMsg() {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_EMPTY_ERROR_MSG);
		return getElementText(driver, UserLoginPageUI.PASSWORD_EMPTY_ERROR_MSG);
	}

	@Step("Get error message when Email Address is invalid")
	public String getEmailAddressInvalidMsg() {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_ADDRESS_INVALID_ERROR_MSG);
		return getElementText(driver, UserLoginPageUI.EMAIL_ADDRESS_INVALID_ERROR_MSG);
	}

	@Step("Get error message when Emai Adress and Password are incorrect")
	public String getAddressPasswordIncorrectMsg() {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_ADDRESS_PASSWORD_INCORRECT_ERROR_MSG);
		return getElementText(driver, UserLoginPageUI.EMAIL_ADDRESS_PASSWORD_INCORRECT_ERROR_MSG);
	}

	@Step("Get error message when Password is invalid")
	public String getPasswordInvalidMsg() {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_INVALID_ERROR_MSG);
		return getElementText(driver, UserLoginPageUI.PASSWORD_INVALID_ERROR_MSG);
	}
	
	@Step("Click to Create an account button")
	public UserRegisterPageObject clickToCreateAnAccountButton() {
		waitForElementClickable(driver, UserLoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
		clickToElement(driver, UserLoginPageUI.CREATE_AN_ACCOUNT_BUTTON);
		return new PageGeneratorManager().getUserRegisterPage(driver);

	}
	

}
