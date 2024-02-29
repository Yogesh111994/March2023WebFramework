package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.JavaScriptUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	private JavaScriptUtil javaScriptUtil;

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
		javaScriptUtil = new JavaScriptUtil(driver);
	}

	Map<String, String> productMap = new HashMap<String, String>();

	private By productname = By.cssSelector("div.col-sm-4 h1");
	private By productInfo = By.xpath("(//h1[text()='MacBook Pro']/following-sibling::ul)[1]/li");
	private By productPriceInfo = By.xpath("(//h1[text()='MacBook Pro']/following-sibling::ul)[2]/li");
	private By img = By.cssSelector("a.thumbnail img");
	// private By footer = By.cssSelector("div.container .col-sm-3 li");
	By footer = By.xpath("//div[@class='container']//div[@class='col-sm-3']//li");

	public String getProductName() {
		String productName = eleUtil.waitForVisibilityOfElement(productname, AppConstant.DEFAULT_MEDIUM_WAIT).getText();
		System.out.println(productName);
		return productName;
	}

	public int imageCount() {
		return eleUtil.waitForVisibilityOfElements(img, AppConstant.DEFAULT_MEDIUM_WAIT).size();
	}

	private void getMetaDataOfProduct() {
		List<WebElement> productInfoList = eleUtil.waitForVisibilityOfElements(productInfo,
				AppConstant.DEFAULT_MEDIUM_WAIT);
		for (WebElement e : productInfoList) {
			String metaData = e.getText();
			String productKey = metaData.split(":")[0].trim();
			String productValue = metaData.split(":")[1].trim();
			productMap.put(productKey, productValue);
		}
	}

	private void getProductPrice() {
		List<WebElement> priceList = eleUtil.waitForVisibilityOfElements(productPriceInfo,
				AppConstant.DEFAULT_MEDIUM_WAIT);
		String productPrice = priceList.get(0).getText();
		String productExTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("price", productPrice);
		productMap.put("productExtraPrice", productExTaxPrice);
	}

	public Map<String, String> getProductInfo() {
		productMap.put("Product Name", getProductName());
		getMetaDataOfProduct();
		getProductPrice();
		return productMap;
	}

	public List<String> getFooterLink() {
		javaScriptUtil.scrollPageDown();
		List<WebElement> footerList = eleUtil.waitForPresenceOfElements(footer, AppConstant.DEFAULT_MEDIUM_WAIT);
		List<String> footertextList = new ArrayList<String>();
		for (WebElement e : footerList) {
			String footerText = e.getText();
			System.out.println(footerText);
			footertextList.add(footerText);
		}
		return footertextList;
	}

}
