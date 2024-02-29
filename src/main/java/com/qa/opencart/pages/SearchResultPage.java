package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	public String getSearchResultPageUrl() {
		return eleUtil.waitForURLContains(AppConstant.SEARCH_RESULT_PAGE_URL_FRACTION, AppConstant.DEFAULT_MEDIUM_WAIT);
	}
	
	public ProductInfoPage selectTheProduct(String productName) {
		By product=By.linkText(productName);
		eleUtil.doClickWithWait(product, AppConstant.DEFAULT_MEDIUM_WAIT);
		return new ProductInfoPage(driver);
	}
}
