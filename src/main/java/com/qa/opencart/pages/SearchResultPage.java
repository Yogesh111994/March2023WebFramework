package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class SearchResultPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public static final Logger log = LogManager.getLogger(SearchResultPage.class);

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public String getSearchResultPageUrl() {
		return eleUtil.waitForURLContains(AppConstant.SEARCH_RESULT_PAGE_URL_FRACTION, AppConstant.DEFAULT_MEDIUM_WAIT);
	}

	@Step("'Select the product given by user")
	public ProductInfoPage selectTheProduct(String productName) {
		By product = By.linkText(productName);
		eleUtil.doClickWithWait(product, AppConstant.DEFAULT_MEDIUM_WAIT);
		return new ProductInfoPage(driver);
	}
}
