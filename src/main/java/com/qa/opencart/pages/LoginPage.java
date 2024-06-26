package com.qa.opencart.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;
	public static final Logger log = LogManager.getLogger(LoginPage.class);

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgetPwdLink = By.linkText("Forgotten Password");
	private By logo = By.cssSelector("img[title='naveenopencart']");
	private By registerLink = By.linkText("Register");
	private By searchField = By.name("search");

	@Step("Login page title")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs("Account Login", 5);
		// System.out.println("Login page title :" + title);
		log.info("Login page title :\" + title");
		return title;
	}

	@Step("Login page url")
	public String getLoginPageUrl() {
		String pageUrl = eleUtil.waitForURLContains("account/login", 5);
		// System.out.println("Login page Url : " + pageUrl);
		log.info("Login page Url : " + pageUrl);
		return pageUrl;
	}

	@Step("Forgot password link is exist")
	public boolean isForgotPasswordLinkExist() {
		return eleUtil.waitForVisibilityOfElement(forgetPwdLink, 5).isDisplayed();
	}

	@Step("Check Logo is displayed")
	public boolean isLogoExist() {
		return eleUtil.waitForVisibilityOfElement(logo, 5).isDisplayed();
	}

	@Step(" login with username:{0} and password:{1}")
	public AccountsPage doLogin(String userName, String pwd) {
		// System.out.println("Username: "+userName+"password :"+pwd);
		eleUtil.waitForVisibilityOfElement(email, 5).clear();
		eleUtil.waitForVisibilityOfElement(password, 5).clear();
		log.info("Username: " + userName + "password :" + pwd);
		eleUtil.waitForVisibilityOfElement(email, 5).sendKeys(userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		// System.out.println("user logged in");
		log.info("user logged in");
		return new AccountsPage(driver);
	}

	@Step("navigate to register page")
	public UserRegistartionPage navigateToRegister() {
		eleUtil.waitForVisibilityOfElement(registerLink, AppConstant.DEFAULT_MEDIUM_WAIT).click();
		return new UserRegistartionPage(driver);
	}

	@Step("Check the seach field exist on login page")
	public boolean searchFieldExist() {
		return eleUtil.waitForVisibilityOfElement(searchField, 5).isDisplayed();
	}
}
