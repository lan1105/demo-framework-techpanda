package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.admin.AdminLoginPageObject;
import pageObjects.navigation.FooterContainerPageObject;
import pageObjects.user.PageGeneratorManager;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageUI.admin.AdminHomePageUI;
import pageUI.user.UserHomePageUI;

public class BasePage {

	public static BasePage getBasePageInstance() {
		return new BasePage();
	}
	
	public void openPageURL(WebDriver driver, String pageURL) {
		driver.get(pageURL);
	}

	public String getPageURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backtoPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardtoPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, longTimeout).until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public void sendkeyToAller(WebDriver driver, String valueToSendkey) {
		waitForAlertPresence(driver).sendKeys(valueToSendkey);
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void switchToWindowByID(WebDriver driver, String expectedID) {
		Set<String> allTabIDs = driver.getWindowHandles();

		for (String id : allTabIDs) {
			if (!id.equals(expectedID)) {
				driver.switchTo().window(id);
				break;
			}
		}

	}

	public void switchToWindowByTitle(WebDriver driver, String expectedTitle) {
		Set<String> allTabIDs = driver.getWindowHandles();

		for (String id : allTabIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle)) {
				break;
			}
		}
	}

	public boolean closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String runWindows : allWindows) {
			if (runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}
	
	public String castRestParameter(String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		return locator;
	}

	// Ham dung chung find xpath
	private By getByLocator(String locator) {
		By by = null;
		if(locator.startsWith("id=")|| locator.startsWith("ID=") || locator.startsWith("Id=") ) {
			by = By.id(locator.substring(3));
		}else if(locator.startsWith("class=") || locator.startsWith("Class=") || locator.startsWith("CLASS=")) {
			by = By.className(locator.substring(6));
		}else if(locator.startsWith("xpath=") || locator.startsWith("Xpath=") || locator.startsWith("XPATH=")) {
			by = By.xpath(locator.substring(6));
		}else if(locator.startsWith("css=")|| locator.startsWith("Css=") || locator.startsWith("CSS=")) {
			by = By.cssSelector(locator.substring(4));
		}else if(locator.startsWith("name=") || locator.startsWith("Name=") || locator.startsWith("NAME=")) {
			by = By.name(locator.substring(5));
		}else {
			throw new RuntimeException("Locator is invalid!");
		}
		return by;
	}

	// Ham chung dung cho findElement
	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByLocator(locator));
	}

	public List<WebElement> getListElement(WebDriver driver, String locator) {
		return driver.findElements(getByLocator(locator));
	}

	public void clickToElement(WebDriver driver, String locator) {
		getWebElement(driver, locator).click();
	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		getWebElement(driver, castRestParameter(locator, values)).click();
	}
	
	public void sendkeyToElement(WebDriver driver, String locator, String valueToInput) {
		WebElement element = getWebElement(driver, locator);
		element.clear();
		element.sendKeys(valueToInput);
	}
	
	public void sendkeyToElement(WebDriver driver, String locator, String valueToInput, String... values) {
		WebElement element = getWebElement(driver, castRestParameter(locator, values));
		element.clear();
		element.sendKeys(valueToInput);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemText) {
		Select select = new Select(getWebElement(driver, locator));
		select.deselectByVisibleText(itemText);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String itemText, String... values) {
		Select select = new Select(getWebElement(driver, castRestParameter(locator, values)));
		select.deselectByVisibleText(itemText);
	}

	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childXpath,String expectedItemText) {
		getWebElement(driver, parentXpath).click();
		sleepInsecond(2);

		List<WebElement> childItems = new WebDriverWait(driver,longTimeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childXpath)));

		for (WebElement tempElement : childItems) {
			if (tempElement.getText().trim().equals(expectedItemText)) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",tempElement);
				sleepInsecond(1);
				tempElement.click();
				sleepInsecond(1);
				break;

				}
			}
		}
	
	public String getElementText(WebDriver driver, String locator) {
		return getWebElement(driver, locator).getText();
	}
	
	public String getElementText(WebDriver driver, String locator, String... values) {
		return getWebElement(driver, castRestParameter(locator, values)).getText();
	}
	
	public String getFirstSelectedTextItem(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getElementAttributeValue(WebDriver driver, String locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}
	
	public String getElementAttributeValue(WebDriver driver, String locator, String attributeName, String... values) {
		return getWebElement(driver, castRestParameter(locator, values)).getAttribute(attributeName);
	}
	
	public String getElementCssValue(WebDriver driver, String locator,String propertyName ) {
		return getWebElement(driver, locator).getCssValue(propertyName);
	}
	
	public int getListElementSize(WebDriver driver, String locator) {
		return getListElement(driver, locator).size();
	}
	
	public int getListElementSize(WebDriver driver, String locator, String... values) {
		return getListElement(driver, castRestParameter(locator, values)).size();
	}
	
	public void checkToCheckboxOrRadio(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	public void uncheckToCheckbox(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if(element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isDisplayed();
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
		return getWebElement(driver, castRestParameter(locator, values)).isDisplayed();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator) {
		return getWebElement(driver, locator).isSelected();
	}
	
	public void switchToIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).perform();
	}
	
	public void rightClickToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.contextClick(getWebElement(driver, locator)).perform();
	}
	
	public void doubleClickToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.doubleClick(getWebElement(driver, locator)).perform();
	}
	
	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator	) {
		Actions action = new Actions (driver);
		action.dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locator), key).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locator, Keys key, String... values) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, castRestParameter(locator, values)), key).perform();
	}

	public void hightlightElement(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])",element, "border: 2px solid red; border-style: dashed;");
		sleepInsecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])",element, originalStyle);
	}
	
	public void clickToElementByJS(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
	}
	
	public void scrollToElementOnTop(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
	}
	
	public void scrollToElementOnDown(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
	}
	
	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', ' " + value +" ')", getWebElement(driver, locator));
	}
	
	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove +"');", getWebElement(driver, locator));
	}
	
	public String getElementValidationMessage(WebDriver driver, String locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("arguments[0].validationMessage;", getWebElement(driver, locator));
	}
	
	public boolean isImageLoaded(WebDriver driver, String locator) {
		boolean status = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth !='undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
		if(status) {
			return true;
		}
		return false;
	}
	
	public void waitForElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
	}
	
	public void waitForElementVisible(WebDriver driver, String locator, String... values) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castRestParameter(locator, values))));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator, String... values) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(castRestParameter(locator, values))));
	}
	
	public void waitForElementClickable(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
	}
	
	public void waitForElementClickable(WebDriver driver, WebElement element) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForElementClickable(WebDriver driver, String locator, String... values) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByLocator(castRestParameter(locator, values))));
	}
	
	/*
	public void uploadMultipleFiles(WebDriver driver, String... fileNames ) {
		String uploadFilePath = GlobalConstants.UPLOAD_PATH;
		String fullFileName = "";
		for(String file : fileNames) {
			fullFileName = fullFileName + uploadFilePath + file +"\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, HomePageUI.UPLOAD_FILE).sendKeys(fullFileName);;
	}
	*/
	
	public FooterContainerPageObject getFooterContainerPage(WebDriver driver) {
		return new FooterContainerPageObject(driver);
		
	}
	
	public UserHomePageObject openUserHomePage(WebDriver driver, String userURL) {
		openPageURL(driver, userURL);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	
	public AdminLoginPageObject openAdminLoginPage(WebDriver driver, String adminURL) {
		openPageURL(driver, adminURL);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}
	
	public UserHomePageObject clickToUserLogoutLink(WebDriver driver) {
		waitForElementVisible(driver, UserHomePageUI.HEADER_ACCOUNT_TEXT);
		clickToElement(driver, UserHomePageUI.HEADER_ACCOUNT_TEXT);
		waitForElementClickable(driver, UserHomePageUI.HEADER_LOGOUT_BUTTON);
		clickToElement(driver, UserHomePageUI.HEADER_LOGOUT_BUTTON);
		return PageGeneratorManager.getUserHomePage(driver);
	}
	
	public AdminLoginPageObject clickToAdminLogoutLink(WebDriver driver) {
		waitForElementClickable(driver, AdminHomePageUI.LOG_OUT_LINK);
		clickToElement(driver, AdminHomePageUI.LOG_OUT_LINK);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}
	
	public void sleepInsecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = 5;

}
