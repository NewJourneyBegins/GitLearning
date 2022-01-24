package com.qa.zerobank.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.zerobank.base.TestBase;

public class LogInPage extends TestBase {

	// LoginPage Object Repository
	@FindBy(id = "user_login")
	WebElement lusername;

	@FindBy(name = "user_password")
	WebElement lpassword;

	@FindBy(css = ".icon-question-sign")
	WebElement questionmark;

	@FindBy(css = "[name='user_remember_me]")
	WebElement signincheckbox;

	@FindBy(name = "submit")
	WebElement signinbutton;

	@FindBy(linkText = "Forgot your password")
	WebElement forgotpassword;

	@FindBy(id = "details-button")
	WebElement detailsbutton;

	@FindBy(linkText = "Proceed to zero.webappsecurity.com (unsafe)")
	WebElement proceedtolink;
	
	@FindBy(xpath = "//div[contains(text(),'Login and/or password are wrong.')]")
	WebElement errMessage;


	// Initializing the Page Objects
	public LogInPage() {

		PageFactory.initElements(driver, this);
	}

	public void assertLogInPageTitle() {
		assertEquals(driver.getTitle(), "Zero - Log in");
	}

	public  AccountSummaryPage logIn() {
		lusername.sendKeys(prop.getProperty("username"));
		lpassword.sendKeys(prop.getProperty("password"));
		signinbutton.click();
		detailsbutton.click();
		proceedtolink.click();
		
		return new AccountSummaryPage();
	}
	public void logInCheck(String Username, String Password) {
		lusername.sendKeys(Username);
		lpassword.sendKeys(Password);
		signinbutton.click();	

	}
	
	public void loginErrorMessageValidation() {
		assertEquals(errMessage.getText(), "Login and/or password are wrong.");
		System.out.println("Successfully received Message : "+ errMessage.getText());
		
	}

}
