package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.user.MyDashboardPageUI;

public class MyDashboardPageObject extends BasePage{
	WebDriver driver;
	public MyDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public boolean checkDisplayContactInfor() {
		waitForElementVisible(driver, MyDashboardPageUI.CONTACT_INFOR_TEXT);
		return getElementText(driver, MyDashboardPageUI.CONTACT_INFOR_TEXT).contains("Auto Testing");
	}

}
