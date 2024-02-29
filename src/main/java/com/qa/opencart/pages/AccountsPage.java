package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	private By logout = By.linkText("Logout");
	private By searchField = By.name("search");
	private By accHeader = By.xpath("//div[@id='content']/h2");
	// private By searchButton = By.xpath("//span[@class='input-group-btn']");
	private By searchButton = By.cssSelector("div#search button");

	@Step("get the account page title")
	public String getAccountPageTitle() {
		return eleUtil.waitForTitleIs(AppConstant.ACCOUNTS_PAGE_TITLE, AppConstant.DEFAULT_SHORT_WAIT);
	}

	@Step("Get the account page Url")
	public String getAccountPageUrl() {
		return eleUtil.waitForURLContains(AppConstant.ACCOUNTS_PAGE_URL_FRACTION, AppConstant.DEFAULT_SHORT_WAIT);
	}

	@Step("logout link is diplayed")
	public boolean isLogoutLinkExist() {
		return eleUtil.waitForVisibilityOfElement(logout, AppConstant.DEFAULT_SHORT_WAIT).isDisplayed();
	}

	@Step("If logout link is displayed then select it")
	public void logout() {
		if (isLogoutLinkExist()) {
			eleUtil.doClick(logout);
		}
	}

	@Step("Search fiels is displayed")
	public boolean isSearchFieldExist() {
		return eleUtil.waitForVisibilityOfElement(searchField, AppConstant.DEFAULT_SHORT_WAIT).isDisplayed();
	}

	@Step("Get all account page headers")
	public List<String> getAllHeaders() {
		List<WebElement> accHeadersList = eleUtil.waitForVisibilityOfElements(accHeader,
				AppConstant.DEFAULT_SHORT_WAIT);
		List<String> headersList = new ArrayList<String>();
		for (WebElement e : accHeadersList) {
			String headerText = e.getText();
			headersList.add(headerText);
		}
		return headersList;
	}

	@Step("Search the product with product name :{0}")
	public SearchResultPage doSearchResult(String searchKey) {
		eleUtil.waitForVisibilityOfElement(searchField, AppConstant.DEFAULT_MEDIUM_WAIT).clear();
		eleUtil.waitForVisibilityOfElement(searchField, AppConstant.DEFAULT_MEDIUM_WAIT).sendKeys(searchKey);
		eleUtil.doClick(searchButton);
		return new SearchResultPage(driver);
	}

}
