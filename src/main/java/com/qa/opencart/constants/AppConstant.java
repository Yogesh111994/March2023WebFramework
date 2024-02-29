package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstant {
	
	// COMMAN CONSTANT
	public static final int DEFAULT_SHORT_WAIT=5;
	public static final int DEFAULT_MEDIUM_WAIT=10;
	public static final int DEFAULT_LONG_WAIT=20;
	
	// LOGIN PAGE CONSTANT VALUE
	public static final String LOGIN_PAGE_TITLE="Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION="account/login";
	
	
	// CCOUNT PAGE CONSTANT VALUE
	public static final String ACCOUNTS_PAGE_TITLE="My Account";
	public static final String ACCOUNTS_PAGE_URL_FRACTION="route=account/account";
	public  static final String ACCOUNTS_PAGE_LOGOUT="Logout";
	public static final List<String> ACCOUNT_PAGE_ALL_HEADERS_NAME= Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	
	// SEARCH RESULT CONSTANT VALUE
		public static final String SEARCH_RESULT_PAGE_URL_FRACTION="search&search";
		
		// REGISTARION
		public static final CharSequence USER_REGISTERATION_SUCCESS_MESG = "Your Account Has Been Created!";
}
