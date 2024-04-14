package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.user.AccountInformationPageUI;

public class AccountInformationPageObject extends BasePage {
	WebDriver driver;
	public AccountInformationPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void inputToFirstNameField(String firstName) {
		waitForElementVisible(driver, AccountInformationPageUI.FIRST_NAME_FIELD);
		sendkeyToElement(driver, AccountInformationPageUI.FIRST_NAME_FIELD, firstName);
		
	}

	public void inputToLastNameField(String lastName) {
		waitForElementVisible(driver, AccountInformationPageUI.LAST_NAME_FIELD);
		sendkeyToElement(driver, AccountInformationPageUI.LAST_NAME_FIELD, lastName);
		
	}
	public void inputToEmailAddressField(String emailAddressField) {
		waitForElementVisible(driver, AccountInformationPageUI.EMAIL_ADDRESS_FIELD);
		sendkeyToElement(driver, AccountInformationPageUI.EMAIL_ADDRESS_FIELD, emailAddressField);
		
	}
	public void inputToCurrentPasswordField(String currentPassword) {
		waitForElementVisible(driver, AccountInformationPageUI.CURRENT_PASSWORD_FIELD);
		sendkeyToElement(driver, AccountInformationPageUI.CURRENT_PASSWORD_FIELD, currentPassword);
		
	}
	public MyDashboardPageObject clickToSaveButton() {
		waitForElementClickable(driver, AccountInformationPageUI.SAVE_BUTTON);
		clickToElement(driver, AccountInformationPageUI.SAVE_BUTTON);
		return  new PageGeneratorManager().getMyDashboardPage(driver);
	}
	

}
