package pageObjects.navigation;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObjects.user.AboutUsPageObject;
import pageObjects.user.MyAccountPageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.SearchTermsPageObject;
import pageUIs.navigation.FooterContainerPageUI;

public class FooterContainerPageObject extends BasePage{
	WebDriver driver;
	
	public FooterContainerPageObject (WebDriver driver) {
		this.driver = driver;
	}
	
	public AboutUsPageObject openAboutUsPage() {
		waitForElementVisible(driver, FooterContainerPageUI.ABOUT_US_LINK);
		clickToElement(driver, FooterContainerPageUI.ABOUT_US_LINK);
		return PageGeneratorManager.getAboutUsPage(driver);
	}
	
	public SearchTermsPageObject openSearchTermsPage() {
		waitForElementVisible(driver, FooterContainerPageUI.SEARCH_TERMS);
		clickToElement(driver, FooterContainerPageUI.SEARCH_TERMS);
		return PageGeneratorManager.getSearchTermsPage(driver);
	}
	
	public MyAccountPageObject openMyAccountPage() {
		waitForElementVisible(driver, FooterContainerPageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, FooterContainerPageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getMyAccountPage(driver);
	}
}
