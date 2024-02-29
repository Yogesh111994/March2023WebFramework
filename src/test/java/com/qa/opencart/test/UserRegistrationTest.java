package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ExcelUtil;


public class UserRegistrationTest extends BaseTest{

	@BeforeClass
	public void regSetup() {
		userRegistartionPage  = loginPage.navigateToRegister();
	}
	
	public String getRandomEmail(String name) {
		return name+System.currentTimeMillis()+"@opencart.com";
	}
	@DataProvider
	public Object[][] userData() {
		return new  Object [][]  {
			{"ravi","biradar","9999988888","ravi234","yes"},
			{"ashi","jadhav","7777788888","ashi234","no"},
			{"sachiv","gund","9577588888","sach234","yes"}
		};
	}
	
	@DataProvider
	public Object[][] getExcelUserData() {
		return ExcelUtil.registerUserData("register");
	}
	@Test(dataProvider="userData")
	public void registrationFormTest(String firstname, String lastname,String phoneNumber, String password,String subscribe) {
		boolean resDone=userRegistartionPage.registrationForm(firstname,lastname,getRandomEmail(firstname) , phoneNumber, password, subscribe);
		Assert.assertTrue(resDone);
	}
	
	
}
