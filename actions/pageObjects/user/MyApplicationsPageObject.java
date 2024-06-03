package pageObjects.user;

import org.openqa.selenium.WebDriver;

import pageObjects.navigation.SideBarMyDashBoardPageObject;

public class MyApplicationsPageObject extends SideBarMyDashBoardPageObject {
	WebDriver driver;

	public MyApplicationsPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
