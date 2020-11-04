package com.appmanager.qa.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.appmanager.qa.base.TestBase;
import com.appmanager.qa.helperclasses.BrowserHelper;
import com.appmanager.qa.helperclasses.CommonActions;
import com.appmanager.qa.helperclasses.LoggerHelper;
import com.relevantcodes.extentreports.ExtentTest;

public class LoginPage extends TestBase{

	private ExtentTest logger; 
	private WebDriver driver;
	private CommonActions commonActions;
	private BrowserHelper browserHelper;
	Logger log = LoggerHelper.getLogger(LoginPage.class);

	//Page factory 
	@FindBy(xpath="//div[@class=\"form-group\"]//input[@name=\"Email\"]")
	WebElement Email; 
	
	@FindBy(xpath="//div[@class=\"form-group\"]//input[@name=\"Password\"]")
	WebElement Password; 
	
	@FindBy(xpath="//input[@value=\"Log in\" and @class=\"btn btn-primary\"]")
	WebElement Login;
	
	@FindBy(xpath="//img[@alt=\"Open Methods Logo\"]")
	WebElement LoginPageLogo;
	
	@FindBy(xpath="//div[@class=\"validation-summary-errors error-text-color\"]//ul//li")
	WebElement invalidLoginErrorMessage;

	@FindBy(xpath = "//div[@class=\"login-options\"]//a[@href=\"/Account/ForgotPassword\"]")
	WebElement ForgotPassword;

	//Initialize page factory 
	public LoginPage(WebDriver driver, ExtentTest logger){
				
		commonActions = new CommonActions(driver, logger);
		browserHelper = new BrowserHelper(driver);
		log.info("Initialized page factory of Login Page.");
		PageFactory.initElements(driver, this);

	}
	
	public boolean loginPageLogo(){
		
		return LoginPageLogo.isDisplayed();
		
	}

	public String errorMessage(){
		String s=  invalidLoginErrorMessage.getText();
		return s;
	}

	public ForgotPasswordPage clickOnForgotPassword() {

		if(commonActions.isElementPresent(ForgotPassword)) {
			ForgotPassword.click();
		}
		else {
			log.info("Forgot Password link is not functional");
		}

		return new ForgotPasswordPage(driver,logger);
	}
	public HomePage login(String e, String pwd)
	{
		
		Email.sendKeys(e);
		Password.sendKeys(pwd);
		Login.click();
		return new HomePage(driver,logger);  
	}
}
