package pageObjects.user;

import org.openqa.selenium.WebDriver;

import pageObjects.navigation.SideBarMyDashBoardPageObject;

public class MyOrdersPageObject extends SideBarMyDashBoardPageObject{
	WebDriver driver;
	public MyOrdersPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
