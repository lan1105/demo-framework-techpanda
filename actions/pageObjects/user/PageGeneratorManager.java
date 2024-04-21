package pageObjects.user;

import org.openqa.selenium.WebDriver;

import pageObjects.admin.AdminHomePageObject;
import pageObjects.admin.AdminLoginPageObject;

public class PageGeneratorManager {
	
	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}
	
	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}
	
	public static MyDashboardPageObject getMyDashboardPage(WebDriver driver) {
		return new MyDashboardPageObject(driver);
	}
	
	public static AccountInformationPageObject getAccountInformationPage(WebDriver driver) {
		return new AccountInformationPageObject(driver);
	}

	public static AboutUsPageObject getAboutUsPage(WebDriver driver) {
		return new AboutUsPageObject(driver);
	}

	public static SearchTermsPageObject getSearchTermsPage(WebDriver driver) {
		return new SearchTermsPageObject(driver);
	}

	public static MyAccountPageObject getMyAccountPage(WebDriver driver) {
		return new MyAccountPageObject(driver);
	}
	
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}
	
	
	public static AdminHomePageObject getAdminHomePage(WebDriver driver) {
		return new AdminHomePageObject(driver);
	}


}
