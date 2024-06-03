package pageObjects.user;

import org.openqa.selenium.WebDriver;

import pageObjects.navigation.SideBarMyDashBoardPageObject;

public class AddressBookPageObject extends SideBarMyDashBoardPageObject{
	WebDriver driver;
	public AddressBookPageObject(WebDriver driver) {
		super(driver); 
		this.driver = driver;
	}

}
