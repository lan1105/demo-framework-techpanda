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
	
	public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}
	
	
	public static MyDashBoardPageObject getMyDashBoardPage(WebDriver driver) {
		return new MyDashBoardPageObject(driver);
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

	public static AddressBookPageObject getAddressBookPage(WebDriver driver) {
		return new AddressBookPageObject(driver);
		
	}

	public static MyOrdersPageObject getMyOrdersPage(WebDriver driver) {
		return new MyOrdersPageObject(driver);
	}

	public static BillingAgreementsPageObject getBillingAgreementsPage(WebDriver driver) {
		return new BillingAgreementsPageObject(driver);
	}

	public static MyProductReviewsPageObject getMyProductReviewPage(WebDriver driver) {
		return new MyProductReviewsPageObject(driver);
	}

	public static MyApplicationsPageObject getMyApplicationsPage(WebDriver driver) {
		return new MyApplicationsPageObject(driver);
	}


}
