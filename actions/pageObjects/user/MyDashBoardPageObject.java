package pageObjects.user;

import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import pageObjects.navigation.SideBarMyDashBoardPageObject;
import pageUI.user.MyDashboardPageUI;
import pageUI.user.UserLoginPageUI;

public class MyDashBoardPageObject extends SideBarMyDashBoardPageObject{
	WebDriver driver;
	
	public MyDashBoardPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	@Step("Verify contact information is displayed")
	public boolean isContactInforDisplayed(String contactInfor) {
		waitForElementVisible(driver, MyDashboardPageUI.CONTACT_INFOR_TEXT);
		String actualContactInforText = getElementText(driver, MyDashboardPageUI.CONTACT_INFOR_TEXT);
		return actualContactInforText.contains(contactInfor);
	}

	@Step("Verify success message is displayed when account information is saved successfuly")
	public boolean isAccountInformationMessageDisplayed() {
		waitForElementVisible(driver, MyDashboardPageUI.ACCOUNT_INFORMATION_SAVED_MESSAGE);
		return isElementDisplayed(driver, MyDashboardPageUI.ACCOUNT_INFORMATION_SAVED_MESSAGE);
	}

	public String getSuccessRegisterMessage() {
		waitForElementVisible(driver, MyDashboardPageUI.SUCCESS_MESSAGE_REGISTER);
		return getElementText(driver, MyDashboardPageUI.SUCCESS_MESSAGE_REGISTER);
	}



}
