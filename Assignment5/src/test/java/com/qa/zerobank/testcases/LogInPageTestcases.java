package com.qa.zerobank.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.zerobank.base.TestBase;
import com.qa.zerobank.pages.AccountSummaryPage;
import com.qa.zerobank.pages.HomePage;
import com.qa.zerobank.pages.LogInPage;

public class LogInPageTestcases extends TestBase {

	HomePage homePage;
	LogInPage logInPage;
	AccountSummaryPage accountsummarypage;

	public LogInPageTestcases() {
		super();// when you want to initialize or call super class, hwere base class is super
				// class so it willinvoke testbase class properties thing which we have have
				// written inside constructor TestBase
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		initialization();
		homePage = new HomePage();
		logInPage = new LogInPage();
	}

	@AfterMethod(alwaysRun = true)
	public void cleanUp() {
		// close driver
		driver.close();
		driver.quit();
	}

	// positive
	@Test(priority = 1, groups = { "Smoke" })
	public void validate_LogIn_Functionality_Valid_Credentials() {
		logInPage = homePage.clickOnSignInButton();
		accountsummarypage = logInPage.logIn();
		accountsummarypage.assertAccountSummaryPageTitle();
		accountsummarypage.logout();
	}

	// Negative
	@Test(groups = { "Regression" })
	public void validate_LogIn_Functionality_InValid_Username_Valid_Password() {
		logInPage = homePage.clickOnSignInButton();
		logInPage.logInCheck("user123", "password");
		logInPage.loginErrorMessageValidation();

	}

	// Negative
	@Test(groups = { "Regression" })
	public void validate_LogIn_Functionality_Valid_Username_InValid_Password() {
		logInPage = homePage.clickOnSignInButton();
		logInPage.logInCheck("username", "password@");
		logInPage.loginErrorMessageValidation();

	}

	// Negative
	@Test( groups = { "Regression" })
	public void validate_LogIn_Functionality_Empty_Username_And_Password() {
		logInPage = homePage.clickOnSignInButton();
		logInPage.logInCheck(" ", " ");
		logInPage.loginErrorMessageValidation();

	}
	
	// Negative
		@Test( groups = { "Regression" })
		public void validate_LogIn_Functionality_InValid_Username_Invalid_Password() {
			logInPage = homePage.clickOnSignInButton();
			logInPage.logInCheck("@@@", "!@#$ ");
			logInPage.loginErrorMessageValidation();

		}

}
