package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUI.user.UserLoginPageUI;
import pageUI.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
	WebDriver driver;

	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;

	}

	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
		sendkeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
		
	}

	public void inputToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
		sendkeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, lastName);
		
	}

	public void inputToEmailAddressTextbox(String emailAddress) {
		waitForElementVisible(driver, UserRegisterPageUI.EMAIL_ADDRESS_TEXTBOX);
		sendkeyToElement(driver, UserRegisterPageUI.EMAIL_ADDRESS_TEXTBOX, emailAddress);
		
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);
		
	}

	public void inputToConfirmPasswordTextbox(String password) {
		waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
		
	}

	public MyDashBoardPageObject clickToRegisterButton() {
		waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
		return new PageGeneratorManager().getMyDashBoardPage(driver);
	}




}
