package com.qa.zerobank.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.zerobank.base.TestBase;

public class AccountSummaryPage extends TestBase {

	// object repository

	@FindBy(id = "account_summary_tab")
	WebElement accountSummaryTab;

	@FindBy(id = "account_activity_tab")
	WebElement accountActivityTab;

	@FindBy(id = "transfer_funds_tab")
	WebElement transferFundTab;

	@FindBy(id = "pay_bills_tab")
	WebElement payBillsTab;

	@FindBy(id = "money_map_tab")
	WebElement moneyMapTab;

	@FindBy(id = "online_statements_tab")
	WebElement onlineStatementsTab;

	@FindBy(linkText = "Purchase Foreign Currency")
	WebElement purchaseForeigCurrencyTab;

	@FindBy(id = "purchase_cash")
	WebElement purchasecashButton;

	@FindBy(xpath = ".//body/div[1]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[3]/a[1]")
	WebElement profile;

	@FindBy(id = "tf_fromAccountId")
	WebElement fromAccount;

	@FindBy(xpath = "//a[contains(@id,'logout')]")
	WebElement logoutButton;

	@FindBy(id = "tf_toAccountId")
	WebElement toAccount;

	@FindBy(id = "tf_description")
	WebElement tf_description;

	@FindBy(id = "btn_submit")
	WebElement tf_btn_submit;

	@FindBy(id = "btn_submit")
	WebElement tf2_btn_submit;

	@FindBy(xpath = "//div[contains(text(),'You successfully submitted your transaction.')]")
	WebElement succMessage;

	@FindBy(id = "tf_amount")
	WebElement tf_amount;
		
	@FindBy(id = "pc_inDollars_true")
	WebElement radiobutton;
	
	@FindBy(id = "pc_currency")
	WebElement currency;
	
	@FindBy(id = "pc_amount")
	WebElement Amount;

	
	public AccountSummaryPage() {

		PageFactory.initElements(driver, this);
	}

	// assert title

	public void assertAccountSummaryPageTitle() {
		assertEquals(driver.getTitle(), "Zero - Account Summary");
	}

	public void verifyPurchaseForeignCurrencyAlert() {

		payBillsTab.click();
		purchaseForeigCurrencyTab.click();
		purchasecashButton.click();
		// switching to alert
		Alert purchaseAlert = driver.switchTo().alert();
		String alertText = purchaseAlert.getText();
		System.out.println("You are getting Alert :" + alertText);
		// Assert
		assertEquals(alertText, "Please, ensure that you have filled all the required fields with valid values.",
				"Alert test Failed because text doesn't match");
		purchaseAlert.accept();
		System.out.println("Alert Accepted Successfully");
	}

	// Negative 
	@Test(priority = 3, groups = { "Regression" })
	public void verify_Purchase_Foreign_Currency_Cash_Invalid_Amount() {

		payBillsTab.click();
		purchaseForeigCurrencyTab.click();
		boolean dollarIsSelected = radiobutton.isSelected();

		if (!dollarIsSelected) {
			radiobutton.click();
			System.out.println("Radio button was not selected I have selected US Dollar radio button");
		}

		Select sel = new Select(currency);
		sel.selectByVisibleText("Denmark (krone)");

		// Amount Data Type -Negative Test
		Amount.sendKeys("ABD");
		System.out.println("Entered string value in Amount field");

		// click on purchase cash button
		purchasecashButton.click();
		
		// switching to alert
		Alert purchaseAlert = driver.switchTo().alert();
		String alertText = purchaseAlert.getText();
		System.out.println("You are getting Alert :" + alertText);
		// Assert
		assertEquals(alertText, "Please, ensure that you have filled all the required fields with valid values.",
				"Alert test Failed because text doesn't match");
		purchaseAlert.accept();
		System.out.println("Alert Accepted Successfully");

	}

	public void verifyFundTransfer() {

		transferFundTab.click();

		Select fromAccountSel = new Select(fromAccount);
		fromAccountSel.selectByVisibleText("Savings(Avail. balance = $ 1548)");

		Select toAccountSel = new Select(toAccount);
		toAccountSel.selectByValue("6");

		tf_amount.sendKeys("10");
		tf_description.sendKeys("Tranferring 10 dollars");
		tf_btn_submit.click();
		tf2_btn_submit.click();

		// soft Assert
		// Verifying success message
		SoftAssert sa = new SoftAssert();
		String sMsg = succMessage.getText();
		sa.assertEquals(sMsg, "You successfully submitted your transaction.");
		sa.assertAll();
		System.out.println(sMsg);
	}

	public void logout() {

		profile.click();
		logoutButton.click();

	}

}
