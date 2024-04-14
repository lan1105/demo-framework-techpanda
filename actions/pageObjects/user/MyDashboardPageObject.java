package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.user.MyDashboardPageUI;

public class MyDashboardPageObject extends BasePage{
	WebDriver driver;
	
	public MyDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isContactInforDisplayed(String contactInfor) {
		waitForElementVisible(driver, MyDashboardPageUI.CONTACT_INFOR_TEXT);
		String actualContactInforText = getElementText(driver, MyDashboardPageUI.CONTACT_INFOR_TEXT);
		return actualContactInforText.contains(contactInfor);
	}

	public AccountInformationPageObject clickToAccountInformationLink() {
		waitForElementClickable(driver, MyDashboardPageUI.ACCOUNT_INFORMATION_LINK);
		clickToElement(driver, MyDashboardPageUI.ACCOUNT_INFORMATION_LINK);
		return PageGeneratorManager.getAccountInformationPage(driver);
		
	}


	public boolean isAccountInformationMessageDisplayed() {
		waitForElementVisible(driver, MyDashboardPageUI.ACCOUNT_INFORMATION_SAVED_MESSAGE);
		return isElementDisplayed(driver, MyDashboardPageUI.ACCOUNT_INFORMATION_SAVED_MESSAGE);
	}


}
