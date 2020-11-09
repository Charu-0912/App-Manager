package com.appmanager.qa.pages;

import com.appmanager.qa.base.TestBase;
import com.appmanager.qa.helperclasses.BrowserHelper;
import com.appmanager.qa.helperclasses.CommonActions;
import com.appmanager.qa.helperclasses.LoggerHelper;
import com.relevantcodes.extentreports.ExtentTest;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ExternalLogins extends TestBase {


	private WebDriver driver;
	private ExtentTest logger;
	private String url;
	private CommonActions commonActions;
	private BrowserHelper browserHelper;

	Logger log = LoggerHelper.getLogger(ExternalLogins.class);

	// Page factory for External Logins Page
	@FindBy(xpath = "//div[@class=\"col-sm-12\"]//h6")
	WebElement ExternalLoginUIText1;

	@FindBy(xpath = "//div[@class=\"col-sm-12\"]//span")
	WebElement ExternalLoginUIText2;

	@FindBy(xpath = "//table[@class=\"table table-striped table-hover sortable table-margin\"]//tr//th[1]")
	WebElement ExternalLoginTableColumn1;

	@FindBy(xpath = "//table[@class=\"table table-striped table-hover sortable table-margin\"]//tr//th[2]")
	WebElement ExternalLoginTableColumn2;

	@FindBy(xpath = "//table[@class=\"table table-striped table-hover sortable table-margin\"]//tr//th[3]")
	WebElement ExternalLoginTableColumn3;

	@FindBy(xpath = "//div[@class=\"row\"]//tr[2]//div//a[@id=\"loginLink\"]")
	WebElement AuthorisedBtn;

	@FindBy(xpath = "//div[@class=\"row\"]//tr[1]//div//a[@id=\"loginLink\"]")
	WebElement ReAuthorisedBtn;

	@FindBy(xpath = "//div[@class=\"row\"]//tr[1]//div//button[@type=\"submit\"]")
	WebElement Remove;

	@FindBy(xpath = "//input[@id=\"UserName\"]")
	WebElement crmUserName;

	@FindBy(xpath = "//input[@id=\"Password\"]")
	WebElement password;

	@FindBy(xpath = "//input[@type=\"submit\"]")
	WebElement submitButton;

	@FindBy(xpath = "//div[@id=\"stage\"]//div[@class=\"alert alert-success notification\"]")
	WebElement crmRemoveSuccessMsg;

	// Initialize page factory
	public ExternalLogins(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		commonActions = new CommonActions(driver, logger);
		browserHelper = new BrowserHelper(driver);
		log.info("Initialized page factory of Home Page.");
		// logger.info("Initialized page factory of OSC Home Page.");
		PageFactory.initElements(driver, this);

	}

	public String externalLoginUIText1() {
		return ExternalLoginUIText1.getText();
	}

	public String externalLoginUIText2() {
		return ExternalLoginUIText2.getText();
	}

	public String accountTableUIColumn1() {
		return ExternalLoginTableColumn1.getText();
	}

	public String accountTableUIColumn2() {
		return ExternalLoginTableColumn2.getText();
	}

	public String accountTableUIColumn3() {
		return ExternalLoginTableColumn3.getText();
	}

	public void clickOnCrmAuthorizationButton() {
		commonActions.waitFor(3000);
		commonActions.isElementEnabled(AuthorisedBtn);
		commonActions.click(AuthorisedBtn);

	}

		public void clickOnCrmReAuthorizationButton () {
			commonActions.waitFor(3000);
			commonActions.isElementPresent(ReAuthorisedBtn);
			commonActions.click(ReAuthorisedBtn);

		}

		public String clickOnCrmRemoveAuthorization () {
			commonActions.waitFor(3000);
			commonActions.isElementPresent(Remove);
			commonActions.click(Remove);
			commonActions.isElementPresent(crmRemoveSuccessMsg);
			return crmRemoveSuccessMsg.getText();
		}

	public void emptyUsernameLogin(){
		password.sendKeys("");
		submitButton.click();
	}
	public void emptyPassLogin(){
		crmUserName.sendKeys("");
		submitButton.click();
	}

	public CRMAuthorizationPage crmLogin(String cu, String cp){
		crmUserName.sendKeys(cu);
		password.sendKeys(cp);
		submitButton.click();
		return new CRMAuthorizationPage(driver,logger);

	}

	}
