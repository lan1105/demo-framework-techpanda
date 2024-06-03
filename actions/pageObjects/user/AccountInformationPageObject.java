package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObjects.navigation.SideBarMyDashBoardPageObject;
import pageUI.user.AccountInformationPageUI;

public class AccountInformationPageObject extends SideBarMyDashBoardPageObject {
	WebDriver driver;
	public AccountInformationPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	public void inputToFirstNameField(String editFirstName) {
		waitForElementVisible(driver, AccountInformationPageUI.FIRST_NAME_FIELD);
		sendkeyToElement(driver, AccountInformationPageUI.FIRST_NAME_FIELD, editFirstName);
		
	}

	public void inputToLastNameField(String editLastName) {
		waitForElementVisible(driver, AccountInformationPageUI.LAST_NAME_FIELD);
		sendkeyToElement(driver, AccountInformationPageUI.LAST_NAME_FIELD, editLastName);
		
	}
	public void inputToEmailAddressField(String editEmailAddressField) {
		waitForElementVisible(driver, AccountInformationPageUI.EMAIL_ADDRESS_FIELD);
		sendkeyToElement(driver, AccountInformationPageUI.EMAIL_ADDRESS_FIELD, editEmailAddressField);
		
	}
	public void inputToCurrentPasswordField(String currentPassword) {
		waitForElementVisible(driver, AccountInformationPageUI.CURRENT_PASSWORD_FIELD);
		sendkeyToElement(driver, AccountInformationPageUI.CURRENT_PASSWORD_FIELD, currentPassword);
		
	}
	public MyDashBoardPageObject clickToSaveButton() {
		waitForElementClickable(driver, AccountInformationPageUI.SAVE_BUTTON);
		clickToElement(driver, AccountInformationPageUI.SAVE_BUTTON);
		return  new PageGeneratorManager().getMyDashBoardPage(driver);
	}
	public boolean isFirstNameDisplayed(String fistName) {
		waitForElementVisible(driver, AccountInformationPageUI.FIRST_NAME_FIELD, fistName);
		return isElementDisplayed(driver, AccountInformationPageUI.FIRST_NAME_FIELD, fistName);
	}
	public boolean isLastNameDisplayed(String lastName) {
		waitForElementVisible(driver, AccountInformationPageUI.LAST_NAME_FIELD, lastName);
		return isElementDisplayed(driver, AccountInformationPageUI.LAST_NAME_FIELD, lastName);
	}
	public boolean isEmailAddressDisplayed(String emailAddress) {
		waitForElementVisible(driver, AccountInformationPageUI.EMAIL_ADDRESS_FIELD, emailAddress);
		return isElementDisplayed(driver, AccountInformationPageUI.EMAIL_ADDRESS_FIELD, emailAddress);
	}

	public void clear() {
		waitForElementVisible(driver, AccountInformationPageUI.FIRST_NAME_FIELD);
		clear();
		
	}


	

}
