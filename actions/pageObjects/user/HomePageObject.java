package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.user.HomePageUI;

public class HomePageObject extends BasePage{
	
	//Ham khoi tao - Constructor
	//Goi dau tien khi khoi tao 1 class len
	//Cung ten voi class
	//Khong co kieu tra ve
	//Co tham so hoac khong co tham so
	//Map driver giua 2 class voi nhau (Class testcase voi Class hien tai)
	WebDriver driver;
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToMyAccountLink() {
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		
	}

}
