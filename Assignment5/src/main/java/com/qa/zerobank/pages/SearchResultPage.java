package com.qa.zerobank.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.zerobank.base.TestBase;

public class SearchResultPage extends TestBase{
	

	//object repository
	
	@FindBy(xpath = "//h2[contains(text(),'Search Results:')]")
	WebElement searchResults;
	
	@FindBy(partialLinkText = "Online Banking")
	WebElement OnlineBanking;
	
	@FindBy(partialLinkText = "Pay Bills")
	WebElement  PayBills;
	
	//constructor
	
	public  SearchResultPage() {
		
		PageFactory.initElements(driver, this);
	}
	
	 
	//assert title
	
	public void assertSearchResultPageTitle() {
		 assertEquals(driver.getTitle(), "Zero - Search Tips");
	 }
	
	public OnlineBankingPage OnlineBankingPageLinkCLicked() {
		OnlineBanking.click();
		return new OnlineBankingPage();
	}
	
	public PayBillsPage PayBillsPageLinkClicked() {
		PayBills.click();
		return new PayBillsPage();
	}
	
	public void verifySearchResult() {
		if(OnlineBanking.isDisplayed()) {
			System.out.println("Result Found");
			OnlineBankingPageLinkCLicked();
		}else if(PayBills.isDisplayed()) {
			System.out.println("Result Found");
			PayBillsPageLinkClicked();
		}else
			System.out.println("Result not Found");
	}
	
//	public void verify_SearchResult_notFound() {
//		System.out.println("Result not Found");
//	}
}
