package pageObjects.user;

import org.openqa.selenium.WebDriver;

import pageObjects.navigation.SideBarMyDashBoardPageObject;

public class BillingAgreementsPageObject extends SideBarMyDashBoardPageObject{
	WebDriver driver;

	public BillingAgreementsPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
