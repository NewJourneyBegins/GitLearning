package com.qa.zerobank.testcases;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.*;
import com.qa.zerobank.base.TestBase;
import com.qa.zerobank.pages.AccountSummaryPage;
import com.qa.zerobank.pages.HomePage;
import com.qa.zerobank.pages.LogInPage;
import com.qa.zerobank.pages.SearchResultPage;
import com.qa.zerobank.util.TestUtil;

public class AccountSummaryTestCases extends TestBase {

	HomePage homePage;
	LogInPage logInPage;
	TestUtil testUtil;
	SearchResultPage searchResultPage;
	String sheetName = "Search";
	AccountSummaryPage accountsummarypage;

	public AccountSummaryTestCases() {
		super();
	}

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		initialization();
		homePage = new HomePage();
		logInPage = new LogInPage();
		accountsummarypage = new AccountSummaryPage();
	}

	@AfterMethod(alwaysRun = true)
	public void cleanUp() {

		accountsummarypage.logout();
		// close driver
		driver.close();
		driver.quit();
	}

	@Test(priority = 1, groups = { "Smoke" })
	public void Purchase_Foreign_Currency_Cash_Alert_Test() {

		logInPage = homePage.clickOnSignInButton();
		accountsummarypage = logInPage.logIn();
		accountsummarypage.assertAccountSummaryPageTitle();

		accountsummarypage.verifyPurchaseForeignCurrencyAlert();
	}

	@Test(priority = 2, groups = { "Smoke" })
	public void Fund_Transfer_test() {
		logInPage = homePage.clickOnSignInButton();
		accountsummarypage = logInPage.logIn();
		accountsummarypage.assertAccountSummaryPageTitle();
		
		accountsummarypage.verifyFundTransfer();

	}

	// Negative
	@Test(priority = 3,groups = {"Regression"})
	public void Purchase_Foreign_Currency_Cash_Invalid_Amount_Test() {
		logInPage = homePage.clickOnSignInButton();
		accountsummarypage = logInPage.logIn();
		accountsummarypage.assertAccountSummaryPageTitle();
		
		accountsummarypage.verify_Purchase_Foreign_Currency_Cash_Invalid_Amount();
	}

}
