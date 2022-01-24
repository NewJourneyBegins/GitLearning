package com.qa.zerobank.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.testng.Assert.assertEquals;
import com.qa.zerobank.base.TestBase;
import com.qa.zerobank.util.TestUtil;

public class HomePage extends TestBase{
	
	//Basic Object Repository
	@FindBy(id="signin_button")
	WebElement signInButton;
	
	@FindBy(name="searchTerm")
	WebElement searchBox;
	
	@FindBy(linkText="Zero Bank")
	WebElement brandLinkZeroBank;
	
	@FindBy(css="#online-banking")
	WebElement buttonMoreServices;
	
	@FindBy(className="brand")
	WebElement brandLogo;
	
	@FindBy(xpath="//strong[contains(text(),'Feedback')]")
	WebElement feedBack;
	
	@FindBy(xpath="//strong[contains(text(),'Online Banking')]")
	WebElement onlineBanking;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public void assertHomePageTitle() {
		assertEquals(driver.getTitle(),"Zero - Personal Banking - Loans - Credit Cards");
	}
	
	public boolean validateBrandLogo() {
		return brandLogo.isDisplayed();
	}
	
	public LogInPage clickOnSignInButton() {
		signInButton.click();
		return new LogInPage();
	}
	
	public SearchResultPage enterOnSearchBox(String searchData) {
		searchBox.click();
		searchBox.sendKeys(searchData);
		searchBox.sendKeys(Keys.ENTER);
		return new SearchResultPage();
	}
	
	
}
