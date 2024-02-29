package com.qa.opencart.test;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;

public class ProductResultPageTest extends BaseTest {

	@BeforeClass
	public void loginTest() {
		 accountPage = loginPage.doLogin(prop.getProperty("userId"), prop.getProperty("password"));
			}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][]{
			{"MacBook","MacBook Pro",4},
			{"MacBook","MacBook Air",4},
			{"iMac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1}
		};
	}
	
	@Test(dataProvider="productData")
	public void getImageCountTest(String searchKey,String productName, int imageCount) {
		searchResultPage  = accountPage.doSearchResult(searchKey);
		Assert.assertTrue(searchResultPage.getSearchResultPageUrl().contains(AppConstant.SEARCH_RESULT_PAGE_URL_FRACTION));
		productInfoPage  = searchResultPage.selectTheProduct(productName);
		Assert.assertEquals(productInfoPage.imageCount(),imageCount);
	}
	
	@Test
	public void productInfoTest() {
		searchResultPage  = accountPage.doSearchResult("MacBook");
		Assert.assertTrue(searchResultPage.getSearchResultPageUrl().contains(AppConstant.SEARCH_RESULT_PAGE_URL_FRACTION));
		productInfoPage  = searchResultPage.selectTheProduct("MacBook Pro");
		Map<String, String> productInfo = productInfoPage.getProductInfo();
		softAssert.assertEquals(productInfo.get("Product Name"), "MacBook Pro");
		softAssert.assertEquals(productInfo.get("Brand"), "Apple");
		softAssert.assertEquals(productInfo.get("Product Code"), "Product 18");
		softAssert.assertEquals(productInfo.get("Reward Points"), "800");
		softAssert.assertEquals(productInfo.get("Availability"), "In Stock");
		softAssert.assertEquals(productInfo.get("price"), "$2,000.00");
		softAssert.assertEquals(productInfo.get("productExtraPrice"), "$2,000.00");
		softAssert.assertAll();
	}
	@Test
	public void footerLinkTest() {
		searchResultPage  = accountPage.doSearchResult("MacBook");
		Assert.assertTrue(searchResultPage.getSearchResultPageUrl().contains(AppConstant.SEARCH_RESULT_PAGE_URL_FRACTION));
		productInfoPage  = searchResultPage.selectTheProduct("MacBook Pro");
		List<String> footerList=productInfoPage.getFooterLink();
		softAssert.assertTrue(footerList.contains("About Us"));
		softAssert.assertTrue(footerList.contains("Contact Us"));
		softAssert.assertTrue(footerList.contains("Specials"));
		softAssert.assertTrue(footerList.contains("Newsletter"));
		softAssert.assertAll();
	}
}
