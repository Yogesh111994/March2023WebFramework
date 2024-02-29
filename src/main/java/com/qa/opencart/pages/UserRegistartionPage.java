package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstant;
import com.qa.opencart.utils.ElementUtil;

public class UserRegistartionPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By firstname = By.name("firstname");
	private By lastname = By.name("lastname");
	private By email = By.name("email");
	private By telephone = By.name("telephone");
	private By password = By.name("password");
	private By passwordConfirm = By.name("confirm");
	private By subscribeYes = By.xpath("(//div[@class='form-group']/div//label)[1]/input");
	private By subscribeNo = By.xpath("(//div[@class='form-group']/div//label)[2]/input");
	private By conditionCheckbox = By.name("agree");
	private By submitButton = By.cssSelector("input.btn.btn-primary");
	private By successMessg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public UserRegistartionPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	public boolean registrationForm(String firstname, String lastname, String email, String telephone, String password,
			String subscribe) {
		eleUtil.waitForVisibilityOfElement(this.firstname, AppConstant.DEFAULT_MEDIUM_WAIT).sendKeys(firstname);
		eleUtil.doSendKeys(this.lastname, lastname);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(passwordConfirm, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(subscribeYes);
		} else {
			eleUtil.doClick(subscribeNo);
		}
		eleUtil.doClick(conditionCheckbox);
		eleUtil.doClick(submitButton);
		if (eleUtil.waitForVisibilityOfElement(successMessg, AppConstant.DEFAULT_MEDIUM_WAIT).getText()
				.contains(AppConstant.USER_REGISTERATION_SUCCESS_MESG)) {
			System.out.println("User registration successful");
			eleUtil.doClick(logoutLink);
			eleUtil.waitForVisibilityOfElement(registerLink, AppConstant.DEFAULT_MEDIUM_WAIT).click();
			return true;
		} else {
			return false;
		}
	}
}
