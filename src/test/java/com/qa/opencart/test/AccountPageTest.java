package com.qa.opencart.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;



public class AccountPageTest extends BaseTest {

	@Description("Setup method for Account page")
	@Severity(SeverityLevel.BLOCKER)
	@BeforeClass
	public void loginTest() {
		 accountPage = loginPage.doLogin(prop.getProperty("userId"), prop.getProperty("password"));
		// accountPage = loginPage.doLogin("biradaryogesh02@gmail.com", "8799978313");
	}
	
	@Description("Setup method for Account page")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void accountPageTitleTest() {
	Assert.assertEquals(	accountPage.getAccountPageTitle(),AppConstant.ACCOUNTS_PAGE_TITLE);
	}
	
	@Description("Setup method for Account page")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void accountPageUrlTest() {
	Assert.assertTrue(	accountPage.getAccountPageUrl().contains(AppConstant.ACCOUNTS_PAGE_URL_FRACTION));
	}
	
	@Description("Logout link is exist")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void logoutLinkExistTest() {
		Assert.assertTrue(accountPage.isLogoutLinkExist());
	}
	
	@Description("Check the search fiels is present ")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void searchFieldExistTest() {
		Assert.assertTrue(accountPage.isSearchFieldExist());
	}
	
	@Description("Account page headear name List")
	@Severity(SeverityLevel.CRITICAL)
	@Test
	public void getAllHeaders() {
		List<String> allHeaders = accountPage.getAllHeaders();
		Assert.assertEquals(allHeaders, AppConstant.ACCOUNT_PAGE_ALL_HEADERS_NAME);
	}
	
	@Description("Search the product and validate the product info")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void searchProduct() {
		searchResultPage  = accountPage.doSearchResult("MacBook");
		Assert.assertTrue(searchResultPage.getSearchResultPageUrl().contains(AppConstant.SEARCH_RESULT_PAGE_URL_FRACTION));
		productInfoPage  = searchResultPage.selectTheProduct("MacBook Pro");
		String productName = productInfoPage.getProductName();
		Assert.assertEquals(productName, "MacBook Pro");
	}
}
