package pageObjects.user;

import org.openqa.selenium.WebDriver;

import pageObjects.navigation.FooterContainerPageObject;

public class SearchTermsPageObject extends FooterContainerPageObject{
	WebDriver driver;

	public SearchTermsPageObject(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

}
