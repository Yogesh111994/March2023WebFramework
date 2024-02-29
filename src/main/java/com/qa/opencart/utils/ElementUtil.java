package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.factory.DriverFactory;

import io.netty.handler.timeout.TimeoutException;
import io.qameta.allure.Step;


public class ElementUtil {

	WebDriver driver;
	JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil=new JavaScriptUtil(driver);
	}

	public By getBy(String locatorType, String locatorValue) {

		By by = null;
		switch (locatorType) {
		case "id":
			by = By.id(locatorValue);
			break;
		case "name":
			by = By.name(locatorValue);
			break;
		case "class":
			by = By.className(locatorValue);
			break;
		case "xpath":
			by = By.xpath(locatorValue);
			break;
		case "css":
			by = By.cssSelector(locatorValue);
			break;
		case "linktext":
			by = By.linkText(locatorValue);
			break;
		case "partiallinktext":
			by = By.partialLinkText(locatorValue);
			break;
		case "tag":
			by = By.tagName(locatorValue);
			break;

		default: {
			System.out.println("Please pass the right locator type" + locatorType);
			throw new FrameworkException("WRONG LOCATOR TYPE");
		}

		}
		return by;

	}
	
	public void isHighlight(WebElement element) {
		if(Boolean.parseBoolean( DriverFactory.highlight) ){
			jsUtil.flash(element);
		}
	}

	/**
	 * doSendKeys() method is used when user want to send some string value which
	 * accept By locator and string
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSendKeys(By locator, String value) {
		getElement(locator).sendKeys(value);
	}

	/**
	 * Overloaded method of doSendKeys()
	 * 
	 * @param locatorType
	 * @param locatorValue
	 * @param value
	 */
	public void doSendKeys(String locatorType, String locatorValue, String value) {
		getElement(locatorType, locatorValue).sendKeys(value);
	}

	/**
	 * getElement() method which return the WebElement by accepting reference of By
	 * class as argument
	 * 
	 * @param locator
	 * @return
	 */
	public WebElement getElement(By locator) {
		WebElement element= driver.findElement(locator);
		isHighlight(element);
		return element;
	}

	/**
	 * Overloaded getElement() method which return the Web element and which accept
	 * string as an argument
	 * 
	 * @param locatorType
	 * @param locatorValue
	 * @return
	 */

	public WebElement getElement(String locatorType, String locatorValue) {
		return driver.findElement(getBy(locatorValue, locatorValue));
	}

	/**
	 * doClick() method which accept the By class reference variable as argument
	 * 
	 * @param locator
	 */
	public void doClick(By locator) {
		getElement(locator).click();
	}

	/**
	 * Overloaded doClick() method
	 * 
	 * @param locatorType
	 * @param locatorValue
	 */
	public void doClick(String locatorType, String locatorValue) {
		getElement(locatorType, locatorValue).click();
	}

	/**
	 * doElementGetText() method which return string and accept By class reference
	 * variable as argument
	 * 
	 * @param locator
	 * @return
	 */
	public String doElementGetText(By locator) {
		return getElement(locator).getText();
	}

	/**
	 * doElementGetText() method which return string and accept String reference
	 * variable as argument
	 * 
	 * @param locatorType
	 * @param locatorValue
	 * @return
	 */
	public String doElementGetText(String locatorType, String locatorValue) {
		return getElement(locatorType, locatorValue).getText();
	}

	/**
	 * doGetAttribute() method which return the string and accept the By reference
	 * and String object
	 * 
	 * @param locator
	 * @return
	 */
	public String doGetAttribute(By locator, String attrName) {
		return getElement(locator).getAttribute(attrName);
	}

	/**
	 * Overloaded doGetAttribute()
	 * 
	 * @param locatorType
	 * @param locatorValue
	 * @param value
	 * @return
	 */
	public String doGetAttribute(String locatorType, String locatorValue, String attrName) {
		return getElement(locatorType, locatorType).getAttribute(attrName);
	}

	/**
	 * getElements() method which returns the List<WebElement> and accept the By
	 * reference variable
	 * 
	 * @param locator
	 * @return
	 */
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	/**
	 * getElementsCount() which return int and accept the By reference variable
	 * 
	 * @param locator
	 * @return
	 */
	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	// WAF : capture the text of all the page links and return List<String>.

	/**
	 * getElementTextList() which return the List<String> and accept the By
	 * reference variable
	 * 
	 * @param locator
	 * @return
	 */
	public List<String> getElementTextList(By locator) {
		List<WebElement> eleList = getElements(locator); // 30 elements
		List<String> eleTextList = new ArrayList<String>(); // pc=0 {}
		for (WebElement e : eleList) {
			String text = e.getText();
			if (text.length() != 0) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}

	// WAF: capture specific attribute from the list:

	public List<String> getElementsAttributeList(By locator, String attrName) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleAttrList = new ArrayList<String>(); // pc=0 {}
		for (WebElement e : eleList) {
			String attrValue = e.getAttribute(attrName);
			eleAttrList.add(attrValue);
		}
		return eleAttrList;
	}

	/**
	 * Search() is used for every search option
	 * 
	 * @param searchField
	 * @param suggestions
	 * @param searchKey
	 * @param suggName
	 * @throws InterruptedException
	 */
	public void Search(By searchField, By suggestions, String searchKey, String suggName) throws InterruptedException {
		doSendKeys(searchField, searchKey);
		Thread.sleep(3000);
		List<WebElement> suggList = driver.findElements(suggestions);
		System.out.println(suggList.size());
		for (WebElement e : suggList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(suggName)) {
				e.click();
				break;
			}
		}
	}

	/**
	 * clickOnElement() method is used to select the element from the element list
	 * 
	 * @param locator
	 * @param eleText
	 */
	public void clikcOnElement(By locator, String eleText) {
		List<WebElement> eleList = getElements(locator);
		System.out.println(eleList.size());
		for (WebElement e : eleList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(eleText)) {
				e.click();
				break;
			}
		}
	}

	/**
	 * checkElementPresent() method to check the element is present
	 * 
	 * @param locator
	 * @return
	 */
	public boolean checkElementPresent(By locator) {
		return driver.findElements(locator).size() == 1 ? true : false;
	}

	public Select doSelect(By locator) {
		Select select = new Select(getElement(locator));
		return select;
	}

	/**
	 * doSelectDropDownByIndex() method is used to select option from drop down by
	 * providing the index
	 * 
	 * @param locator
	 * @param index
	 */
	public void doSelectDropDownByIndex(By locator, int index) {
		doSelect(locator).selectByIndex(index);
	}

	/**
	 * doSelectDropDownByVisibleText() method is used to select option from drop
	 * down by providing the text
	 * 
	 * @param locator
	 * @param text
	 */
	public void doSelectDropDownByVisibleText(By locator, String text) {
		doSelect(locator).selectByVisibleText(text);
	}

	/**
	 * doSelectDropDownByValue() method is used to select option from drop down by
	 * providing the value
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSelectDropDownByValue(By locator, String value) {
		doSelect(locator).selectByValue(value);
	}

	/**
	 * selectDropDownValue() method is used to select drop down option without using
	 * select Class
	 * 
	 * @param locator
	 * @param value
	 */
	public void selectDropDownValue(By locator, String value) {
		List<WebElement> options = getElements(locator);
		for (WebElement e : options) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	/**
	 * getDropDownOptionsCount() method is used to get the drop down count
	 * 
	 * @param locator
	 * @return
	 */

	public int getDropDownOptionsCount(By locator) {
		return doSelect(locator).getOptions().size();
	}

	/**
	 * getDropDownOption() return the options value
	 * 
	 * @param locator
	 * @return
	 */
	public List<String> getDropDownOption(By locator) {
		List<WebElement> optionsList = doSelect(locator).getOptions();
		List<String> optionText = new ArrayList<String>();
		for (WebElement e : optionsList) {
			String text = e.getText();
			optionText.add(text);
		}
		return optionText;
	}

	public boolean isDropDownMultiple(By locator) {
		return doSelect(locator).isMultiple() ? true : false;
	}

	/**
	 * This method is used to select the values from the drop down. It can select;
	 * 1. single selection 2. Multiple selection 3. All Selection: please pass "all"
	 * as a value to select all the values
	 * 
	 * @param locator
	 * @param values
	 */
	public void selectDropDownMultipleValues(By locator, By optionLocator, String... values) {
		if (isDropDownMultiple(locator)) {
			if (values[0].equalsIgnoreCase("all")) {
				List<WebElement> optionsList = getElements(optionLocator);
				for (WebElement e : optionsList) {
					e.click();
				}
			} else {
				for (String value : values) {
					doSelect(locator).selectByVisibleText(value);
				}
			}

		}

	}
	// *****************Actions utils ***************//

	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).perform();
	}

	public void doActionsClick(By locator) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).perform();
	}

	public void twoLevelMenuHandle(By parentMenuLocator, By childMenuLocator) throws InterruptedException {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(parentMenuLocator)).build().perform();
		Thread.sleep(1000);
		doClick(childMenuLocator);
	}

	public void fourLevelMenuHandle(By parentMenuLocator, By firstChildMenuLocaor, By secondChildMenuLocaor,
			By thirdChildMenuLocaor) throws InterruptedException {

		Actions act = new Actions(driver);

		doClick(parentMenuLocator);
		Thread.sleep(1000);

		act.moveToElement(getElement(firstChildMenuLocaor)).build().perform();

		Thread.sleep(1000);

		act.moveToElement(getElement(secondChildMenuLocaor)).build().perform();

		Thread.sleep(1000);

		doClick(thirdChildMenuLocaor);
	}

	public void doActionsSendKeysWithPause(By locator, String value) {
		Actions act = new Actions(driver);
		char val[] = value.toCharArray();
		for (char c : val) {
			act.sendKeys(getElement(locator), String.valueOf(c)).pause(500).build().perform();
		}
	}

	// ****************Wait Utils***************//

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible on the page.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForPresenceOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement element= wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		isHighlight(element);
		return element;
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page.
	 * This does not necessarily mean that the element is visible on the page.
	 * 
	 * @param locator
	 * @param timeOut
	 * @param intervalTime
	 * @return
	 */
	public WebElement waitForPresenceOfElement(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
		WebElement element= wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		isHighlight(element);
		return element;
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForVisibilityOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		isHighlight(element);
		return element;
	}

	/**
	 * An expectation for checking that an element is present on the DOM of a page
	 * and visible. Visibility means that the element is not only displayed but also
	 * has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @param intervalTime
	 * @return
	 */
	public WebElement waitForVisibilityOfElement(By locator, int timeOut, int intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(intervalTime));
		WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		isHighlight(element);
		return element;
	}

	public List<WebElement> waitForVisibilityOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}

	/**
	 * An expectation for checking that there is at least one element present on a
	 * web page.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public List<WebElement> waitForPresenceOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public void doClickWithWait(By locator, int timeOut) {
		waitForVisibilityOfElement(locator, timeOut).click();
	}

	public void doSendKeysWithWait(By locator, String value, int timeOut) {
		waitForVisibilityOfElement(locator, timeOut).sendKeys(value);
	}

	public String waitForTitleContains(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
				return driver.getTitle();
			}
		} catch (TimeoutException e) {
			System.out.println(titleFraction + " title value is not present....");
			e.printStackTrace();
		}
		return null;

	}

	@Step(" Waiting for page title :{0} timeout :{1} ")
	public String waitForTitleIs(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.titleIs(title))) {
				return driver.getTitle();
			}
		} catch (TimeoutException e) {
			System.out.println(title + " title value is not present....");
			e.printStackTrace();
		}
		return null;

	}

	@Step("Waiting for the URL title :{0} and timeout :{1}")
	public String waitForURLContains(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
				return driver.getCurrentUrl();
			}
		} catch (TimeoutException e) {
			System.out.println(urlFraction + " url value is not present....");
			e.printStackTrace();
		}
		return null;

	}

	public String waitForURLToBe(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.urlToBe(url))) {
				return driver.getCurrentUrl();
			}
		} catch (TimeoutException e) {
			System.out.println(url + " url value is not present....");
			e.printStackTrace();
		}
		return null;

	}

	public Alert waitForJSAlert(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptJSAlert(int timeOut) {
		waitForJSAlert(timeOut).accept();
	}

	public void dismissJSAlert(int timeOut) {
		waitForJSAlert(timeOut).dismiss();
	}

	public String getJsAlertText(int timeOut) {
		return waitForJSAlert(timeOut).getText();
	}

	public void enterValueOnJsAlert(int timeOut, String value) {
		waitForJSAlert(timeOut).sendKeys(value);
	}

	public boolean checkNewWindowExist(int timeOut, int expectedNumberOfWindows) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows))) {
				return true;
			}
		} catch (TimeoutException e) {
			System.out.println("number of windows are not same....");
		}
		return false;
	}

	public void waitForFrameByLocator(By frameLocator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
	}

	public void waitForFrameByIndex(int frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public void waitForFrameByIDOrName(String IDOrName, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IDOrName));
	}

	public void waitForFrameByElement(WebElement frameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	public WebElement waitForElementWithFluentWait(By locator, int timeOut, int intervalTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(intervalTime))
				.withMessage("--time out is done...element is not found....").ignoring(NoSuchElementException.class)
				.ignoring(ElementNotInteractableException.class);
		WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		isHighlight(element);
		return element;
	}

	// *****************Custom Wait***********************//

	public WebElement retryingElement(By locator, int timeOut) {

		WebElement element = null;
		int attempts = 0;

		while (attempts < timeOut) {
			try {
				element = getElement(locator);
				System.out.println("element is found...." + locator + " in attempt " + attempts);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("element is not found...." + locator + " in attempt " + attempts);
				try {
					Thread.sleep(500);// default polling time = 500 ms
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}

			attempts++;
		}

		if (element == null) {
			System.out.println("element is not found....tried for " + timeOut + " times " + " with the interval of "
					+ 500 + " milli seconds ");
			throw new FrameworkException("No Such Element");
		}

		isHighlight(element);
		return element;
	}

	public WebElement retryingElement(By locator, int timeOut, int intervalTime) {

		WebElement element = null;
		int attempts = 0;

		while (attempts < timeOut) {
			try {
				element = getElement(locator);
				System.out.println("element is found...." + locator + " in attempt " + attempts);
				break;
			} catch (NoSuchElementException e) {
				System.out.println("element is not found...." + locator + " in attempt " + attempts);
				try {
					Thread.sleep(intervalTime);// custom polling time
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}

			}

			attempts++;
		}

		if (element == null) {
			System.out.println("element is not found....tried for " + timeOut + " times " + " with the interval of "
					+ 500 + " milli seconds ");
			throw new FrameworkException("No Such Element");
		}

		isHighlight(element);
		return element;
	}

	public boolean isPageLoaded(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete'"))
				.toString();
		return Boolean.parseBoolean(flag);
	}
}
