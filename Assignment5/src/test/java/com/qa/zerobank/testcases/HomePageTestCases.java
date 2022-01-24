package com.qa.zerobank.testcases;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.zerobank.base.TestBase;
import com.qa.zerobank.pages.HomePage;
import com.qa.zerobank.pages.LogInPage;
import com.qa.zerobank.pages.SearchResultPage;
import com.qa.zerobank.util.TestUtil;

public class HomePageTestCases extends TestBase {
	
	HomePage  homePage;
	LogInPage logInPage;
	TestUtil testUtil;
	SearchResultPage searchResultPage;
	String sheetName = "Search";
	
	public HomePageTestCases() {
		super();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		initialization();
		homePage = new HomePage();
		logInPage = new LogInPage();
		testUtil = new TestUtil();
	}
	
	@AfterMethod(alwaysRun = true)
	public void cleanUp() {
		
		//TestUtil.takeScreenshotAtEndOfTest("HomePage");
		
		//close driver
		driver.close();
		driver.quit();
	}
	
	@Test
	public void validateHomePage() {
		homePage.assertHomePageTitle();
	}
	
	@Test
	public void validateLogo() {
		assertTrue(homePage.validateBrandLogo());
	}
	
	@Test(priority = 1, groups = { "Smoke" })
	public void signInButtonTest() {
		logInPage = homePage.clickOnSignInButton();
		logInPage.assertLogInPageTitle();
	}
	
	//positive 
	
	@Test(priority = 2, groups = { "Smoke" })
	public void searchBoxPositiveTest() {
		searchResultPage = homePage.enterOnSearchBox("Online Banking");
		searchResultPage.assertSearchResultPageTitle();
		searchResultPage.verifySearchResult();
	}
	
	

	


}
