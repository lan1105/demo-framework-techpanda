package pageObjects.user;

import org.openqa.selenium.WebDriver;

import pageObjects.navigation.SideBarMyDashBoardPageObject;

public class MyProductReviewsPageObject  extends SideBarMyDashBoardPageObject{
	WebDriver driver;

	public MyProductReviewsPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
