package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUI.user.UserHomePageUI;

public class UserHomePageObject extends BasePage{
	
	//Ham khoi tao - Constructor
	//Goi dau tien khi khoi tao 1 class len
	//Cung ten voi class
	//Khong co kieu tra ve
	//Co tham so hoac khong co tham so
	//Map driver giua 2 class voi nhau (Class testcase voi Class hien tai)
	WebDriver driver;
	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click to My Account Link")
	public UserLoginPageObject clickToMyAccountLink() {
		waitForElementClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
		
	}

}
