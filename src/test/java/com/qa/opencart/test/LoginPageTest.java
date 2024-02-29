package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.listener.TestAllureListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design opencard login page")
@Story("US 101: Login page feature")
@Feature("login page test feature")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {

	@Description("Login page title test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, "Account Login");
	}
	@Description("Login page Url test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actTitle = loginPage.getLoginPageUrl();
		Assert.assertTrue(actTitle.contains("account/login"));
	}

	@Description("Cheak forgot password link is exist")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 3)
	public void isForgotPasswordLinkExistTest() {
		Assert.assertTrue(loginPage.isForgotPasswordLinkExist());
	}

	@Description("Logo is exist")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 4)
	public void isLogoExistTest() {
		Assert.assertTrue(loginPage.isLogoExist());
	}

	@Description("User successfully login with valid credential")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	public void doLoginTest() {
		accountPage = loginPage.doLogin(prop.getProperty("userId"), prop.getProperty("password"));
		Assert.assertEquals(accountPage.getAccountPageTitle(), AppConstant.ACCOUNTS_PAGE_TITLE);
	}

}
