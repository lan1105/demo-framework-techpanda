package pageObjects.navigation;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObjects.user.AboutUsPageObject;
import pageObjects.user.AccountInformationPageObject;
import pageObjects.user.AddressBookPageObject;
import pageObjects.user.BillingAgreementsPageObject;
import pageObjects.user.MyAccountPageObject;
import pageObjects.user.MyApplicationsPageObject;
import pageObjects.user.MyDashBoardPageObject;
import pageObjects.user.MyOrdersPageObject;
import pageObjects.user.MyProductReviewsPageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.SearchTermsPageObject;
import pageUI.user.MyDashboardPageUI;
import pageUIs.navigation.FooterContainerPageUI;
import pageUIs.navigation.SideBarMyDashBoardPageUI;

public class SideBarMyDashBoardPageObject extends BasePage{
	WebDriver driver;
	
	public SideBarMyDashBoardPageObject (WebDriver driver) {
		this.driver = driver;
	}
	
	public AccountInformationPageObject openAccountInformationPage() {
		waitForElementClickable(driver, SideBarMyDashBoardPageUI.ACCOUNT_INFORMATION_LINK);
		clickToElement(driver, SideBarMyDashBoardPageUI.ACCOUNT_INFORMATION_LINK);
		return PageGeneratorManager.getAccountInformationPage(driver);
	}
	
	public MyDashBoardPageObject openMyDashBoardPage() {
		waitForElementClickable(driver, SideBarMyDashBoardPageUI.ACCOUNT_INFORMATION_LINK);
		clickToElement(driver, SideBarMyDashBoardPageUI.ACCOUNT_INFORMATION_LINK);
		return PageGeneratorManager.getMyDashBoardPage(driver);
	}
	
	public AddressBookPageObject openAddressBookPage() {
		waitForElementClickable(driver, SideBarMyDashBoardPageUI.ADDRESS_BOOK_LINK);
		clickToElement(driver, SideBarMyDashBoardPageUI.ADDRESS_BOOK_LINK);
		return PageGeneratorManager.getAddressBookPage(driver);
	}
	
	public MyOrdersPageObject openMyOrdersPage() {
		waitForElementClickable(driver, SideBarMyDashBoardPageUI.MY_ORDERS_LINK);
		clickToElement(driver, SideBarMyDashBoardPageUI.MY_ORDERS_LINK);
		return PageGeneratorManager.getMyOrdersPage(driver);
	}
	
	public BillingAgreementsPageObject openBillingAgreementsPage() {
		waitForElementClickable(driver, SideBarMyDashBoardPageUI.BILLING_AGREEMENTS_LINK);
		clickToElement(driver, SideBarMyDashBoardPageUI.BILLING_AGREEMENTS_LINK);
		return PageGeneratorManager.getBillingAgreementsPage(driver);
	}
	
	public MyProductReviewsPageObject openMyProductReviewPage() {
		waitForElementClickable(driver, SideBarMyDashBoardPageUI.MY_PRODUCT_REVIEWS_LINK);
		clickToElement(driver, SideBarMyDashBoardPageUI.MY_PRODUCT_REVIEWS_LINK);
		return PageGeneratorManager.getMyProductReviewPage(driver);
	}
	
	public MyApplicationsPageObject openMyApplicationsPage() {
		waitForElementClickable(driver, SideBarMyDashBoardPageUI.MY_APPLICATION_LINK);
		clickToElement(driver, SideBarMyDashBoardPageUI.MY_APPLICATION_LINK);
		return PageGeneratorManager.getMyApplicationsPage(driver);
	}
	
	// Khong co return ve bat ky 1 page nao het
	public void openSideBarLinkByPageName(String pageName) {
		waitForElementClickable(driver, SideBarMyDashBoardPageUI.DYNAMIC_SIDE_BAR_LINK , pageName);
		clickToElement(driver,SideBarMyDashBoardPageUI.DYNAMIC_SIDE_BAR_LINK  , pageName);
	}

}
